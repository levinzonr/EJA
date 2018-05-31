package cz.levinzonr.ezpad.ui.notedetail

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class CustomViewModelFactory() : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteDetailViewModel() as T
    }
}