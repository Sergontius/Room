package com.example.room

import android.app.Application
import com.example.room.data.MainDb

class RoomApp : Application() {
    val database by lazy { //инициализируем базу данных, только при обращении к ней если пользоваться приложением, но не обращаться база не создастся
        MainDb.createDatabase(this) //this - позвошляет обратиться напрямую к классу
    }
}