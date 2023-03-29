package com.example.app_task.mvp.view

import android.app.Activity
import java.lang.ref.WeakReference

open class ActivityView(activity: Activity) {
    private var refActivity : WeakReference<Activity> = WeakReference(activity)

    val activity: Activity?
        get() = refActivity.get()
}