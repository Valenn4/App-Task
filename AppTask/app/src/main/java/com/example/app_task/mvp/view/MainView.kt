package com.example.app_task.mvp.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_task.R
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
        return sharedPreferences.getString("listTask", "")!!.isEmpty()
    }
    override fun loadRecycler(){
        var recycler = binding.recycler
        recycler.visibility = View.VISIBLE
        var list: String = sharedPreferences.getString("listTask", "")!!
        recycler.adapter = MyRecycler(list.split(","), activity)
        recycler.layoutManager = LinearLayoutManager(activity)
    }
    override fun onClickItemRecycler(position: Int){
        var listTask = sharedPreferences.getString("listTask", "")
        var listConvert = listTask?.split(",")?.toMutableList()
        listConvert?.remove(listConvert[position])
        var stringBuilder = StringBuilder()
        listConvert?.forEach {
                el -> if(el == listConvert[listConvert.size-1]){
                        stringBuilder.append(el)
                } else {
                    stringBuilder.append("$el,")
                }
        }
        val editor = sharedPreferences.edit()
        editor?.putString("listTask", stringBuilder.toString())
        editor?.apply()

        var listDescription = sharedPreferences.getString("descriptionsTasks", "")
        var listConvertDescriptions = listDescription?.split(",")?.toMutableList()
        listConvertDescriptions?.remove(listConvertDescriptions[position])
        var stringBuilderDescription = StringBuilder()
        listConvertDescriptions?.forEach {
                el -> if(el == listConvertDescriptions[listConvertDescriptions.size-1]){
            stringBuilderDescription.append(el)
        } else {
            stringBuilderDescription.append("$el,")
        }
        }
        val editorDescription = sharedPreferences.edit()
        editorDescription?.putString("descriptionsTasks", stringBuilderDescription.toString())
        editorDescription?.apply()

    }
    override fun invisibleRecycler(){
        binding.recycler.visibility = View.INVISIBLE
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
        binding.editTextTextPersonName.text.clear()
        binding.editTextTextPersonName.clearFocus()
    }
    override fun addNewTask(){
        var list : String = sharedPreferences.getString("listTask", "")!!
        list = binding.editTextTextPersonName.text.toString()+","+list
        val editor = sharedPreferences.edit()
        editor?.putString("listTask", list)
        editor?.putString("descriptionsTasks", "Sin descripcion,")
        editor?.apply()
        binding.editTextTextPersonName.text.clear()
        binding.editTextTextPersonName.clearFocus()
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
    override fun setOnClickAllDeleteButton(function: () -> Unit){
        binding.allDelete.setOnClickListener { function() }
    }
}