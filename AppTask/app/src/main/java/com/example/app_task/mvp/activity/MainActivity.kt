package com.example.app_task.mvp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_task.mvp.presenter.MainPresenter
import com.example.app_task.mvp.view.MainView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainPresenter(MainView(this))
    }
}