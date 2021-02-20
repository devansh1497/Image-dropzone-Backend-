package com.app.enums;

public enum BucketName {

    PROFILE_IMAGE("image-upload-1497");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
