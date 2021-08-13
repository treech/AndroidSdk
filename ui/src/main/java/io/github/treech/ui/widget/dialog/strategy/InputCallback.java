package io.github.treech.ui.widget.dialog.strategy;

import android.content.DialogInterface;

import androidx.annotation.NonNull;

/**
 * 输入内容回调
 *
 * @since 2018/11/16 上午12:15
 */
public interface InputCallback {

    void onInput(@NonNull DialogInterface dialog, CharSequence input);
}
