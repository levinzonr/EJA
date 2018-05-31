package cz.levinzonr.ezpad.api.repositories

import cz.levinzonr.ezpad.api.models.Note
import org.springframework.data.repository.CrudRepository

interface NotesRepository : CrudRepository<Note, Long>{

}