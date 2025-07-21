package ru.ohayo.moneypr.ui.screens.addAccount.components

import ru.ohayo.moneypr.data.room.account.AccountType

val accountTypeList = listOf(
    AccountItem(
        iconResId = AccountIconMapper.getIconResId(AccountType.Cash),
        mainText = "Cash",
        secondaryText = "Cash transactions",
        shouldTintIcon = true
    ),
    AccountItem(
        iconResId = AccountIconMapper.getIconResId(AccountType.Card),
        mainText = "Card",
        secondaryText = "Credit/Debit card transactions",
        shouldTintIcon = true
    ),
    AccountItem(
        iconResId = AccountIconMapper.getIconResId(AccountType.Contribution),
        mainText = "Contribution",
        secondaryText = "Contributions made",
        shouldTintIcon = true
    ),
    AccountItem(
        iconResId = AccountIconMapper.getIconResId(AccountType.Other),
        mainText = "Other",
        secondaryText = "",
        shouldTintIcon = true
    )
)