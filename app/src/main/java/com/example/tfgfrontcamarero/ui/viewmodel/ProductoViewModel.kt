package com.example.tfgfrontcamarero.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgfrontcamarero.data.ProductoRepository
import com.example.tfgfrontcamarero.data.dto.ProductoDto
import kotlinx.coroutines.launch

class ProductoViewModel : ViewModel() {
    private val repository = ProductoRepository()

    val productosModelDto = MutableLiveData<List<ProductoDto>>()

    fun getProductosByCategoria(categoriaId: Long){
        viewModelScope.launch {
            val result:List<ProductoDto> = repository.getProductosByCategoria(categoriaId)
            productosModelDto.postValue(result)

        }
    }

}