package com.example.room.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "name_table") //аннотация для room - это будет теблицой
data class NameEntity (
    val name: String,
    @PrimaryKey(autoGenerate = true) //аннотация для роом - это id,а не просто число. autoGenerate = true -они будут генерироваться авто
    val id: Int ? = null, //чтобы отличать заметки. Ставим заглушку: (? = null) т.к компилятор будет ждать конкретное число
    //а его генерирует room внутри себя
)