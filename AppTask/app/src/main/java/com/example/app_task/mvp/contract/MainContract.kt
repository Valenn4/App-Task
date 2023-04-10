package com.example.app_task.mvp.contract

interface MainContract {
    interface Model {
        fun getTasks(): List<String>
        fun addTask(title: String)
        fun deleteTask(item: String)
    }
    interface View {
        fun getValueInput(): String
        fun getValueInputIsEmpty(): Boolean
        fun showMessageToast()
        fun showToastRepeatTitle()
        fun loadRecycler(list: List<String>)
        fun invisibleText()
        fun visibleText()
        fun invisibleRecycler()
        fun clearFocusInput()
        fun setOnClickAddButton(function: () -> Unit)
        fun inization(function: () -> Unit)
    }

    interface Presenter {
        fun inization()
        fun setOnClickAddButton()
        fun clickDeleteButton(item: String)
    }
}