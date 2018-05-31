package cz.levinzonr.ezpad.api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany


@Entity
data class Notebook(
        var name: String
        ) {

    @Id @GeneratedValue
    var id: Long = 0

    @JsonIgnore
    @OneToMany (mappedBy = "notebook")
    var notes : List<Note> = ArrayList()

    var notesCount = 0

}