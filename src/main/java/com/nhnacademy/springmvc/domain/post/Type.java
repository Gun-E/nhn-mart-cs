package com.nhnacademy.springmvc.domain.post;

public enum Type {
    COMPLAINT("불만 접수"),
    OFFER("제안"),
    REFUND_EXCHANGE("환불/교환"),
    PRAISE("칭찬해요"),
    OTHER("기타 문의");

    private final String displayName;

    Type(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
