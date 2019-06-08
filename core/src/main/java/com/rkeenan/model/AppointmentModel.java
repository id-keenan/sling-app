package com.rkeenan.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentModel {

    private static final String PROP_LOC_DEST = "locationDestination";
    private static final String PROP_LOC_PICKUP = "locationPickup";
    private static final String PROP_POLICY_NUM = "policyNum";
    private static final String PROP_TIME_PICKUP = "timePickup";
    private static final String PROP_TIME_DROPOFF = "timeDropoff";
    private static final String PROP_DRIVER = "driver";
    private static final String PROP_COST = "cost";

    private String locationDestination;
    private String locationPickup;
    private String policyNum;
    private Date timePickup;
    private Date timeDropoff;
    private String driver;
    private String cost;

    public AppointmentModel(Map<String, String[]> params) {
        this.locationDestination = params.get(PROP_LOC_DEST)[0];
        this.locationPickup = params.get(PROP_LOC_PICKUP)[0];
        this.policyNum = params.get(PROP_POLICY_NUM)[0];
        this.timePickup = getDateFromString(params.get(PROP_TIME_PICKUP)[0]);
        this.timeDropoff = getDateFromString(params.get(PROP_TIME_DROPOFF)[0]);
        this.driver = params.get(PROP_DRIVER)[0];
        this.cost = params.get(PROP_COST)[0];
    }

    private Date getDateFromString(String dateString) {
        return Calendar.getInstance().getTime();
    }

    public Map<String, Object> getPropertyMap() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(PROP_LOC_DEST, this.locationDestination);
        properties.put(PROP_LOC_PICKUP, this.locationPickup);
        properties.put(PROP_POLICY_NUM, this.policyNum);
        properties.put(PROP_TIME_PICKUP, this.timePickup);
        properties.put(PROP_TIME_DROPOFF, this.timeDropoff);
        properties.put(PROP_DRIVER, this.driver);
        properties.put(PROP_COST, this.cost);
        return properties;
    }

    public String getLocationDestination() {
        return locationDestination;
    }

    public String getLocationPickup() {
        return locationPickup;
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public Date getTimePickup() {
        return timePickup;
    }

    public Date getTimeDropoff() {
        return timeDropoff;
    }

    public String getDriver() {
        return driver;
    }

    public String getCost() {
        return cost;
    }
}
