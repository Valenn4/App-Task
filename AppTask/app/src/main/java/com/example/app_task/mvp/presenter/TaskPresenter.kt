package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.TaskContract

class TaskPresenter(private var view: TaskContract.View): TaskContract.Presenter {
    init {
        view.inputFocus()
        view.setTitle()
        view.setOnClickTextDescription { setOnClickTextDescription() }
        view.setOnClickSaveButton { setOnClickSaveButton() }
    }

    override fun setOnClickTextDescription(){
        view.invisibleTextAndVisibleInput()
        view.setFocusInput()
        if(view.descriptionIsNull()){
            view.cleanInput()
        }
    }
    override fun setOnClickSaveButton() {
        view.clearFocus()
        view.saveDescriptionText()
        view.showDescription()
        view.visibleTextAndInvisibleInput()
        if(view.descriptionIsBlank()){
            view.setValueDefaultDescription()
        }
    }
}