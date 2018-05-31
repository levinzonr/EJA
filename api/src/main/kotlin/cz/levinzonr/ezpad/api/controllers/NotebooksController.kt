package cz.levinzonr.ezpad.api.controllers

import cz.levinzonr.ezpad.api.models.Notebook
import cz.levinzonr.ezpad.api.repositories.NotebookRepository
import cz.levinzonr.ezpad.api.repositories.NotesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable


@RestController
class NotebooksController {

    @Autowired lateinit var repository: NotebookRepository
    @Autowired lateinit var notesRepo: NotesRepository

    @GetMapping("/notebooks")
    fun getNotebooks(): Collection<Notebook> {
        return repository.findAll().toMutableList()
    }

    @GetMapping("/notebooks/{id}")
    fun getNotebookById(@PathVariable id: Long) : ResponseEntity<*> {
        val optional  = repository.findById(id)
        return if (optional.isPresent) {
            ResponseEntity.ok(optional.get())
        } else {
            ResponseEntity.notFound().build<Notebook>()
        }
    }

    @PostMapping("/notebooks")
    fun postNotebook(@RequestBody notebook: Notebook) : ResponseEntity<Notebook> {
        println("Post $notebook")
        if (notebook.name.isEmpty()) return ResponseEntity.badRequest().build()
        val result = repository.save(notebook)
        return ResponseEntity.ok(result)

    }

    @DeleteMapping("/notebooks/{notebookId}")
    fun deleteNotebook(@PathVariable notebookId: Long) : ResponseEntity<Any> {
        println("Delete notebook $notebookId")
        val toDelete = repository.findById(notebookId)
        return if (toDelete.isPresent) {
            toDelete.get().notes.forEach {
                notesRepo.deleteById(it.id)
            }
            repository.delete(toDelete.get())
            ResponseEntity.ok(toDelete.get())

        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/notebooks/{id}")
    fun updateNotebook(@PathVariable id: Long, @RequestBody notebook: Notebook) : ResponseEntity<Any> {
        println("Update $notebook")
        val toUpdate = repository.findById(id)
        return if (toUpdate.isPresent) {
            val nb = toUpdate.get()
            nb.name = notebook.name
            repository.save(nb)
            ResponseEntity.ok(nb)
        } else {
             ResponseEntity.notFound().build()
        }

    }


}