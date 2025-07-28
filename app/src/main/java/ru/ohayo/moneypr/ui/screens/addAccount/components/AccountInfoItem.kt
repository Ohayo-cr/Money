package ru.ohayo.moneypr.ui.screens.addAccount.components

data class AccountInfoItem(
    val key: String,
    val valueText: String? = null,
    val valueIcon: Int? = null,
    val onClick: () -> Unit = {},
    val isClickable: Boolean = true
)