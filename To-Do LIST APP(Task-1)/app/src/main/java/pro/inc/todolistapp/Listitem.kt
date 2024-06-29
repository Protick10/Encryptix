package pro.inc.todolistapp

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

data class Listitem(
    var id: Int,
    var name: String,
    var priority:Int,
    var description: String,
    var creationDate: Instant = Clock.System.now(),
    var dueDate: Instant,
    var isComplete:Boolean = false)
//                    var isEditingmode: Boolean = false)


data class ListitemResponse(val listitems: List<Listitem>)


//class ListitemRepository{
//    private var _listItems = Listitem(0, "name", 0, "description", Date(System.currentTimeMillis()), Date(System.currentTimeMillis()), false, false)
//
//    fun getListItems() = _listItems
//
//    fun addItem(item: Listitem){
//        _listItems.id = item.id
//    }
//
//
//
// }
