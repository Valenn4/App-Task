package com.example.app_task.mvp.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_task.databinding.ActivityMainBinding
import com.example.app_task.mvp.MyRecycler
import com.example.app_task.mvp.contract.MainContract
import java.io.File
import java.io.FileOutputStream

class MainView(activity: Activity): ActivityView(activity), MainContract.View {
    private val binding : ActivityMainBinding = ActivityMainBinding.inflate(activity.layoutInflater)
    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("recycler", Context.MODE_PRIVATE)

    init {
        activity.setContentView(binding.root)
    }

    override fun inization(function: () -> Unit){
        function()
    }
    override fun conditionSharedPreferences(): Boolean{
        return sharedPreferences.getString("listTask", "")!!.isEmpty()
    }
    override fun loadRecycler(){
        var recycler = binding.recycler
        var list: String = sharedPreferences.getString("listTask", "")!!
        recycler.adapter = MyRecycler(list.split(",").reversed(), activity)
        recycler.layoutManager = LinearLayoutManager(activity)
    }
    override fun onClickItemRecycler(position: Int){
        var listTask = sharedPreferences.getString("listTask", "")
        var listConvert = listTask?.split(",")?.toMutableList()
        listConvert?.remove(listConvert[position])

        var stringBuilder = StringBuilder()
        listConvert?.forEach { el -> stringBuilder.append("$el,") }
        stringBuilder.substring(0,stringBuilder.length-1)

        val editor = sharedPreferences.edit()
        editor?.putString("listTask", stringBuilder.toString())
        editor?.apply()
    }
    override fun invisibleText(){
        binding.textView.visibility = View.INVISIBLE
    }
    override fun visibleText(){
        binding.textView.visibility = View.VISIBLE
    }
    override fun addFirstNewTask(){
        val editor = sharedPreferences.edit()
        editor?.putString("listTask", binding.editTextTextPersonName.text.toString())
        editor?.putString("descriptionsTasks", "Sin descripcion")
        editor?.apply()
    }
    override fun addNewTask(){
        var list : String = sharedPreferences.getString("listTask", "")!!
        list += ","+binding.editTextTextPersonName.text.toString()
        val editor = sharedPreferences.edit()
        editor?.putString("listTask", list)
        editor?.putString("descriptionsTasks", "Sin descripcion,")
        editor?.apply()
    }
    override fun allDeleteTask(){
        sharedPreferences.edit().clear().apply()
    }
    override fun visibleAllDelete(){
        binding.allDelete.visibility = View.VISIBLE
    }
    override fun invisibleAllDelete(){
        binding.allDelete.visibility = View.INVISIBLE
    }
    override fun setOnClickAddButton(function: () -> Unit){
        binding.buttonAddTask.setOnClickListener { function() }
    }
    override fun setOnClickDeleteButton(function: () -> Unit){
        binding.allDelete.setOnClickListener { function() }
    }
}