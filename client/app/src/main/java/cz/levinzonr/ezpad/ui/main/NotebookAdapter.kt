package cz.levinzonr.ezpad.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Notebook
import kotlinx.android.synthetic.main.item_notebook.view.*

class NotebookAdapter(private val listener: ItemListener) : RecyclerView.Adapter<NotebookAdapter.ViewHolder>() {

    interface ItemListener {
        fun onNotebookClicked(notebook: Long)
        fun onEditNotebook(notebook: Notebook)
        fun onDeleteNotebook(notebook: Notebook)
    }

    var items = ArrayList<Notebook>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_notebook, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.notebookNameTextView.text = items[position].name
        holder.view.notesCountTextView.text = "Notes count: ${items[position].notesCount}"
        holder.view.setOnClickListener { listener.onNotebookClicked(items[position].id!!) }

        holder.view.buttonDelete.setOnClickListener { listener.onDeleteNotebook(items[position])}
        holder.view.buttonEdit.setOnClickListener { listener.onEditNotebook(items[position])}

    }
}