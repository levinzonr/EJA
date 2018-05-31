package cz.levinzonr.ezpad.ui.notelist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Note
import kotlinx.android.synthetic.main.note_list_fragment.*

class NoteListFragment : Fragment() {

    companion object {
        fun newInstance() = NoteListFragment()
    }

    private lateinit var viewModel: NoteListViewModel
    private var id = -1L
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Notes"
        viewModel = ViewModelProviders.of(this).get(NoteListViewModel::class.java)
         id  = arguments?.getLong("ARG", -1) ?: -1
        viewModel.getNotes(id)
        viewModel.data.observe(this, Observer {
            (recyclerView.adapter as NoteAdapter).items = ArrayList(it)
        })    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.apply {
            adapter = NoteAdapter({
                val bundle = Bundle().also { bundle ->  bundle.putParcelable("note", it) }
                findNavController().navigate(R.id.noteDetailFragment, bundle )
            },
                    {viewModel.deleteNote(it) }
            )
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        addNoteButton.setOnClickListener {
            val note =  Note(notebookId = id, title = "", contents =  "")
            val bundle = Bundle().also { it.putParcelable("note", note)}
            findNavController().navigate(R.id.noteDetailFragment, bundle)
        }
    }

}
