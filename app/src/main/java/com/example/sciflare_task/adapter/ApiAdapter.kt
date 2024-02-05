package com.example.sciflare_task.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sciflare_task.databinding.ItempicBinding
import com.example.sciflare_task.model.Model

class ApiAdapter(private val context: Context, details: List<Model>) :
    RecyclerView.Adapter<ApiAdapter.ApiViewHolder>() {
    private var details: List<Model>

    init {
        this.details = details
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
        return ApiViewHolder(
            ItempicBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        val userDetails: Model = details[position]
        holder.name.text = userDetails.name
        holder.email.text = userDetails.email
        holder.phone.text = userDetails.mobile
        holder.gender.text = userDetails.gender
    }

    override fun getItemCount(): Int {
        return details.size
    }

    fun getAllDatas(details: List<Model>) {
        this.details = details
    }

    class ApiViewHolder(binding:ItempicBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.name
        val email = binding.email
        val gender = binding.gender
        val  phone = binding.phone

    }
}