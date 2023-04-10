package com.example.app_task.mvp.presenter

import com.example.app_task.mvp.contract.TaskContract

class TaskPresenter(private var view: TaskContract.View, private var model: TaskContract.Model): TaskContract.Presenter {
    init {
        view.inputFocus()
        view.setTitle()
        view.setDescription(model.getDescription(view.getTitleIntent()))
        view.setOnClickTextDescription { setOnClickTextDescription() }
        view.setOnClickSaveButton { setOnClickSaveButton() }
    }


    override fun setOnClickTextDescription(){
        view.invisibleTextAndVisibleInput()
        view.setFocusInput()
        view.openKeyboard()
        if(view.descriptionIsNull()){
            view.cleanInput()
        }
    }
    override fun setOnClickSaveButton() {
        model.saveDescription(view.getValueInput(), view.getTitleIntent())
        view.setDescription(view.getValueInput())
        view.clearFocus()
        view.visibleTextAndInvisibleInput()
        if(view.descriptionIsBlank()){
            model.saveDescription(NULL_DESCRIPTION, view.getTitleIntent())
            view.setDescription(NULL_DESCRIPTION)
        }
    }

    companion object {
        private const val NULL_DESCRIPTION = "There is no description"
    }
}