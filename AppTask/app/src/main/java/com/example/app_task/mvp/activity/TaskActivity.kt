package com.example.app_task.mvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_task.mvp.presenter.TaskPresenter
import com.example.app_task.mvp.view.TaskView

class TaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TaskPresenter(TaskView(this))
    }
}