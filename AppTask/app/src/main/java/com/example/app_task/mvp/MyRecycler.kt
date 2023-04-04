package com.example.app_task.mvp
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_task.R
import com.example.app_task.TaskActivity
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
        holder.buttonDelete.id = position
        holder.buttonDelete.setOnClickListener {
            val presenter = MainPresenter(MainView(activity!!))
            presenter.clickDeleteButton(position)
        }
        holder.itemView.setOnClickListener {
            val intent : Intent = Intent(activity, TaskActivity::class.java)
            intent.putExtra("task_name", item)
            intent.putExtra("task_id", position.toString())
            activity?.startActivity(intent)
        }
    }

}