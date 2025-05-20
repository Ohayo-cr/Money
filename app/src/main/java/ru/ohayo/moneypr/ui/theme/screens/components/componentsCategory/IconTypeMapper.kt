package ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory

import ru.ohayo.moneypr.R

object IconTypeMapper {
    private val noTintIcons = mutableSetOf<Int>().apply {
        add(R.drawable.notint_cat_1)
        add(R.drawable.notint_cat_2)
        add(R.drawable.notint_cat_3)
    }

    fun isNoTint(iconResId: Int): Boolean = iconResId in noTintIcons

    // Добавить иконку в список "без tint"
    fun addNoTintIcon(iconResId: Int) {
        noTintIcons.add(iconResId)
    }

    // Удалить иконку из списка "без tint"
    fun removeNoTintIcon(iconResId: Int) {
        noTintIcons.remove(iconResId)
    }

}
