package com.example.app_task.mvp.contract

interface TaskContract {
    interface Model {
        fun getDescription(title: String): String
        fun saveDescription(description: String, title: String)
    }
    interface View {
        fun clearFocus()
        fun inputFocus()
        fun setFocusInput()
        fun setTitle()
        fun setDescription(description: String)
        fun getTitleIntent(): String
        fun getValueInput(): String
        fun descriptionIsNull(): Boolean
        fun descriptionIsBlank(): Boolean
        fun cleanInput()
        fun openKeyboard()
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