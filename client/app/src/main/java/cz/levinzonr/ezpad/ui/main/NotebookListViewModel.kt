package cz.levinzonr.ezpad.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import cz.levinzonr.ezpad.domain.models.Notebook
import cz.levinzonr.ezpad.domain.repositories.NotebookRepoImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NotebookListViewModel : ViewModel() {

    private val repo: NotebookRepoImpl = NotebookRepoImpl()
    val data = MutableLiveData<ArrayList<Notebook>>()
    private val cd = CompositeDisposable()




    fun getNotebooks() {
        Log.d("Load", "Load")
        cd.addAll( repo.getNotebooks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result: List<Notebook> -> data.value = ArrayList(result)},
                        {e: Throwable? -> }
                        )
        )
    }

    fun createNotebook(notebook: Notebook) {
        cd.addAll(repo.addNotebook(notebook)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    data.value?.add(it)
                    data.value = ArrayList(data.value)
                }, {}))
    }

    fun editNotebook(notebook: Notebook) {
        cd.addAll(repo.updateNotebook(notebook)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ updated ->
                    data.value?.forEach({
                        if (it.id == updated.id) {
                            it.name = updated.name
                        }
                    })
                    data.value = data.value
                }, {}

                ))
    }

    fun deleteNotebook(notebook: Notebook) {
        cd.addAll(repo.deleteNotebook(notebook.id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                   data.value?.remove(notebook)
                    data.value = ArrayList(data.value)
                },
                        {e: Throwable? -> Log.d("List", e?.localizedMessage) }
                ))
    }


    override fun onCleared() {
        super.onCleared()
        cd.clear()
    }
}
