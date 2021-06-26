package com.treech.sdk.loadX.core;


import com.treech.sdk.loadX.callback.Callback;

public interface Convertor<T> {
   Class<?extends Callback> map(T t);
}
