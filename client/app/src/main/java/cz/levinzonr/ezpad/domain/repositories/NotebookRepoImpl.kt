package cz.levinzonr.ezpad.domain.repositories

import cz.levinzonr.ezpad.domain.models.Notebook
import cz.levinzonr.ezpad.network.NetworkRepository
import io.reactivex.Flowable

class NotebookRepoImpl : NotebookRepository {
    private val remote = NetworkRepository()

    override fun getNotebook(id: Long): Flowable<Notebook> {
        return remote.getNotebook(id)
    }

    override fun getNotebooks(): Flowable<List<Notebook>> {
        return remote.getNotebooks()
    }

    override fun addNotebook(notebook: Notebook): Flowable<Notebook> {
        return remote.addNotebook(notebook)
    }

    override fun updateNotebook(notebook: Notebook) : Flowable<Notebook> {
        return remote.updateNotebook(notebook)
    }

    override fun deleteNotebook(id: Long) : Flowable<Any> {
        return remote.deleteNotebook(id)
    }
}