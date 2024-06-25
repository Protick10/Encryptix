package pro.inc.todolistapp

import java.sql.Date

data class Listitem(var id: Int,
                    var name: String,
                    var priority:Int,
                    var description: String,
                    var creationDate: Date = Date(System.currentTimeMillis()),
                    var dueDate: Date,
                    var isComplete:Boolean = false,
                    var isEditingmode: Boolean = false)


