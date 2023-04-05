package com.example.app_task.mvp.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
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

    companion object {
        private const val NULL_DESCRIPTION = "There is no description"
        private const val NULL_DESCRIPTION2 = "There is no description,"
        private const val TASKS_LIST = "listTask"
        private const val DESCRIPTIONS_LIST = "descriptionsList"
    }
    private fun applyChangeToTaskList(listTask: String, listDescriptions: String){
        val editor = sharedPreferences.edit()
        editor?.putString("listTask", listTask)
        editor?.putString("descriptionsTasks", listDescriptions)
        editor?.apply()
    }
    private fun getListTasksOrDescriptions(list: String): MutableList<String>{
        var list = sharedPreferences.getString(list, "")
        return list?.split(",")?.toMutableList()!!
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
        recycler.adapter = MyRecycler(getListTasksOrDescriptions(TASKS_LIST), activity)
        recycler.layoutManager = LinearLayoutManager(activity)
    }
    override fun deleteTask(position: Int){
        val listTasks = getListTasksOrDescriptions(TASKS_LIST)
        listTasks?.remove(listTasks[position])
        var stringBuilder = StringBuilder()
        listTasks?.forEach {
                el -> if(el == listTasks[listTasks.size-1]){
                        stringBuilder.append(el)
                } else {
                    stringBuilder.append("$el,")
                }
        }
        val listDescription = getListTasksOrDescriptions(DESCRIPTIONS_LIST)
        listDescription.remove(listDescription[position])
        val stringBuilderDescription = StringBuilder()
        listDescription?.forEach {
                el -> if(el == listDescription[listDescription.size-1]){
            stringBuilderDescription.append(el)
        } else {
            stringBuilderDescription.append("$el,")
        }
        }
        applyChangeToTaskList(stringBuilder.toString(), stringBuilderDescription.toString())
    }
    override fun invisibleRecycler(){
        binding.recycler.visibility = View.INVISIBLE
    }
    override fun invisibleText(){
        binding.textNullTask.visibility = View.INVISIBLE
    }
    override fun visibleText(){
        binding.textNullTask.visibility = View.VISIBLE
    }
    override fun addFirstNewTask(){
        applyChangeToTaskList(binding.editTextTextPersonName.text.toString(), NULL_DESCRIPTION)

        binding.editTextTextPersonName.text.clear()
        val view = activity?.currentFocus
        view?.clearFocus()
        val imm : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun addNewTask(){
        var list : String = sharedPreferences.getString("listTask", "")!!
        list = binding.editTextTextPersonName.text.toString()+","+list
        applyChangeToTaskList(list, NULL_DESCRIPTION2)

        binding.editTextTextPersonName.text.clear()
        val view = activity?.currentFocus
        view?.clearFocus()
        val imm : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun setOnClickDeleteButton(function: () -> Unit){
        binding.buttonAddTask.setOnClickListener { function() }
    }
}