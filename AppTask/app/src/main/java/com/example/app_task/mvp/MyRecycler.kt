package com.example.app_task.mvp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_task.R

class MyRecycler(private var list: MutableList<String>): RecyclerView.Adapter<MyRecycler.MyHolder>() {

    inner class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
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
        val item = list[position]
        holder.textView.text = item
    }
}