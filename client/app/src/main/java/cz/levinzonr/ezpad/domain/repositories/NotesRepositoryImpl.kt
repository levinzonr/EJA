package cz.levinzonr.ezpad.domain.repositories

import cz.levinzonr.ezpad.domain.models.Note
import cz.levinzonr.ezpad.network.NetworkRepository
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow

class NotesRepositoryImpl : NoteRepository{
    private val reomte = NetworkRepository()

    override fun getNotesFromNotebook(notebookId: Long): Flowable<List<Note>> {
        return  reomte.getNotesFromNotebook(notebookId).map {it ->
            it.forEach {it.notebookId = notebookId}
            return@map it
        }
    }

    override fun addNoteToNotebook(notebookId: Long, note: Note) : Flowable<Note> {
        return reomte.addNoteToNotebook(notebookId, note)
    }

    override fun deleteNote(noteId: Long) : Flowable<Any>{
        return reomte.deleteNote(noteId)
    }

    override fun updateNote(note: Note) : Flowable<Note> {
        return reomte.updateNote(note)
    }

    override fun getNoteById(id: Long): Flowable<Note> {
        return reomte.getNoteById(id)
    }
}