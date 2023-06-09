package com.example.app_task.mvp.recycler
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app_task.R
import com.example.app_task.mvp.activity.TaskActivity
import com.example.app_task.mvp.model.MainModel
import com.example.app_task.mvp.presenter.MainPresenter
import com.example.app_task.mvp.view.MainView

class MyRecycler(private var list: List<String>, private var activity: Activity?): RecyclerView.Adapter<MyRecycler.MyHolder>() {

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val textView : TextView = itemView.findViewById(R.id.task)
        val buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDeleteTask)
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
        holder.buttonDelete.setOnClickListener {
            val presenter = MainPresenter(MainView(activity!!), MainModel(activity!!))
            presenter.clickDeleteButton(item)
        }

        holder.itemView.setOnClickListener {
            val intent : Intent = Intent(activity, TaskActivity::class.java)
            intent.putExtra("task_name", item)
            activity?.startActivity(intent)
        }
    }

}