package com.example.app_task.mvp.contract

interface MainContract {
    interface View {
        fun conditionSharedPreferences(): Boolean
        fun loadRecycler()
        fun invisibleText()
        fun visibleText()
        fun invisibleRecycler()
        fun addFirstNewTask()
        fun addNewTask()
        fun setOnClickDeleteButton(function: () -> Unit)
        fun inization(function: () -> Unit)
        fun deleteTask(position: Int)
    }

    interface Presenter {
        fun inization()
        fun setOnClickAddButton()
        fun clickDeleteButton(position: Int)
    }
}