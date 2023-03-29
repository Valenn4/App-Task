package com.example.app_task.mvp.contract

interface MainContract {
    interface View {
        fun createShared()
        fun conditionSharedPreferences(): Boolean
        fun loadRecycler()
        fun InvisibleText()
        fun addNewTask()
        fun setOnClickAddButton(function: () -> Unit)
        fun inization(function: () -> Unit)
    }

    interface Model

    interface Presenter {
        fun inization()
        fun setOnClickAddButton()
    }
}