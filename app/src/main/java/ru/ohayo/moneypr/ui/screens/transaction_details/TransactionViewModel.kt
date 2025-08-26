package ru.ohayo.moneypr.ui.screens.transaction_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.TransactionRepository
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository,
) : ViewModel() {

    private val _transactionId = MutableStateFlow<Long?>(null)
    val transactionId: StateFlow<Long?> = _transactionId.asStateFlow()



    val transactionWithAccount = _transactionId
        .filterNotNull()
        .flatMapLatest { id ->

            repository.getTransactionWithAccount(id)

        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun loadTransaction(id: Long) {
        _transactionId.value = id
    }

    fun clearTransaction() {
        _transactionId.value = null
    }
    fun setTransactionIdAndNavigate(id: Long, onComplete: () -> Unit) {
        viewModelScope.launch {
            // Сохраняем текущий ID для сравнения
            val currentId = _transactionId.value

            // Устанавливаем новый ID
            _transactionId.value = id

            // Если уже был другой ID, ждем обновления данных
            if (currentId != null && currentId != id) {
                // Ждем, пока данные обновятся на новые
                transactionWithAccount
                    .filter { it?.transaction?.id == id }
                    .first()
            } else {
                // Если это первый переход или тот же ID, ждем любых данных
                transactionWithAccount
                    .filterNotNull()
                    .first()
            }

            // Данные загружены, можно переходить
            onComplete()
        }
    }
}


