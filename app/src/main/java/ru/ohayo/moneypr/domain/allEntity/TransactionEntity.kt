package ru.ohayo.moneypr.domain.allEntity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    indices = [
        Index(value = ["timestamp"]),
        Index(value = ["category"]),
        Index(value = ["currency"]),
        Index(value = ["fromAccount"]),
        Index(value = ["toAccount"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["fromAccount"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["toAccount"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class TransactionEntity(
    val amount: Double,
    val content: String? = null,
    val timestamp: Long,
    val category: Long, // ID категории
    val fromAccount: Long? = null, // Счет, с которого списываются средства
    val toAccount: Long? = null, // Счет, на который зачисляются средства
    val currency: String,
    @PrimaryKey(autoGenerate = true) val id: Long? = null
)