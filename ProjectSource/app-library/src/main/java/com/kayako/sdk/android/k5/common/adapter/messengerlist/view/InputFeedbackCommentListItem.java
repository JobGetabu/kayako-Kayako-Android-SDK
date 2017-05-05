package com.kayako.sdk.android.k5.common.adapter.messengerlist.view;

import android.support.annotation.NonNull;

import com.kayako.sdk.android.k5.common.adapter.messengerlist.MessengerListType;

public class InputFeedbackCommentListItem extends InputFieldlListItem {

    private String instructionMessage;
    private RATING rating;
    private OnAddFeedbackCommentCallback onAddFeedbackComment;

    public InputFeedbackCommentListItem(@NonNull String instructionMessage, @NonNull RATING rating, @NonNull OnAddFeedbackCommentCallback onAddFeedbackComment) {
        super(MessengerListType.INPUT_FIELD_FEEDBACK_COMMENT);
        this.instructionMessage = instructionMessage;
        this.rating = rating;
        this.onAddFeedbackComment = onAddFeedbackComment;

        if (instructionMessage == null || rating == null || onAddFeedbackComment == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    public InputFeedbackCommentListItem(@NonNull String instructionMessage, @NonNull String submittedComment) {
        super(MessengerListType.INPUT_FIELD_FEEDBACK_COMMENT, submittedComment);
        this.instructionMessage = instructionMessage;

        if (submittedComment == null || instructionMessage == null) {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    public String getInstructionMessage() {
        return instructionMessage;
    }

    public RATING getRating() {
        return rating;
    }

    public OnAddFeedbackCommentCallback getOnAddFeedbackComment() {
        return onAddFeedbackComment;
    }

    public enum RATING {
        GOOD, BAD
    }

    public interface OnAddFeedbackCommentCallback {
        void onAddFeedbackComment(RATING rating, String feedback);
    }
}