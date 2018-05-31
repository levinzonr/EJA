package cz.levinzonr.ezpad.api.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Note(
        @ManyToOne @JsonIgnore
        val notebook: Notebook,
        var title: String,
        var contents: String) {

        @Id @GeneratedValue
        var id: Long = 0
}
