package pro.inc.todolistapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel(){

//    private val _listItems = mutableStateOf(listOf<Listitem>()) // Mutable state to hold the list of list items
//
//    val listItems: MutableState<List<Listitem>> = _listItems  // Exposing the mutable state as immutable state to the view
//
//    private val _id = mutableStateOf<Int>(0) // Mutable state to hold the id of the list item()     // Mutable state to hold the id of the list item
//    val id: MutableState<Int> = _id // Exposing the mutable state as immutable state to the view
//
//
//
//    fun addItem(item: Listitem){
//
//        _listItems.value = _listItems.value + item
//    }
//
//    fun removeItem(item: Listitem){
//        _listItems.value = _listItems.value - item
//    }


    private val _listState = mutableStateOf(ListState())
    val listState: State<ListState> = _listState




    fun addItem(item: Listitem) {
        _listState.value = _listState.value.copy(
            list = _listState.value.list + item,
            isEditing = false
        )


    }

    fun removeItem(item: Listitem) {
        _listState.value = _listState.value.copy(
            list = _listState.value.list - item,
            isEditing = false
        )
    }

    fun startEditItem(item: Listitem) {
        _listState.value = _listState.value.copy(
            isEditing = true
        )
    }

    fun saveEditedItem(item: Listitem) {
        val updatedList = _listState.value.list.map {
            if (it.id == item.id) item else it
        }
        _listState.value = _listState.value.copy(
            list = updatedList,
            isEditing = false
        )
    }
    data class ListState(
        val isEditing: Boolean = false,
        val list: List<Listitem> = emptyList(),
        val error: String? = null
    )


}