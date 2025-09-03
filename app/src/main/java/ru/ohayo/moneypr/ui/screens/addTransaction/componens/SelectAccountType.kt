package ru.ohayo.moneypr.ui.screens.addTransaction.componens

sealed class AccountSelectionType {
    data object From : AccountSelectionType()
    data object To : AccountSelectionType()
}