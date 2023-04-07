package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.MainContract

class MainPresenter(private var view: MainContract.View): MainContract.Presenter {

    init {
        view.inization { inization() }
        view.setOnClickAddButton { setOnClickAddButton() }
    }

    override fun inization(){
        if(!view.conditionSharedPreferences()){
            view.invisibleText()
            view.loadRecycler()
        }
    }

    override fun setOnClickAddButton(){
        if(!view.getValueInputIsEmpty()) {
            if (view.conditionSharedPreferences()) {
                view.addFirstNewTask()
            } else {
                view.addNewTask()
            }
            view.loadRecycler()
            view.invisibleText()
        } else {
            view.showMessageToast()
        }
    }
    override fun clickDeleteButton(position: Int){
        view.alertDeleteTask(position)
        if (view.conditionSharedPreferences()) {
            view.visibleText()
            view.invisibleRecycler()
        }
    }
}