package cz.levinzonr.ezpad.ui.notedetail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import androidx.navigation.fragment.findNavController

import cz.levinzonr.ezpad.R
import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.ui.onTextChanged
import kotlinx.android.synthetic.main.note_detail_fragment.*


class NoteDetailFragment : Fragment() {

    companion object {
        fun newInstance() = NoteDetailFragment()
        const val ARG_ID = "NoteId"
    }

    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.note_detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Detail"

        viewModel = ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
        if (viewModel.item.value == null) {
            viewModel.item.value = arguments?.getParcelable("note")
            title.setText(viewModel.item.value?.title)
            contents.setText(viewModel.item.value?.contents)
        }

        title.onTextChanged { viewModel.setTitle(it) }
        contents.onTextChanged { viewModel.setContents(it) }

        buttonSubmit.setOnClickListener{
            viewModel.postNote()
            findNavController().popBackStack()
        }

    }


}
