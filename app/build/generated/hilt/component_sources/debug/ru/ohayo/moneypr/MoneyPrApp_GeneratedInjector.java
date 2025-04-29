package ru.ohayo.moneypr;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = MoneyPrApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface MoneyPrApp_GeneratedInjector {
  void injectMoneyPrApp(MoneyPrApp moneyPrApp);
}
