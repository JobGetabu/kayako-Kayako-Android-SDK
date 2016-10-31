package com.kayako.sdk.android.k5.common.adapter.messengerlist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kayako.sdk.android.k5.common.adapter.BaseListItem;

import java.util.Map;

public class AttachmentMessageOtherListItem extends BaseListItem {

    private String avatarUrl;
    private String attachmentThumbnailUrl;
    private String message;
    private long time;
    private ChannelDecoration channel;
    private Map<String, Object> data;

    public AttachmentMessageOtherListItem(@NonNull String attachmentThumbnailUrl, @Nullable String message, @NonNull String avatarUrl, @Nullable ChannelDecoration channel, @Nullable long time, @Nullable Map<String, Object> data) {
        super(MessengerListType.ATTACHMENT_MESSAGE_OTHER);
        this.message = message;
        this.avatarUrl = avatarUrl;
        this.attachmentThumbnailUrl = attachmentThumbnailUrl;
        this.time = time;
        this.channel = channel;
        this.data = data;

    }

    public String getAttachmentThumbnailUrl() {
        return attachmentThumbnailUrl;
    }

    public void setAttachmentThumbnailUrl(String attachmentThumbnailUrl) {
        this.attachmentThumbnailUrl = attachmentThumbnailUrl;
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public ChannelDecoration getChannel() {
        return channel;
    }

    public void setChannel(ChannelDecoration channel) {
        this.channel = channel;
    }
}
