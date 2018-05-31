package cz.levinzonr.ezpad.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Notebook
import kotlinx.android.synthetic.main.notebooklist_fragment.*

class NotebookListFragment : Fragment(), EditNotebookDialog.OnCreateListener, NotebookAdapter.ItemListener{

    companion object {
        const val TAG = "TAG"

        fun newInstance() = NotebookListFragment()
    }


    private lateinit var viewModel: NotebookListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.notebooklist_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NotebookListViewModel::class.java)
        viewModel.data.observe(this, Observer {
            (recyclerView.adapter as NotebookAdapter).items = ArrayList(it)
            progressBar.visibility = View.GONE
        })
        viewModel.getNotebooks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Notebooks"
        recyclerView.apply {
            adapter = NotebookAdapter(this@NotebookListFragment)
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            floatingActionButton.setOnClickListener({
                EditNotebookDialog.show(childFragmentManager, this@NotebookListFragment)
            })

        }
    }

    override fun onNotebookClicked(notebook: Long) {
        println("Hey")
        val bundle = Bundle()
        bundle.putLong("ARG", notebook)
        findNavController().navigate(R.id.noteListFragment, bundle)    }

    override fun onEditNotebook(notebook: Notebook) {
        EditNotebookDialog.show(childFragmentManager, notebook = notebook, listener = this)
    }

    override fun onDeleteNotebook(notebook: Notebook) {
        AlertDialog.Builder(context!!)
                .setMessage("Are you sure?")
                .setTitle("Deleting notebook..")
                .setPositiveButton(android.R.string.yes, {_, _ ->
                    viewModel.deleteNotebook(notebook)
                })
                .setNegativeButton(android.R.string.no, {_,_ ->

                })
                .create().show()
    }

    override fun onEdited(notebook: Notebook) {
        viewModel.editNotebook(notebook)


    }

    override fun onCreated(notebook: Notebook) {
        viewModel.createNotebook(notebook)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
         inflater?.inflate(R.menu.notebooklist_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.buttonRefresh -> {
                progressBar.visibility = View.VISIBLE
                viewModel.getNotebooks()
            }
        }
        return true
    }

}
