package cz.levinzonr.ezpad.ui.main

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.widget.EditText
import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Notebook
import kotlinx.android.synthetic.main.edit_notebook_dialog.*

class EditNotebookDialog : DialogFragment() {

    private lateinit var listener: OnCreateListener

    interface OnCreateListener {
        fun onCreated(notebook: Notebook)
        fun onEdited(notebook: Notebook)
    }

    companion object {

        const val ARG_ID = "NotebookID"
        const val TAG = "EditNotebookFragment"

        fun show(manager: FragmentManager, listener: OnCreateListener, notebook: Notebook? = null) {
            val bundle = Bundle()
            bundle.putParcelable(ARG_ID, notebook)
            val fragment = manager.findFragmentByTag(TAG) as? EditNotebookDialog ?: EditNotebookDialog()
            fragment.apply {
                arguments = bundle
                this.listener = listener
                show(manager, TAG)
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val infalter = LayoutInflater.from(context)
        val view = infalter.inflate(R.layout.edit_notebook_dialog, null, false)
        val edit = view.findViewById<EditText>(R.id.editText)

        val notebook :Notebook? = arguments?.getParcelable(ARG_ID)


        val builder = AlertDialog.Builder(context)
                .setView(view)
                .setNegativeButton(android.R.string.no, {_, _ ->
                    dismiss()
                })

        if (notebook ==  null) {
            builder.setTitle("new")
            builder.setPositiveButton(android.R.string.yes, {_, _ ->
                listener.onCreated(Notebook(name = edit.text.toString()))
            })
        } else {
            edit.setText(notebook.name)
            builder.setTitle("Edit")
            builder.setPositiveButton(android.R.string.yes, {_, _ ->
                listener.onEdited(notebook.also { it.name = edit.text.toString() })
            })
        }


        return builder.create()
    }

}