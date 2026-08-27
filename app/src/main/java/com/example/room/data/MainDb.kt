package com.example.room.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Создаем абстрактный класс

@Database(
    entities = [
        NameEntity :: class
    ],
    version = 1) //информируем рум что это класс нашей базы данных
//база должна знать какие в ней сущности
//version = 1 на случай, если у нас будут менятся базы(например добавим новые поля)

abstract class MainDb : RoomDatabase(){
    //: RoomDatabase() - наследуемся от класса чтобы иметь все функции как App от Application

    abstract val dao: Dao //создаем переменную, чтобы через MainDb обратиться к методам Dao
    //в будущем на вьюмоделе мы это реализуем (удаление, добавление, редактирование)
    // например MainDb.dao.deleteItem() - удалить заметку/

    companion object { //в отличие от обьекта не имеет имени, он тоже синглтон(создасться 1 раз), как и обьект
        fun createDatabase(context : Context) : MainDb { //context - это мостик между нашим приложением и Android
            return Room.databaseBuilder(
                context,
                MainDb :: class.java,
               "room.db",
            ).build()
        }
    }
}