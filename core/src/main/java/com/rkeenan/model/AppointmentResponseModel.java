package com.rkeenan.model;

public class AppointmentResponseModel {

    private boolean success;
    private String pagePath;

    public AppointmentResponseModel(boolean success, String pagePath) {
        this.success = success;
        this.pagePath = pagePath;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getPagePath() {
        return pagePath;
    }
}
