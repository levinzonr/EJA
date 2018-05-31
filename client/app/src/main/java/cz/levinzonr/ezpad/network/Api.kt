package cz.levinzonr.ezpad.network

import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.domain.models.Notebook
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow
import retrofit2.http.*

interface Api {

    @GET("/notebooks")
    fun getNotebooks() : Flowable<List<Notebook>>

    @GET("/notebooks/{notebookId}/notes")
    fun getNotesFromNotebook(@Path("notebookId")notebookId: Long) : Flowable<List<Note>>

    @GET("/notes/{id}")
    fun getNote(@Path("id") id: Long) : Flowable<Note>

    @POST("/notebooks/")
    fun postNotebook(@Body notebook: Notebook) : Flowable<Notebook>

    @POST("/notebooks/{notebookId}/notes")
    fun postNote(@Path("notebookId") notebook: Long, @Body note: Note) : Flowable<Note>

    @DELETE("/notebooks/{notebookId}")
    fun deleteNotebook(@Path("notebookId") notebookId: Long) : Flowable<Any>

    @DELETE("/notes/{id}")
    fun deleteNote(@Path("id") noteId: Long) : Flowable<Any>

    @POST("/notes/{id}")
    fun updateNote(@Path("id") noteId: Long, @Body note: Note) : Flowable<Note>

    @POST("/notebooks/{id}")
    fun updateNotebook(@Path("id") notebookId: Long, @Body notebook: Notebook) : Flowable<Notebook>

}