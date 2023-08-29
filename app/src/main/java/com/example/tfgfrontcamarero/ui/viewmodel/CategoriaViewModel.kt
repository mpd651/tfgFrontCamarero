package com.example.tfgfrontcamarero.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgfrontcamarero.data.CategoriaRepository
import com.example.tfgfrontcamarero.data.model.Categoria
import kotlinx.coroutines.launch

class CategoriaViewModel: ViewModel() {

    private val repository = CategoriaRepository()

    val categoriasModel = MutableLiveData<List<Categoria>>()

    fun getAllCategorias(){
        viewModelScope.launch {
            val result:List<Categoria> = repository.getAllCategorias()
            categoriasModel.postValue(result)

        }
    }

}