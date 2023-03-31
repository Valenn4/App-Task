package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.MainContract

class MainPresenter(private var view: MainContract.View): MainContract.Presenter {

    init {
        view.inization { inization() }
        view.setOnClickAddButton { setOnClickAddButton() }
        view.setOnClickDeleteButton { setOnClickDeleteButton() }
    }

    override fun inization(){
        if(!view.conditionSharedPreferences()){
            view.invisibleText()
            view.loadRecycler()
        }
    }

    override fun setOnClickAddButton(){
        if(view.conditionSharedPreferences()){
            view.visibleAllDelete()
        }
        view.addNewTask()
        view.loadRecycler()
        view.invisibleText()
    }
    override fun onClickItemRecycler(position: Int){
        view.onClickItemRecycler(position)
        view.loadRecycler()
        if (view.conditionSharedPreferences()){
            view.visibleText()
        }
    }

    override fun setOnClickDeleteButton(){
        view.invisibleAllDelete()
        view.allDeleteTask()
        view.loadRecycler()
        view.visibleText()
    }
}