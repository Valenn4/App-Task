package com.example.app_task.mvp.view

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.app_task.databinding.ActivityTaskBinding
import com.example.app_task.mvp.contract.TaskContract

class TaskView(private var activity: Activity): TaskContract.View {
    private val binding : ActivityTaskBinding = ActivityTaskBinding.inflate(activity.layoutInflater)
    private val sharedPreferences: SharedPreferences = activity.getSharedPreferences("recycler", Context.MODE_PRIVATE)

    init {
        activity.setContentView(binding.root)
    }

    override fun inputFocus(){
        binding.inputDescription.setOnFocusChangeListener { view, b ->
            if(!b){
                binding.buttonSaveDescription.visibility = View.INVISIBLE
            } else {
                binding.buttonSaveDescription.visibility = View.VISIBLE
            }
        }
    }
    override fun clearFocus(){
        val view = activity.currentFocus
        view?.clearFocus()

        val imm : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun setFocusInput(){
        binding.inputDescription.requestFocus(0)
    }
    override fun setOnClickTextDescription(function: () -> Unit){
        binding.textDescription.setOnClickListener { function() }
    }
    override fun invisibleTextAndVisibleInput(){
        binding.textDescription.visibility = View.INVISIBLE
        binding.inputDescription.visibility = View.VISIBLE
    }
    override fun visibleTextAndInvisibleInput(){
        binding.textDescription.visibility = View.VISIBLE
        binding.inputDescription.visibility = View.INVISIBLE
    }

    override fun setTitle(){
        binding.titleTask.text = activity.intent.getStringExtra("task_name")
        binding.textDescription.text = sharedPreferences.getString("descriptionsTasks", "")?.split(",")?.get(activity.intent.getIntExtra("task_id", -10)).toString()
        binding.inputDescription.setText(sharedPreferences.getString("descriptionsTasks", "")?.split(",")?.get(activity.intent.getIntExtra("task_id", -10)).toString())
    }
    override fun showDescription(){
        var listDescription = sharedPreferences.getString("descriptionsTasks", "")
        var listConvert = listDescription?.split(",")?.toMutableList()
        binding.textDescription.setText(listConvert?.get(activity.intent.getIntExtra("task_id", -10)))
    }
    override fun saveDescriptionText(){
        var listDescription = sharedPreferences.getString("descriptionsTasks", "")
        var listConvert = listDescription?.split(",")?.toMutableList()
        listConvert?.set(activity.intent.getIntExtra("task_id", -10), binding.inputDescription.text.toString())

        var stringBuilder = StringBuilder()
        listConvert?.forEach { el -> stringBuilder.append("$el,") }
        stringBuilder.substring(0,stringBuilder.length-1)

        val editor = sharedPreferences.edit()
        editor?.putString("descriptionsTasks", stringBuilder.toString())
        editor?.apply()
    }
    override fun setOnClickSaveButton(function: () -> Unit){
        binding.buttonSaveDescription.setOnClickListener { function() }
    }
}