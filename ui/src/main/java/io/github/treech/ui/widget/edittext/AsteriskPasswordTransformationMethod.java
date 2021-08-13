package io.github.treech.ui.widget.edittext;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

/**
 * ‘****’号密码输入样式
 *
 * @since 2019-07-05 9:34
 */
public class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {

    private static AsteriskPasswordTransformationMethod sInstance;

    public static PasswordTransformationMethod getInstance() {
        if (sInstance != null) {
            return sInstance;
        }
        sInstance = new AsteriskPasswordTransformationMethod();
        return sInstance;
    }

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new AsteriskPasswordCharSequence(source);
    }

    private static class AsteriskPasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        AsteriskPasswordCharSequence(CharSequence source) {
            mSource = source; // Store char sequence
        }

        @Override
        public char charAt(int index) {
            return '*'; // This is the important part
        }

        @Override
        public int length() {
            return mSource.length(); // Return default
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }

}
