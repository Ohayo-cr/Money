package ru.ohayo.moneypr.ui.screens.transaction_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.data.room.transaction.TransactionDbo
import ru.ohayo.moneypr.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _transaction = MutableStateFlow<TransactionDbo?>(null)
    val transaction: StateFlow<TransactionDbo?> = _transaction.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    fun getTransactionById(id: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _transaction.value = repository.getTransactionById(id)
            } catch (e: Exception) {
                _transaction.value = null
            } finally {
                _loading.value = false
            }
        }
    }
}