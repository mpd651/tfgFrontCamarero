package com.example.tfgfrontcamarero.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgfrontcamarero.data.ProductosPedidoRepository
import com.example.tfgfrontcamarero.data.dto.ProductosPedidoDto
import kotlinx.coroutines.launch

class ProductosPedidosViewModel : ViewModel() {
    private val repository = ProductosPedidoRepository()

    val productosPedidosModelDto = MutableLiveData<List<ProductosPedidoDto>>()

    fun getProductosPedidoByPedidoId(pedidoId: Long){
        viewModelScope.launch {
            val result:List<ProductosPedidoDto> = repository.getProductosPedidoByPedidoId(pedidoId)
            productosPedidosModelDto.postValue(result)

        }
    }

}