package com.example.app_task.mvp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertDialogDeleteTask(private var function: Unit): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(MESSAGE)
        builder.setPositiveButton(POSITIVE, DialogInterface.OnClickListener {
                dialogInterface, i ->
            function
        })
        builder.setNegativeButton(NEGATIVE, DialogInterface.OnClickListener {
                dialogInterface, i ->
        })
        builder.show()
        return builder.create()
    }

    companion object {
        private const val MESSAGE = "Are you sure to delete the task?"
        private const val POSITIVE = "Delete"
        private const val NEGATIVE = "None"
    }
}