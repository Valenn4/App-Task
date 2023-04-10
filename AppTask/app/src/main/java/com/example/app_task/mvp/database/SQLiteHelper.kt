package com.example.app_task.mvp.database

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteHelper(activity: Activity): SQLiteOpenHelper(activity, "task.db", null, 2) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table task(\n" +
                "\tid integer primary key autoincrement,\n" +
                "\ttitle text,\n" +
                "\tdescription text\n" +
                ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}