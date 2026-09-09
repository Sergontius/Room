package com.example.room.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.room.RoomApp
import com.example.room.data.MainDb
import com.example.room.data.NameEntity
import kotlinx.coroutines.launch

class MainViewModel(
    val database : MainDb
) : ViewModel() {

    val itemList = database.dao.getAllItems()

    var nameEntity : NameEntity? = null

    var newText = mutableStateOf("")

    fun deleteItem(
        nameEntity: NameEntity //передем сущность заметки
    ){  //viewModelScope.launch - создаем область для работы корутин. .launch - как кнопка старт для создания
        viewModelScope.launch {
            database.dao.deleteItem(nameEntity)
        }
    }

    fun insetItem() {
        viewModelScope.launch {
            val itemEntity = nameEntity?.copy(name = newText.value) ?: NameEntity(name = newText.value)
            database.dao.insertItem(itemEntity)
            nameEntity = null  //иначе будет работать только редактирование заметки
            newText.value = "" //при создании заметки текст на эране сброситься
        }
    }

    companion object {
        val factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras,
            ): T {
                val database = checkNotNull(extras[APPLICATION_KEY] as RoomApp).database

                return MainViewModel(database) as T
            }
        }
    }
}
