package com.example.sciflare_task.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.sciflare_task.model.Model
import com.example.sciflare_task.repo.Repository


class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: Repository
    var getAllDetails: LiveData<List<Model>>

    init {
        repository = Repository(application)
        getAllDetails = repository.getAllDetails
    }

    fun insert(details: List<Model?>?) {
        repository.insert(details)
    }

    val allDetails: LiveData<List<Model>>
        get() = getAllDetails
}
