package cz.levinzonr.ezpad.domain.repositories

import cz.levinzonr.ezpad.domain.models.Notebook
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow

interface NotebookRepository {

    fun getNotebook(id: Long) : Flowable<Notebook>

    fun getNotebooks() : Flowable<List<Notebook>>

    fun addNotebook(notebook: Notebook) : Flowable<Notebook>

    fun updateNotebook(notebook: Notebook) : Flowable<Notebook>

    fun deleteNotebook(id: Long) : Flowable<Any>
}