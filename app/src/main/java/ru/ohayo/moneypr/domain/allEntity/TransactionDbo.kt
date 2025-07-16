package ru.ohayo.moneypr.domain.allEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["categoryDbo"]),
        Index(value = ["currency"]),
        Index(value = ["fromAccount"]),
        Index(value = ["toAccount"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = CategoryDbo::class,
            parentColumns = ["id"],
            childColumns = ["categoryDbo"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AccountDbo::class,
            parentColumns = ["id"],
            childColumns = ["fromAccount"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = AccountDbo::class,
            parentColumns = ["id"],
            childColumns = ["toAccount"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TransactionDbo(
    val amount: Double,
    val content: String? = null,
    val timestamp: Long,
    val categoryDbo: Long, // ID категории
    val fromAccount: Long? = null, // Счет, с которого списываются средства
    val toAccount: Long? = null, // Счет, на который зачисляются средства
    val currency: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)