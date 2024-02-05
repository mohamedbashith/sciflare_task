package com.example.sciflare_task.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sciflare_task.model.Model
import kotlin.concurrent.Volatile


@Database(entities = [Model::class], version = 5)
abstract class ApiDatabase : RoomDatabase() {
    abstract fun detailsDao(): Dao
    internal class PopulateDbAsyn(detailsDatabase: ApiDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val detailsDao: Dao

        init {
            detailsDao = detailsDatabase!!.detailsDao()
        }


        override fun doInBackground(vararg p0: Void?): Void? {
            detailsDao.deleteAll()
            return null
        }
    }

    companion object {
        private const val DATABASE_NAME = "Details"

        @Volatile
        var INSTANCE: ApiDatabase? = null
        fun getInstance(context: Context?): ApiDatabase {
            if (INSTANCE == null) {
                synchronized(ApiDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context!!,
                            ApiDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .addCallback(callback)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        var callback: Callback = object : Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsyn(INSTANCE)
            }
        }
    }
}