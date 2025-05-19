package ru.ohayo.moneypr.ui.theme.screens.components.componentsCategory

sealed class CategoryIconItem {
    abstract val iconResId: Int
    abstract val isPicture: Boolean
}

data class VectorIcon(override val iconResId: Int) : CategoryIconItem() {
    override val isPicture: Boolean = false
}

data class PictureIcon(override val iconResId: Int) : CategoryIconItem() {
    override val isPicture: Boolean = true
}