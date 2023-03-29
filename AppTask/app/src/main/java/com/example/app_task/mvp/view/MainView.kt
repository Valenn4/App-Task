package com.example.app_task.mvp.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.core.view.isEmpty
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_task.databinding.ActivityMainBinding
import com.example.app_task.mvp.MyRecycler
import com.example.app_task.mvp.contract.MainContract

class MainView(activity: Activity): ActivityView(activity), MainContract.View {
    private val binding : ActivityMainBinding = ActivityMainBinding.inflate(activity.layoutInflater)
    init {
        activity.setContentView(binding.root)
    }

    override fun inization(function: () -> Unit){
        function()
    }

    override fun createShared(){
       var sharedPreferences = activity?.getSharedPreferences("recycler", Context.MODE_PRIVATE)
        sharedPreferences?.edit()?.clear()?.apply()
        /*var
         var sharedPreferences = activity?.getSharedPreferences("recycler", Context.MODE_PRIVATE)
         val editor = sharedPreferences?.edit()
         editor?.putStringSet("list", mutableSetOf<String>("Talleres", "Belgrano", "Instituto"))
         editor?.apply()

          */
    }

    override fun conditionSharedPreferences(): Boolean{
        var sharedPreferences = activity?.getSharedPreferences("recycler", Context.MODE_PRIVATE)
        return sharedPreferences?.getStringSet("list", mutableSetOf())!!.isEmpty()
    }

    override fun loadRecycler(){
        var sharedPreferences = activity?.getSharedPreferences("recycler", Context.MODE_PRIVATE)
        Toast.makeText(activity,"ajaj", Toast.LENGTH_SHORT).show()
        var recycler = binding.recycler
        recycler.adapter = MyRecycler(sharedPreferences?.getStringSet("list", mutableSetOf())!!.toMutableList())
        recycler.layoutManager = LinearLayoutManager(activity)
    }

    override fun InvisibleText(){
        binding.textView.visibility = View.INVISIBLE
    }

    override fun addNewTask(){
        var sharedPreferences = activity?.getSharedPreferences("recycler", Context.MODE_PRIVATE)
        val list = sharedPreferences?.getStringSet("list", mutableSetOf())
        list?.add(binding.editTextTextPersonName.text.toString())
        sharedPreferences?.edit()?.putStringSet("list", list)?.apply()

        activity?.recreate()
    }

    override fun setOnClickAddButton(function: () -> Unit){
        binding.buttonAddTask.setOnClickListener { function() }
    }
}