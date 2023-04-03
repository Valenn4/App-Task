package com.example.app_task.mvp.contract

interface TaskContract {

    interface View {
        fun setTitle()
        fun clearFocus()
        fun inputFocus()
        fun setFocusInput()
        fun showDescription()
        fun saveDescriptionText()
        fun setOnClickSaveButton(function: () -> Unit)
        fun setOnClickTextDescription(function: () -> Unit)
        fun invisibleTextAndVisibleInput()
        fun visibleTextAndInvisibleInput()
    }

    interface Presenter {
        fun setOnClickTextDescription()
        fun setOnClickSaveButton()
    }
}