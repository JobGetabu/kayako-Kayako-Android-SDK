package com.kayako.sdk.android.k5.common.adapter.messengerlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kayako.sdk.android.k5.common.adapter.BaseListItem;

import java.util.Map;

public class SimpleMessageContinuedOtherListItem extends BaseListItem {

    private String message;
    private long time;
    private Map<String, Object> data;

    public SimpleMessageContinuedOtherListItem(@NonNull String message, @Nullable long time, @Nullable Map<String, Object> data) {
        super(MessengerListType.SIMPLE_MESSAGE_CONTINUED_OTHER);
        this.message = message;
        this.time = time;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
