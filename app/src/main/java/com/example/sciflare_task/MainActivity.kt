package com.example.sciflare_task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sciflare_task.adapter.ApiAdapter
import com.example.sciflare_task.databinding.ActivityMainBinding
import com.example.sciflare_task.model.Model
import com.example.sciflare_task.repo.Repository
import com.example.sciflare_task.service.Api
import com.example.sciflare_task.viewModel.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var detailsViewModel: ViewModel? = null
    private lateinit var getDetails: List<Model>
    private var adapter: ApiAdapter? = null
    private var repository: Repository? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository(application)
        getDetails = ArrayList()

        binding.recyclerView.setLayoutManager(GridLayoutManager(this, 1))
        binding.recyclerView.setHasFixedSize(true)

        detailsViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        adapter = ApiAdapter(this, getDetails)
        makeRequest()
        detailsViewModel!!.getAllDetails.observe(this, object : Observer<List<Model>> {

            override fun onChanged(value: List<Model>) {
                binding.recyclerView.setAdapter(adapter)
                adapter!!.getAllDatas(value)
                Log.d("main", "onChanged: $value")
            }
        })
    }

    private fun makeRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://crudcrud.com/api/d7f36d8bafd74e00b08c41caaf6c9772/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api: Api = retrofit.create(Api::class.java)
        val call: Call<List<Model>> = api.getUserDetails()
        call.enqueue(object : Callback<List<Model>> {

            override fun onResponse(call: Call<List<Model>>, response: Response<List<Model>>) {
                if (response.isSuccessful) {
                    repository?.insert(response.body())
                }
            }

            override fun onFailure(call: Call<List<Model>>, t: Throwable) {
                Log.d("main", "onFailure: " + t.message)
            }
        })
    }
}