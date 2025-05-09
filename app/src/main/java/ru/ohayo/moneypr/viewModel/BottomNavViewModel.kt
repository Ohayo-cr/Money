package ru.ohayo.moneypr.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavViewModel @Inject constructor() : ViewModel() {

    private val _selectedItem = mutableStateOf<String>("")
    val selectedItem: State<String> get() = _selectedItem

    fun selectItem(route: String) {
        viewModelScope.launch {
            _selectedItem.value = route
        }
    }
}