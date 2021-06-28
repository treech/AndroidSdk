package io.github.treech.loadX.core;


import io.github.treech.loadX.callback.Callback;

public interface Convertor<T> {
   Class<?extends Callback> map(T t);
}
