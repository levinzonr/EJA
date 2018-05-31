package cz.levinzonr.ezpad.api.integration.jpa

import cz.levinzonr.ezpad.api.integration.NotesDao
import cz.levinzonr.ezpad.api.models.Note
import cz.levinzonr.ezpad.api.models.Notebook
import cz.levinzonr.ezpad.api.repositories.NotebookRepository
import cz.levinzonr.ezpad.api.repositories.NotesRepository
import org.springframework.beans.factory.annotation.Autowired

class NotesDaoImpl : NotesDao {

    @Autowired lateinit var repo: NotesRepository
    @Autowired lateinit var notebooksRepository: NotebookRepository

    override fun findById(id: Long): Note {
        return repo.findById(id).get()
    }

    override fun fromNotebook(notebookID: Long): List<Note> {
        return notebooksRepository.findById(notebookID).get().notes.toList()
    }

    override fun create(note: Note, notebook: Notebook): Note {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}