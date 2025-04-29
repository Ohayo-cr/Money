package ru.ohayo.moneypr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {
    private val _isBottomBarVisible = MutableStateFlow(true)
    val isBottomBarVisible: StateFlow<Boolean> = _isBottomBarVisible

    fun setBottomBarVisibility(isVisible: Boolean) {
        viewModelScope.launch {
            _isBottomBarVisible.value = isVisible
        }
    }
}