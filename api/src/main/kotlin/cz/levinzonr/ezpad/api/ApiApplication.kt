package cz.levinzonr.ezpad.api

import cz.levinzonr.ezpad.api.models.Note
import cz.levinzonr.ezpad.api.models.Notebook
import cz.levinzonr.ezpad.api.repositories.NotebookRepository
import cz.levinzonr.ezpad.api.repositories.NotesRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class ApiApplication {

    companion object {
        @JvmStatic
         fun main(args: Array<String>) {
            SpringApplication.run(ApiApplication::class.java, *args)
        }
    }


}