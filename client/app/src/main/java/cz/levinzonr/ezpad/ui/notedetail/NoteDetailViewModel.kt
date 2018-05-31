package cz.levinzonr.ezpad.ui.notedetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.domain.repositories.NotesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoteDetailViewModel() : ViewModel() {
    val note = MutableLiveData<Note>()
    private val cd = CompositeDisposable()
    private val repo = NotesRepositoryImpl()
    val item = MutableLiveData<Note>()


    fun setTitle(string: String) {
        item.value?.title = string
    }

    fun setContents(string: String) {
        item.value?.contents = string
    }

    fun postNote() {
       val note = item.value!!
       val flow =  if (note.id == null) {
            repo.addNoteToNotebook(note.notebookId, note)
        } else {
            repo.updateNote(note)
        }
       cd.addAll(flow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
               .subscribe(
                       { Log.d("Detail", "Ok")
                       item.value = it},
                       {e: Throwable? -> Log.d("Detail", "$e")}
               ))
    }


}
