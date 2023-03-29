package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.MainContract

class MainPresenter(private var view: MainContract.View): MainContract.Presenter {

    init {
        view.inization { inization() }
        view.setOnClickAddButton { setOnClickAddButton() }
    }

    override fun inization(){
        if(!view.conditionSharedPreferences()){
            view.loadRecycler()
            view.InvisibleText()
        }
    }

    override fun setOnClickAddButton(){
        view.addNewTask()
    }
}