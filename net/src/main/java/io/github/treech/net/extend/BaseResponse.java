package io.github.treech.net.extend;

import com.google.gson.annotations.SerializedName;

import io.github.treech.net.HttpConstant;

/**
 * custom {@link BaseResponse} for your project
 *
 * @param <T>
 */
public class BaseResponse<T> {

    @SerializedName(value = "status", alternate = "code")
    public int status;
    @SerializedName(value = "message", alternate = "msg")
    public String message;
    @SerializedName("data")
    public T data;

    public boolean isSuccess() {
        return status == HttpConstant.IS_SUCCESS;
    }

    public boolean isPermissionDenied() {
        return status == HttpConstant.IS_PERMISSION_DENIED;
    }

    public boolean isInvalid() {
        return status == HttpConstant.IS_INVALID;
    }

    public boolean isOutOfDate() {
        return status == HttpConstant.IS_OUT_OF_DATE;
    }

    public boolean isKickOff() {
        return status == HttpConstant.IS_KICK_OFF;
    }

    public int getCode() {
        return status;
    }

    public void setCode(int code) {
        this.status = code;
    }

    public T getResult() {
        return data;
    }

    public void setResult(T result) {
        this.data = result;
    }

    public boolean isOk() {
        return status == 0;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
