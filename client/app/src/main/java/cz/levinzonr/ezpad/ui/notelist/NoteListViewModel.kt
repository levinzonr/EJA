package cz.levinzonr.ezpad.ui.notelist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel;
import android.util.Log
import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.domain.models.Notebook
import cz.levinzonr.ezpad.domain.repositories.NotebookRepoImpl
import cz.levinzonr.ezpad.domain.repositories.NotesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NoteListViewModel : ViewModel() {

    private val repo = NotesRepositoryImpl()
    val data = MutableLiveData<ArrayList<Note>>()
    private val cd = CompositeDisposable()


    fun getNotes(id: Long) {
        Log.d("Load", "Load")
        cd.addAll( repo.getNotesFromNotebook(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result: List<Note> -> data.value = ArrayList(result)})
        )
    }

    fun deleteNote(note: Note) {
        cd.addAll(repo.deleteNote(note.id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({data.value = data.value?.also { it.remove(note) }}))
    }

    override fun onCleared() {
        super.onCleared()
        cd.clear()
    }
}
