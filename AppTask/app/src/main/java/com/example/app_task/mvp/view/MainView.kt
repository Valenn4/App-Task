package com.example.app_task.mvp.view

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_task.databinding.ActivityMainBinding
import com.example.app_task.mvp.recycler.MyRecycler
import com.example.app_task.mvp.contract.MainContract
import com.example.app_task.mvp.model.MainModel
import com.example.app_task.mvp.presenter.MainPresenter

class MainView(activity: Activity): ActivityView(activity), MainContract.View {
    private val binding : ActivityMainBinding = ActivityMainBinding.inflate(activity.layoutInflater)
    init {
        activity.setContentView(binding.root)
    }
    companion object {
        private const val TOAST_ENTER_MESSAGE = "Enter a text"
        private const val TOAST_REPEAT_TITLE = "A task with the same name already exists"
    }
    override fun getValueInput(): String {
        return binding.editTextTextPersonName.text.toString()
    }

    override fun inization(function: () -> Unit){
        function()
    }
    override fun getValueInputIsEmpty(): Boolean{
        return binding.editTextTextPersonName.text.isEmpty()
    }
    override fun showMessageToast(){
        Toast.makeText(activity, TOAST_ENTER_MESSAGE, Toast.LENGTH_SHORT).show()
    }
    override fun showToastRepeatTitle(){
        Toast.makeText(activity, TOAST_REPEAT_TITLE, Toast.LENGTH_SHORT).show()
    }
    override fun loadRecycler(list: List<String>){
        var recycler = binding.recycler
        recycler.visibility = View.VISIBLE
        recycler.adapter = MyRecycler(list, activity)
        recycler.layoutManager = LinearLayoutManager(activity)
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
    override fun clearFocusInput(){
        binding.editTextTextPersonName.text.clear()
        binding.editTextTextPersonName.clearFocus()
        val view = activity?.currentFocus
        view?.clearFocus()
        val imm : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    override fun setOnClickAddButton(function: () -> Unit){
        binding.buttonAddTask.setOnClickListener { function() }
    }
}