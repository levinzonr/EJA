package cz.levinzonr.ezpad.api.controllers

import cz.levinzonr.ezpad.api.models.Note
import cz.levinzonr.ezpad.api.models.Notebook
import cz.levinzonr.ezpad.api.repositories.NotebookRepository
import cz.levinzonr.ezpad.api.repositories.NotesRepository
import org.aspectj.weaver.ast.Not
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
class NotesController {

    @Autowired lateinit var notesRepo:  NotesRepository
    @Autowired lateinit var notebookRepo: NotebookRepository


    private fun getNotebookWithNote(id: Long) : Notebook? {
        var nb : Notebook? = null
        notebookRepo.findAll().forEach {
            val notebook = it
            notebook.notes.forEach {
                if (it.id == id) {
                    nb = notebook
                }
            }
        }
        return nb
    }

    @GetMapping("/notebooks/{notebookId}/notes")
    fun getNotes(@PathVariable notebookId: Long) : Collection<Note> {
        return notebookRepo.findById(notebookId).get().notes.toMutableList()
    }

    @PostMapping("/notebooks/{notebookId}/notes")
    fun postNote(@PathVariable notebookId: Long, @RequestBody note: Note) :ResponseEntity<Note> {
        val notebook = notebookRepo.findById(notebookId)
        return if (notebook.isPresent) {
            val result = notesRepo.save(Note(notebook.get(), note.title, note.contents))

            val nb = notebook.get()
            nb.notesCount++
            notebookRepo.save(nb)
            ResponseEntity.ok(result)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/notes/{noteId}")
    fun deleteNote(@PathVariable("noteId") noteId: Long) : ResponseEntity<Any> {
        val toDelete = notesRepo.findById(noteId)
        val nb = getNotebookWithNote(noteId)
        return if (nb != null) {
            if (toDelete.isPresent) {
                notesRepo.delete(toDelete.get())
                nb.notes = nb.notes.filter { noteId != it.id }
                nb.notesCount--
                notebookRepo.save(nb)
                ResponseEntity.ok(nb as Any)

            } else {
                ResponseEntity.notFound().build()
            }
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/notes/{id}")
    fun updateNote(@PathVariable("id") noteId: Long, @RequestBody note: Note) : ResponseEntity<Any> {
        val toUpdate = notesRepo.findById(noteId)
        return if (toUpdate.isPresent) {
            toUpdate.get().contents = note.contents
            toUpdate.get().title = note.title
            notesRepo.save(toUpdate.get())
            ResponseEntity.ok(toUpdate.get())
        } else {
            return ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/notes/{id}")
    fun getNote(@PathVariable("id") id: Long) : ResponseEntity<Note> {
        val note = notesRepo.findById(id)
        return if (note.isPresent) {
            ResponseEntity.ok(note.get())
        } else {
            ResponseEntity.notFound().build()
        }
    }

}