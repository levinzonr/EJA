package cz.levinzonr.ezpad.network

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.domain.models.Notebook
import cz.levinzonr.ezpad.domain.repositories.NoteRepository
import cz.levinzonr.ezpad.domain.repositories.NotebookRepository
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkRepository : NoteRepository, NotebookRepository {

    companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }


    private  val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val service = retrofit.create(Api::class.java)


    override fun getNotesFromNotebook(notebookId: Long): Flowable<List<Note>> {
        return service.getNotesFromNotebook(notebookId)
    }

    override fun addNoteToNotebook(notebookId: Long, note: Note) : Flowable<Note> {
        return service.postNote(notebookId, note)
    }

    override fun getNoteById(id: Long): Flowable<Note> {
        return service.getNote(id)
    }

    override fun deleteNote(noteId: Long) : Flowable<Any>{
        return service.deleteNote(noteId)
    }

    override fun updateNote(note: Note) : Flowable<Note> {
       return service.updateNote(note.id!!, note)
    }

    override fun getNotebook(id: Long): Flowable<Notebook> {
        return Flowable.just(Notebook(name = ""))
    }

    override fun getNotebooks(): Flowable<List<Notebook>> {
        return service.getNotebooks()
    }

    override fun addNotebook(notebook: Notebook): Flowable<Notebook> {
        return service.postNotebook(notebook)
    }

    override fun updateNotebook(notebook: Notebook) : Flowable<Notebook> {
        return service.updateNotebook(notebook.id!!, notebook)
    }

    override fun deleteNotebook(id: Long) : Flowable<Any>{
       return service.deleteNotebook(id)
    }
}