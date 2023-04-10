package com.example.app_task.mvp.model

import android.app.Activity
import android.content.ContentValues
import com.example.app_task.mvp.contract.MainContract
import com.example.app_task.mvp.database.SQLiteHelper

class MainModel(activity: Activity): MainContract.Model {
    private val database = SQLiteHelper(activity)

    override fun getTasks(): List<String> {
        val cursor = database.writableDatabase.rawQuery("select * from task order by id DESC", null)
        val list = mutableListOf<String>()
        if(cursor.moveToFirst()){
            do {
                list.add(cursor.getString(1))
            } while (cursor.moveToNext())
        }
        return list
    }

    override fun addTask(title: String) {
        val values = ContentValues()
        values.put("title", title)
        values.put("description", NULL_DESCRIPTION)
        database.writableDatabase.insert("task", null, values)
    }

    override fun deleteTask(title: String){
        database.writableDatabase.execSQL("delete from task where title = '$title'")
    }

    companion object {
        private const val NULL_DESCRIPTION = "There is no description"
    }
}