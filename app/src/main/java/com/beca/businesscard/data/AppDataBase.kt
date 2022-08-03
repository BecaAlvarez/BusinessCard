package com.beca.businesscard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


//Gerenciar o BD(observar) e o room
            //Quais entidades no projeto e versão que o bd está utilizando
@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun businessDao(): BusinessCardDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "businesscard_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}