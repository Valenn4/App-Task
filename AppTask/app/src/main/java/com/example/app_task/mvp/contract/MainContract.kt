package com.example.app_task.mvp.contract

interface MainContract {
    interface View {
        fun conditionSharedPreferences(): Boolean
        fun loadRecycler()
        fun invisibleText()
        fun visibleText()
        fun addFirstNewTask()
        fun addNewTask()
        fun setOnClickAddButton(function: () -> Unit)
        fun setOnClickDeleteButton(function: () -> Unit)
        fun inization(function: () -> Unit)
        fun onClickItemRecycler(position: Int)
        fun allDeleteTask()
        fun visibleAllDelete()
        fun invisibleAllDelete()
    }

    interface Presenter {
        fun inization()
        fun setOnClickAddButton()
        fun onClickItemRecycler(position: Int)
        fun setOnClickDeleteButton()
    }
}