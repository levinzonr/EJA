package cz.levinzonr.ezpad.api.integration.jpa

import cz.levinzonr.ezpad.api.integration.NotebookDao
import cz.levinzonr.ezpad.api.models.Notebook
import cz.levinzonr.ezpad.api.repositories.NotebookRepository
import org.springframework.beans.factory.annotation.Autowired

class NotebookDaoImpl : NotebookDao {

    @Autowired lateinit var repo : NotebookRepository

    override fun all(): List<Notebook> {
        return repo.findAll().toList()
    }

    override fun findById(id: Long): Notebook {
        return repo.findById(id).get()
    }

    override fun create(notebook: Notebook): Notebook {
        val created = repo.save(notebook)
        return created
    }
}