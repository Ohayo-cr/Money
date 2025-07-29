package ru.ohayo.moneypr.ui.screens.currencyScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.ohayo.moneypr.repository.CurrencyRepository
import ru.ohayo.moneypr.data.room.currency.CurrencyDbo
import ru.ohayo.moneypr.ui.screens.addAccount.components.AccountItem
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : ViewModel() {

    private val _currencyList = mutableListOf<AccountItem>()
    val currencyList: List<AccountItem> get() = _currencyList


    init {
        viewModelScope.launch {
            loadCurrencyList()
        }
    }
    private fun loadCurrencyList() {
        viewModelScope.launch {
            val currencies = currencyRepository.getAllAccountItems()
            _currencyList.clear()
            _currencyList.addAll(currencies)
        }
    }
}