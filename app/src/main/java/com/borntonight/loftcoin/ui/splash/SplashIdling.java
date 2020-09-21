package com.borntonight.loftcoin.ui.splash;

// интерфейс для отложенных операций (запуск только через 1.5 сек
public interface SplashIdling {
    void busy();
    void idle();
}
