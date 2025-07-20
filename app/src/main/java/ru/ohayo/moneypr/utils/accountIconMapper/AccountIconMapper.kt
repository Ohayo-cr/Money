package ru.ohayo.moneypr.utils.accountIconMapper

import ru.ohayo.moneypr.R
import ru.ohayo.moneypr.data.room.account.AccountType

object AccountIconMapper {

    private val iconMapping = mapOf(
        AccountType.Cash to R.drawable.acc_d_cash,
        AccountType.Card to R.drawable.acc_d_card,
        AccountType.Contribution to R.drawable.acc_d_contribution,
        AccountType.Other to R.drawable.acc_d_other
    )
    fun getIconResId(accountType: AccountType): Int {
        return iconMapping[accountType] ?: R.drawable.acc_d_cash
    }
}