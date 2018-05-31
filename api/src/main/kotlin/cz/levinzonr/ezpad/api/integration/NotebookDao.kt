package cz.levinzonr.ezpad.api.integration

import cz.levinzonr.ezpad.api.models.Notebook

interface NotebookDao {

    fun all() : List<Notebook>

    fun findById(id: Long) : Notebook

    fun create(notebook: Notebook) : Notebook

}