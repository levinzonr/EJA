package cz.levinzonr.ezpad.domain.repositories

import cz.levinzonr.ezpad.domain.models.Note
import io.reactivex.Flowable
import org.intellij.lang.annotations.Flow

interface NoteRepository {

    fun getNotesFromNotebook(notebookId: Long) : Flowable<List<Note>>

    fun addNoteToNotebook(notebookId: Long, note: Note) : Flowable<Note>

    fun deleteNote(noteId: Long) : Flowable<Any>

    fun updateNote(note: Note) : Flowable<Note>

    fun getNoteById(id: Long) : Flowable<Note>

}