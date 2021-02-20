package com.app.enums;

public  enum AwsKeys {

    ACCESS("AWSACCESSKEY"),
    SECRET("AWSSECRETKEY");

    String type;

    AwsKeys(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
