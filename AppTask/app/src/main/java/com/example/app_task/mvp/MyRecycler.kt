package com.example.app_task.mvp
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_task.R
import com.example.app_task.TaskActivity
import com.example.app_task.mvp.contract.MainContract
import com.example.app_task.mvp.presenter.MainPresenter
import com.example.app_task.mvp.view.MainView

class MyRecycler(private var list: List<String>, private var activity: Activity?): RecyclerView.Adapter<MyRecycler.MyHolder>() {

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.task)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        var item = list[position]
        holder.textView.text = item
        holder.itemView.setOnClickListener {
            val intent : Intent = Intent(activity, TaskActivity::class.java)
            intent.putExtra("task_name", item)
            intent.putExtra("task_id", position)
            activity?.startActivity(intent)
        }


    }

}