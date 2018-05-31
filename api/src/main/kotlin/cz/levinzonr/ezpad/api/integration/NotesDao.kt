package cz.levinzonr.ezpad.api.integration

import cz.levinzonr.ezpad.api.models.Note
import cz.levinzonr.ezpad.api.models.Notebook

interface NotesDao {

    fun findById(id: Long) : Note

    fun fromNotebook(notebookID : Long) : List<Note>

    fun create(note: Note, notebook: Notebook) : Note

}