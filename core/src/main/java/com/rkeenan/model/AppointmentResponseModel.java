package com.rkeenan.model;

public class AppointmentResponseModel {

    private boolean success;
    private String message;
    private String pagePath;

    public AppointmentResponseModel(boolean success, String pagePath, String message) {
        this.success = success;
        this.message = message;
        this.pagePath = pagePath;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getPagePath() {
        return pagePath;
    }
}
