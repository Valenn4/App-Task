package com.example.app_task.mvp.model

import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.service.quicksettings.Tile
import com.example.app_task.mvp.contract.TaskContract
import com.example.app_task.mvp.database.SQLiteHelper

class TaskModel(activity: Activity): TaskContract.Model {
    private val database = SQLiteHelper(activity)
    override fun getDescription(title: String): String {
        val cursor: Cursor = database.writableDatabase.rawQuery("select * from task where title = '$title'", null)
        if(cursor.moveToFirst()){
            return cursor.getString(2)
        }
        return "null"
    }
    override fun saveDescription(description: String, title: String){
        val value = ContentValues().apply {
            put("description", description)
        }
        database.writableDatabase.update("task", value, "title='$title'", null)
    }
}