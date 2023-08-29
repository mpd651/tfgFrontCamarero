package com.example.tfgfrontcamarero.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgfrontcamarero.data.MesaRepository
import com.example.tfgfrontcamarero.data.model.Mesa
import kotlinx.coroutines.launch

class MesaViewModel :ViewModel() {

    private val repository = MesaRepository()

    val mesasModel = MutableLiveData<List<Mesa>>()

    fun getAllMesas(){
        viewModelScope.launch {
            val result:List<Mesa> = repository.getAllMesas()

            if (result!=null){
                mesasModel.postValue(result)
            }

        }
    }
}