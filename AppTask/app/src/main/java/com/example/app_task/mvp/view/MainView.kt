package com.example.app_task.mvp.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_task.databinding.ActivityMainBinding
import com.example.app_task.mvp.MyRecycler
import com.example.app_task.mvp.contract.MainContract

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
        return sharedPreferences.getStringSet("listTask", mutableSetOf())!!.isEmpty()
    }
    override fun loadRecycler(){
        var recycler = binding.recycler
        recycler.adapter = MyRecycler(sharedPreferences.getStringSet("listTask", mutableSetOf())!!.toMutableList(), activity)
        recycler.layoutManager = LinearLayoutManager(activity)
    }
    override fun onClickItemRecycler(position: Int){
        var listTask = sharedPreferences.getStringSet("listTask", mutableSetOf())?.toMutableList()
        listTask?.remove(listTask?.get(position))

        val editor = sharedPreferences.edit()
        editor?.putStringSet("listTask", listTask?.toMutableSet())
        editor?.apply()
    }
    override fun invisibleText(){
        binding.textView.visibility = View.INVISIBLE
    }
    override fun visibleText(){
        binding.textView.visibility = View.VISIBLE
    }
    override fun addNewTask(){
        var list : MutableList<String> = sharedPreferences.getStringSet("listTask", mutableSetOf())!!.toMutableList()
        list.add(binding.editTextTextPersonName.text.toString())
        val editor = sharedPreferences.edit()
        editor?.putStringSet("listTask", list.toMutableSet())
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