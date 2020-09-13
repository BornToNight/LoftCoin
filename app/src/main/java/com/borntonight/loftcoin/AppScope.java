package com.borntonight.loftcoin;

import javax.inject.Scope;

@Scope
public @interface AppScope { // Кастомная аннотация (Single ton) -  Ограничение создание объекта несколько раз
}