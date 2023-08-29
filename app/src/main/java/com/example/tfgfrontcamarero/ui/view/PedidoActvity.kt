package com.example.tfgfrontcamarero.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Mesa
import com.example.tfgfrontcamarero.data.model.Pedido
import com.example.tfgfrontcamarero.data.model.Producto
import com.example.tfgfrontcamarero.data.model.ProductosPedido
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.PedidoViewModel
import com.example.tfgfrontcamarero.ui.viewmodel.ProductosPedidosViewModel

class PedidoActvity : AppCompatActivity(), AdapterRecyclerView.OnItemClickListener {

    private val pedidoViewModel: PedidoViewModel by viewModels()
    private val productosPedidoViewModel: ProductosPedidosViewModel by viewModels()

    var precioProductosPedidoRecibido:Float = 0F
    var precioProductosNuevos:Float = 0F
    var precioTotal:Float = 0F
    var productosNuevosLista:MutableList<ProductosPedido> = emptyList<ProductosPedido>().toMutableList()
    lateinit var pedidoRecibido: PedidoDto
    lateinit var mesa: Mesa
    lateinit var usuario: Usuario
    var productosViejos:MutableList<ProductosPedido> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedido)

        mesa = intent.getSerializableExtra("mesa")as Mesa
        usuario = intent.getSerializableExtra("usuario")as Usuario
        pedidoRecibido = (intent.getSerializableExtra("pedido") as? PedidoDto)!!
        val productosNuevos = intent.getSerializableExtra("productosNuevos") as? MutableMap<String, ProductosPedido>
        productosNuevosLista=productosNuevos!!.values.toMutableList()

        findViewById<TextView>(R.id.txtUser).setText("Usuario: "+usuario.userName)
        findViewById<TextView>(R.id.txtMesa).setText("Mesa: "+mesa.numeroMesa)


        for (productoNuevo in productosNuevos!!.values.toList()){
            val cantidad: Int? = productoNuevo.cantidad
            val preci: Float? = productoNuevo.producto?.precio
            precioProductosNuevos += cantidad!! * preci!!
        }

        val items = mutableListOf<String>()
        if (pedidoRecibido.id != -1L) {

            productosPedidoViewModel.getProductosPedidoByPedidoId(pedidoRecibido.id)
            productosPedidoViewModel.productosPedidosModelDto.observe(this@PedidoActvity, Observer{
                for (dto in it){
                    var pp = ProductosPedido(dto.productoPedidoid, producto= Producto(id = dto.productoId ,nombre = dto.productoNombre, precio = dto.productoPrecio), cantidad = dto.cantidad)
                    productosViejos.add(pp)
                    precioProductosPedidoRecibido += dto.cantidad!! * dto.productoPrecio!!
                }
                var recyclerViewPedidosViejos = findViewById<RecyclerView>(R.id.lstProductosPedidosViejos)
                recyclerViewPedidosViejos.layoutManager = LinearLayoutManager(this)
                val adapter = AdapterRecyclerView(productosViejos)
                adapter.setOnItemClickListener(this)
                recyclerViewPedidosViejos.adapter = adapter


                precioTotal=precioProductosNuevos+precioProductosPedidoRecibido
                findViewById<TextView>(R.id.txtTotal).setText("Total: "+(precioTotal)+" €")
            })

        }


        val recyclerView = findViewById<RecyclerView>(R.id.productosPedidosNuevos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = AdapterRecyclerView(productosNuevos!!.values.toMutableList())
        adapter.setOnItemClickListener(this)
        recyclerView.adapter = adapter


        findViewById<Button>(R.id.btnPagar).setOnClickListener{
            val productosEnviar:List<ProductosPedido> = productosNuevosLista+ productosViejos

            if (pedidoRecibido!!.id==-1L){
                var pedidoObject = Pedido(mesa = mesa, productosPedidos = productosEnviar)
                pedidoViewModel.crearPedido(pedidoObject, usuario.id!!)
                pedidoViewModel.pedidoIdDevuelto.observe(this, Observer{
                    pedidoViewModel.pagar(usuario.id!!, Pedido(id = it, productosPedidos = productosEnviar))
                })
            }else{
                pedidoViewModel.pagar(usuario.id!!, Pedido(id = pedidoRecibido.id, productosPedidos = productosEnviar))
            }

            val intent = Intent(this@PedidoActvity, MesasPedidosActivity::class.java )
            intent.putExtra("usuario", usuario)
            this@PedidoActvity.finish()
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnGuardar).setOnClickListener{
            val productosEnviar:List<ProductosPedido> = productosNuevosLista+ productosViejos

            if (pedidoRecibido!!.id==-1L){
                var pedidoObject = Pedido(mesa = mesa, productosPedidos = productosEnviar)
                pedidoViewModel.crearPedido(pedidoObject, usuario.id!!)
            }else{
                var pedidoObject = Pedido(id= pedidoRecibido.id,productosPedidos = productosEnviar)
                pedidoViewModel.actualizarPedido(pedidoObject, usuario.id!!)
            }

            val intent = Intent(this@PedidoActvity, MesasPedidosActivity::class.java )
            intent.putExtra("usuario", usuario)
            this@PedidoActvity.finish()
            startActivity(intent)
        }
        findViewById<Button>(R.id.btnAnular).isEnabled = pedidoRecibido!!.id!=-1L
        findViewById<Button>(R.id.btnAnular).setOnClickListener{
            pedidoViewModel.anular(usuario.id!!, pedidoRecibido.id)

            val intent = Intent(this@PedidoActvity, MesasPedidosActivity::class.java )
            intent.putExtra("usuario", usuario)
            this@PedidoActvity.finish()
            startActivity(intent)
        }

    }

    override fun onItemClick(productosDevueltos: MutableList<ProductosPedido>) {
        precioTotal=0F

        var precioProductosDevueltos:Float = 0F
        var productosViejosBool:Boolean = false

        for (productoNuevo in productosDevueltos){
            if (productoNuevo.productoPedidoid!=null){
                productosViejosBool=true
            }
            val cantidad: Int? = productoNuevo.cantidad
            val preci: Float? = productoNuevo.producto?.precio
            precioProductosDevueltos += cantidad!! * preci!!
        }

        if (productosViejosBool==true){
            precioProductosPedidoRecibido=precioProductosDevueltos
            productosViejos=productosDevueltos
        }else{
            precioProductosNuevos=precioProductosDevueltos
            productosNuevosLista=productosDevueltos

        }
        precioTotal=precioProductosNuevos+precioProductosPedidoRecibido

        findViewById<TextView>(R.id.txtTotal).setText("Total: "+(precioTotal)+" €")
    }

    override fun onBackPressed()
    {
        val productosEnviar:List<ProductosPedido> = productosNuevosLista.filterNot { it.cantidad == 0 }.toMutableList()
        val intent = Intent(this@PedidoActvity, CategoriaProductoActivity::class.java )
        intent.putExtra("usuario", usuario)
        intent.putExtra("mesa", mesa)
        intent.putExtra("productosActualizados", ArrayList(productosEnviar))
        this@PedidoActvity.finish()
        startActivity(intent)
    }
}