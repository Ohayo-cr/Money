package ru.ohayo.moneypr.ui.screens.addAccount.components


data class AccountItem(
    val iconResId: Int,
    val mainText: String,
    val secondaryText: String,
    val shouldTintIcon: Boolean = false
)