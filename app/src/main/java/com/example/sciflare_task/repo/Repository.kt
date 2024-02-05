package com.example.sciflare_task.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.sciflare_task.db.Dao
import com.example.sciflare_task.db.ApiDatabase
import com.example.sciflare_task.model.Model


class Repository(application: Application?) {
    var detailsDao: Dao
    var getAllDetails: LiveData<List<Model>>
    private val database: ApiDatabase

    init {
        database = ApiDatabase.getInstance(application)
        detailsDao = database.detailsDao()
        getAllDetails = detailsDao.getDetails()
    }

    fun insert(UserDetails: List<Model?>?) {
        InsertAsyncTask(detailsDao).execute(UserDetails)
    }

    val allDetails: LiveData<List<Model>>
        get() = getAllDetails

    private class InsertAsyncTask(userDao: Dao) :
        AsyncTask<List<Model?>?, Void?, Void?>() {
        private val dao: Dao

        init {
            dao = userDao
        }

        override fun doInBackground(vararg p0: List<Model?>?): Void? {
            dao.insert(p0[0])
            return null
        }
    }
}