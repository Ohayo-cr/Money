package ru.ohayo.moneypr.ui.screens.currencyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    // StateFlow для управления состоянием списка валют
    private val _currencies = MutableStateFlow<List<CurrencyDbo>>(emptyList())
    val currencies: StateFlow<List<CurrencyDbo>> = _currencies

    // StateFlow для отображения ошибок
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            try {
                // Сбор данных из репозитория
                currencyRepository.getAllCurrenciesFlow().collect { currencies ->
                    _currencies.value = currencies
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}