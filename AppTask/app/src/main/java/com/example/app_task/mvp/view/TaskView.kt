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

    companion object {
        private const val NULL_DESCRIPTION = "There is no description"
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
    override fun cleanInput(){
        binding.inputDescription.text.clear()
    }
    override fun openKeyboard(){
        val imm : InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.inputDescription, InputMethodManager.SHOW_IMPLICIT)
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

    }
    override fun setDescription(description: String){
        binding.textDescription.text = description
        binding.inputDescription.setText(description)
    }
    override fun getValueInput(): String {
        return binding.inputDescription.text.toString()
    }
    override fun getTitleIntent(): String{
        return activity.intent.getStringExtra("task_name").toString()
    }
    override fun descriptionIsNull(): Boolean{
        return binding.textDescription.text.equals(NULL_DESCRIPTION)
    }
    override fun descriptionIsBlank(): Boolean{
        return binding.inputDescription.text.isBlank()
    }
    override fun setOnClickSaveButton(function: () -> Unit){
        binding.buttonSaveDescription.setOnClickListener { function() }
    }

}