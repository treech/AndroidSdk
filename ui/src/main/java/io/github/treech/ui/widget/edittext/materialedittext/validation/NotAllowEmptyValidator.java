package io.github.treech.ui.widget.edittext.materialedittext.validation;

import androidx.annotation.NonNull;

/**
 * 非空检验
 */
public class NotAllowEmptyValidator extends METValidator {

    public NotAllowEmptyValidator(@NonNull String errorMessage) {
        super(errorMessage);
    }

    @Override
    public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
        return !isEmpty;
    }
}
