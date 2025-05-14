package ru.ohayo.moneypr.viewModel.use_case

import ru.ohayo.moneypr.domain.AccountRepository

// Domain Layer: Use Case for updating account balance
class UpdateAccountBalanceUseCase(private val accountRepository: AccountRepository) {
    suspend operator fun invoke(accountId: Long, amount: Double) { // Изменено на Long
        accountRepository.updateBalance(accountId, amount)
    }
}