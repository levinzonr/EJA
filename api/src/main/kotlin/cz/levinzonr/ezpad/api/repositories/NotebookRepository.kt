package cz.levinzonr.ezpad.api.repositories

import cz.levinzonr.ezpad.api.models.Notebook
import org.springframework.data.repository.CrudRepository

interface NotebookRepository : CrudRepository<Notebook, Long> {


}