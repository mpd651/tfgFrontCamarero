package com.example.tfgfrontcamarero.ui.view

import android.app.ActionBar
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Mesa
import com.example.tfgfrontcamarero.data.model.Producto
import com.example.tfgfrontcamarero.data.model.ProductosPedido
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.CategoriaViewModel
import com.example.tfgfrontcamarero.ui.viewmodel.PedidoViewModel
import com.example.tfgfrontcamarero.ui.viewmodel.ProductoViewModel
import com.google.android.flexbox.FlexboxLayout

class CategoriaProductoActivity : AppCompatActivity() {

    private val pedidoViewModel: PedidoViewModel by viewModels()
    private val categoriaViewModel: CategoriaViewModel by viewModels()
    private val productoViewModel: ProductoViewModel by viewModels()

    private var productosBool:Boolean = false
    private var productosPedidosNuevos:MutableMap<String, ProductosPedido> = mutableMapOf()
    private var importeTotal:Float = 0.0F
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_producto)

        val container: FlexboxLayout = findViewById(R.id.activityPedidoLayout)
        val botonPedido:Button = findViewById(R.id.btnPedido)


        usuario = intent.getSerializableExtra("usuario")as Usuario
        var mesa = intent.getSerializableExtra("mesa")as? Mesa
        var pedido = intent.getSerializableExtra("pedido")as? PedidoDto

        if (pedido!=null){
            mesa = pedido.mesa
            importeTotal+=pedido.importe
            botonPedido.setText("Mi pedido: "+importeTotal+" €")
        }else{
            pedidoViewModel.getPedidoByMesaId(mesa!!.id!!)
            pedidoViewModel.pedidoModelDto.observe(this, Observer{
                pedido=it
                importeTotal+=it.importe
                botonPedido.setText("Mi pedido: "+importeTotal+" €")
            })
        }

        val productosActualizados = intent.getSerializableExtra("productosActualizados") as? ArrayList<ProductosPedido>
        if (productosActualizados != null) {
            productosPedidosNuevos.clear()
            for (productoActualizado in productosActualizados){
                productosPedidosNuevos[productoActualizado!!.producto!!.nombre!!]=productoActualizado
                importeTotal+=productoActualizado.cantidad!!*productoActualizado!!.producto!!.precio!!
            }
        }

        findViewById<TextView>(R.id.txtUserPedidoMesa).setText("Usuario: "+usuario.userName)
        findViewById<TextView>(R.id.txtMesaPedidoMesa).setText("Mesa: "+mesa.numeroMesa)

        categoriaViewModel.getAllCategorias()
        categoriaViewModel.categoriasModel.observe(this, Observer {
            findViewById<TextView>(R.id.txtTituloCategoriasProducto).setText("Categorías")
            container.removeAllViews()
            productosBool=false

            for (categoria in it) {
                val button = Button(this)
                val buttonSize = resources.displayMetrics.widthPixels/3
                val params = ActionBar.LayoutParams(buttonSize, buttonSize)

                button.layoutParams = params
                button.setTag(categoria.id)
                button.setText(categoria.nombre)

                container.addView(button)

                button.setOnClickListener{
                    productoViewModel.getProductosByCategoria(categoria.id)
                    productoViewModel.productosModelDto.observe(this, Observer{
                        findViewById<TextView>(R.id.txtTituloCategoriasProducto).setText("Productos")
                        container.removeAllViews()
                        productosBool=true
                        for (producto in it){
                            val button = Button(this)

                            button.layoutParams = params
                            button.setTag(producto.id)
                            button.setText(producto.nombre+"\n"+producto.precio+" €")

                            container.addView(button)
                            button.setOnClickListener{
                                importeTotal= importeTotal+ producto.precio
                                botonPedido.setText("Mi pedido: "+importeTotal+" €")

                                if (productosPedidosNuevos[producto.nombre]==null){
                                    var productoPedido = ProductosPedido(null, producto= Producto(id=producto.id, nombre =producto.nombre, precio =producto.precio), null, 1,null)
                                    productosPedidosNuevos[producto.nombre]=productoPedido
                                }else{
                                    productosPedidosNuevos[producto.nombre]!!.cantidad = productosPedidosNuevos[producto.nombre]!!.cantidad?.plus(
                                        1
                                    )
                                }
                            }
                        }

                    })
                }

            }
        })


        botonPedido.setOnClickListener{
            val intent = Intent(this@CategoriaProductoActivity, PedidoActvity::class.java )
            val hashMap:HashMap<String, ProductosPedido> = HashMap(productosPedidosNuevos)

            intent.putExtra("mesa", mesa)
            intent.putExtra("pedido", pedido)
            intent.putExtra("productosNuevos", hashMap)
            intent.putExtra("usuario", usuario)

            startActivity(intent)

        }

    }


    override fun onBackPressed()
    {
        if (productosBool==true){
            productosBool=false
            categoriaViewModel.getAllCategorias()
        }else{
            val intent = Intent(this@CategoriaProductoActivity, MesasPedidosActivity::class.java )
            intent.putExtra("usuario", usuario)
            this@CategoriaProductoActivity.finish()
            startActivity(intent)
        }
    }
}