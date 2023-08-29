package com.example.tfgfrontcamarero.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgfrontcamarero.data.PedidoRepository
import com.example.tfgfrontcamarero.data.dto.PedidoDto
import com.example.tfgfrontcamarero.data.model.Mesa
import com.example.tfgfrontcamarero.data.model.Pedido
import kotlinx.coroutines.launch

class PedidoViewModel: ViewModel() {

    private val repository = PedidoRepository()

    val pedidoModel = MutableLiveData<Pedido>()
    val pedidoModelDto = MutableLiveData<PedidoDto>()
    val pedidoIdDevuelto = MutableLiveData<Long>()
    val pedidosModelDto = MutableLiveData<List<PedidoDto>>()

    fun getPedidoByMesaId(mesaId: Long){
        viewModelScope.launch {
            val result: PedidoDto? = repository.getPedidoByMesaId(mesaId)
            pedidoModelDto.postValue(result?:null)

        }
    }

    fun getPedidoById(id: Long){
        viewModelScope.launch {
            val result: PedidoDto? = repository.getPedidoById(id)
            pedidoModelDto.postValue(result?:null)

        }
    }

    fun getPedidosActivos(){
        viewModelScope.launch {
            val result: List<PedidoDto> = repository.getPedidosActivos()
            pedidosModelDto.postValue(result?: emptyList<PedidoDto>())

        }
    }

    fun crearPedido(pedido: Pedido, usuarioId: Long){
        viewModelScope.launch {
            val result: Long?=repository.crearPedido(pedido, usuarioId)
            pedidoIdDevuelto.postValue(result?:null)
        }
    }

    fun pagar(usuarioId: Long, pedido: Pedido){
        viewModelScope.launch {
            repository.pagar(usuarioId, pedido)
        }
    }

    fun anular(usuarioId: Long, pedidoId: Long){
        viewModelScope.launch {
            repository.anular(usuarioId, pedidoId)
        }
    }

    fun asignarPedido(pedidoId: Long){
        viewModelScope.launch {
            repository.asignarPedido(pedidoId)
        }
    }

    fun actualizarPedido(pedido: Pedido, usuarioId: Long){
        viewModelScope.launch {
            val result: Long?=repository.actualizarPedido(pedido, usuarioId)
            pedidoIdDevuelto.postValue(result?:null)
        }
    }

}