package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.MainContract

class MainPresenter(private var view: MainContract.View): MainContract.Presenter {

    init {
        view.inization { inization() }
        view.setOnClickDeleteButton { setOnClickAddButton() }
    }

    override fun inization(){
        if(!view.conditionSharedPreferences()){
            view.invisibleText()
            view.loadRecycler()
        }
    }

    override fun setOnClickAddButton(){
        if(view.conditionSharedPreferences()){
            view.addFirstNewTask()
        } else {
            view.addNewTask()
        }
        view.loadRecycler()
        view.invisibleText()
    }
    override fun clickDeleteButton(position: Int){
        view.deleteTask(position)
        view.loadRecycler()
        if (view.conditionSharedPreferences()) {
            view.visibleText()
            view.invisibleRecycler()
        }
    }
}