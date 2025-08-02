package ru.ohayo.moneypr.data.room.transaction

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import ru.ohayo.moneypr.data.room.account.AccountDbo
import ru.ohayo.moneypr.data.room.category.CategoryDbo

@Entity(
    tableName = "transactions",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["category"]),
        Index(value = ["currency"]),
        Index(value = ["paymentAccount"]),
        Index(value = ["recipientAccount"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = CategoryDbo::class,
            parentColumns = ["categoryName"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AccountDbo::class,
            parentColumns = ["name"],
            childColumns = ["paymentAccount"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = AccountDbo::class,
            parentColumns = ["name"],
            childColumns = ["recipientAccount"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class TransactionDbo(
    val amount: Double,
    val currency: String,
    val account: String? = null,
    val category: String,
    val parentCategory: Long? = null,
    val paymentAccount: String? = null,
    val recipientAccount: String? = null,
    val note: String? = null,
    val tag: String? = null,
    val timestamp: Long,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)