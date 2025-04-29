package ru.ohayo.moneypr.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.ohayo.moneypr.data.repository.CurrencyRepository
import ru.ohayo.moneypr.domain.category.Category
import ru.ohayo.moneypr.domain.currency.Currency
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    // StateFlow для управления состоянием списка валют
    private val _currencies = MutableStateFlow<List<Currency>>(emptyList())
    val currencies: StateFlow<List<Currency>> = _currencies

    // StateFlow для отображения ошибок
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        viewModelScope.launch {
            try {
                // Сбор данных из репозитория
                currencyRepository.getAllCurrencies().collect { currencies ->
                    _currencies.value = currencies
                }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    suspend fun isCurrencyEmpty(): Boolean {
        return currencyRepository.isCurrencyEmpty()
    }

    fun addCurrency(currency: Currency) {
        viewModelScope.launch {
            try {
                currencyRepository.insertCurrency(currency)
            } catch (e: Exception) {
                _error.value = "Failed to add currency: ${e.message}"
            }
        }
    }
}