package cz.levinzonr.ezpad.api.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account(
        val firstName: String,
        val lastName: String,
        val displayName: String,
        val dateOfBirth: String,
        val imageUrl: String) {

    @Id
    @GeneratedValue
    var id: Long = -1
}