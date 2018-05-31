package cz.levinzonr.ezpad.ui.notelist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Note
import kotlinx.android.synthetic.main.item_note.view.*

class NoteAdapter(val onClick: (Note) -> Unit, val onDelete: (Note)-> Unit) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var items = ArrayList<Note>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    inner class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return  ViewHolder(inflater.inflate(R.layout.item_note, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.noteTitle.text = items[position].title
        holder.view.noteContents.text = items[position].contents
        holder.view.setOnClickListener {
            onClick(items[position])
        }
        holder.view.buttonDelete.setOnClickListener {
            onDelete(items[position])
        }
    }
}