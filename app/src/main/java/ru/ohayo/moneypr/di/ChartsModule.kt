package ru.ohayo.moneypr.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.ohayo.moneypr.domain.use_case.CalendarUseCase
import ru.ohayo.moneypr.ui.screens.charts.components.ChartDataMapper

@Module
@InstallIn(SingletonComponent::class)
object ChartsModule {

    @Provides
    fun provideCalendarUseCase(): CalendarUseCase {
        return CalendarUseCase()
    }

    @Provides
    fun provideChartDataMapper(): ChartDataMapper {
        return ChartDataMapper()
    }
}