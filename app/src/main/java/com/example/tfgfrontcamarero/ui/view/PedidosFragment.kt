package com.example.tfgfrontcamarero.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.tfgfrontcamarero.R
import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Usuario
import com.example.tfgfrontcamarero.ui.viewmodel.PedidoViewModel

class PedidosFragment : Fragment() {

    private lateinit var listView: ListView
    private val pedidoViewModel: PedidoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pedidos_fragment_layout, container, false)
        listView = rootView.findViewById(R.id.lstPedidos)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun updatePedidoList(pedidos: List<PedidoDto>) {
        val datosLista = mutableListOf<String>()
        for (pedidoDto in pedidos) {
            datosLista.add("Mesa "+pedidoDto.mesa.numeroMesa)
        }

        var arrayAdapter : ArrayAdapter<String> = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, datosLista)
        listView.adapter = arrayAdapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), CategoriaProductoActivity::class.java )
            pedidoViewModel.asignarPedido(pedidos[position].id)
            intent.putExtra("pedido", pedidos[position])
            intent.putExtra("usuario", arguments?.getSerializable(USUARIO) )
            startActivity(intent)
        }
    }

    companion object {
        private const val USUARIO = "usuario"

        fun newInstance(usuario: Usuario): PedidosFragment{
            val fragment = PedidosFragment()
            val args = Bundle()
            args.putSerializable(USUARIO, usuario)
            fragment.arguments = args
            return fragment
        }
    }
}