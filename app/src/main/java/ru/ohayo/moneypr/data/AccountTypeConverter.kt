package ru.ohayo.moneypr.data


import androidx.room.TypeConverter
import ru.ohayo.moneypr.domain.AccountType

class AccountTypeConverter {

    @TypeConverter
    fun fromAccountType(type: AccountType): String {
        return type.name
    }

    @TypeConverter
    fun toAccountType(name: String): AccountType {
        return AccountType.valueOf(name)
    }
}