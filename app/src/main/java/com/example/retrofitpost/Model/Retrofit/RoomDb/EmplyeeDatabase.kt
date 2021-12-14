package com.example.retrofitpost.Model.Retrofit.RoomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Emplyee::class),version = 1,exportSchema = false)

abstract class EmplyeeDatabase :RoomDatabase(){
    abstract fun getEmplyeeDao():EmplyeeDao

     companion object{
        @Volatile
        private var instance: EmplyeeDatabase? = null
         private val LOCK = Any()
         operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
             instance ?: createDatabase(context).also {
                 instance = it
             }
         }

         private fun createDatabase(context: Context) =
             Room.databaseBuilder(context.applicationContext, EmplyeeDatabase::class.java, "emplyee_database").build()
//        private var INSTANCE :EmplyeeDatabase? = null
//         fun getDatabase(context:Context): EmplyeeDatabase{
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    EmplyeeDatabase::class.java,
//                    "emplyee_database").build()
//                INSTANCE=instance
//                instance
//            }
//        }
    }
}