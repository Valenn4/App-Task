package com.example.app_task.mvp.presenter

import android.app.AlertDialog
import com.example.app_task.mvp.contract.MainContract
import kotlin.coroutines.coroutineContext

class MainPresenter(private var view: MainContract.View, private var model: MainContract.Model): MainContract.Presenter {

    init {
        view.inization { inization() }
        view.setOnClickAddButton { setOnClickAddButton() }
    }

    override fun inization(){
        if(model.getTasks().isNotEmpty()){
            view.invisibleText()
            view.loadRecycler(model.getTasks())
        }

    }

    override fun setOnClickAddButton(){
        if(!view.getValueInputIsEmpty()) {
            if(!model.getTasks().contains(view.getValueInput())){
                model.addTask(view.getValueInput())
                view.loadRecycler(model.getTasks())
                view.invisibleText()
                view.clearFocusInput()
            } else {
                view.showToastRepeatTitle()
            }
        } else {
            view.showMessageToast()
        }
    }
    override fun clickDeleteButton(item: String){
            model.deleteTask(item)
            view.loadRecycler(model.getTasks())
            view.invisibleText()
            if(model.getTasks().isEmpty()){
                view.visibleText()
                view.invisibleRecycler()
            }
    }
}