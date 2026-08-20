package com.example.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao //теперь компилятор знал что это Дао
interface Dao {  //Для работы с запросами. для отправки запросов в базу данных

    @Insert(onConflict = OnConflictStrategy.REPLACE) //это аннатация. Для того чтобы мы что-то записывали в базу данных
//onConflict = OnConflictStrategy.REPLACE - в случае изменения заметки id будут одинаковы, так мы решаем проблемму конфликта одинаковых id
    suspend fun insertItem(nameEntity: NameEntity) //передаем в базу обьект (текущую заметку)
    //suspend - снижает нагрузку со смартфона. Пока идет загрузка из базы паралельно отрисовывается, например интерфейс


    @Delete() //Для удаления из базы
    suspend fun deleteItem(nameEntity: NameEntity)



    @Query("SELECT * FROM name_table") //Запроос к базе Выбери все(*) из(FROM) нашей таблицы
    fun getAllItems() : Flow<List<NameEntity>> //не suspend потому что и так поток и так разгружает

}