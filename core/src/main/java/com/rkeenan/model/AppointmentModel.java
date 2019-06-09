package com.rkeenan.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = {SlingHttpServletRequest.class, Resource.class})
public class AppointmentModel {

    private static final Logger log = LoggerFactory.getLogger(AppointmentModel.class);

    private static final String PROP_LOC_DEST = "location-destination";
    private static final String PROP_LOC_PICKUP = "location-pickup";
    private static final String PROP_POLICY_NUM = "policy-num";
    private static final String PROP_TIME_PICKUP = "time-pickup";
    private static final String PROP_DATE_PICKUP = "date-pickup";
    private static final String PROP_TIME_DROPOFF = "time-dropoff";
    private static final String PROP_DRIVER = "driver";
    private static final String PROP_COST = "cost";

    private String locationDestination;
    private String locationPickup;
    private String policyNum;
    private Calendar datePickup;
    private String timePickup;
    private String timeDropoff;
    private String driver;
    private String cost;
    private String pageLink;

    public AppointmentModel(SlingHttpServletRequest request) {
        Map<String, String[]> params = request.getParameterMap();
        if (params != null && params.get(PROP_POLICY_NUM) != null) {
            this.locationDestination = params.get(PROP_LOC_DEST)[0];
            this.locationPickup = params.get(PROP_LOC_PICKUP)[0];
            this.policyNum = params.get(PROP_POLICY_NUM)[0];
            this.datePickup = getCalendarFromString(params.get(PROP_DATE_PICKUP)[0]);
            this.timePickup = params.get(PROP_TIME_PICKUP)[0];
            this.timeDropoff = params.get(PROP_TIME_DROPOFF)[0];
            this.driver = params.get(PROP_DRIVER)[0];
            this.cost = params.get(PROP_COST)[0];
        }
    }

    public AppointmentModel(Resource resource) {
        ValueMap properties = resource.getValueMap();
        this.locationDestination = properties.get(PROP_LOC_DEST, "");
        this.locationPickup = properties.get(PROP_LOC_PICKUP, "");
        this.policyNum = properties.get(PROP_POLICY_NUM, "");
        this.datePickup = properties.get(PROP_DATE_PICKUP, Calendar.class);
        this.timePickup = properties.get(PROP_TIME_PICKUP, "");
        this.timeDropoff = properties.get(PROP_TIME_DROPOFF, "");
        this.driver = properties.get(PROP_DRIVER, "");
        this.cost = properties.get(PROP_COST, "");
        this.pageLink = resource.getPath();
    }

    private Calendar getCalendarFromString(String dateString) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parsed = df.parse(dateString);
            Calendar dateTime = Calendar.getInstance();
            dateTime.setTime(parsed);
            return dateTime;
        } catch (ParseException e) {
            log.error("Error parsing date.");
        }
        return Calendar.getInstance();
    }

    public Map<String, Object> getPropertyMap() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(PROP_LOC_DEST, this.locationDestination);
        properties.put(PROP_LOC_PICKUP, this.locationPickup);
        properties.put(PROP_POLICY_NUM, this.policyNum);
        properties.put(PROP_DATE_PICKUP, this.datePickup);
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

    public Calendar getDatePickup() {
        return datePickup;
    }

    public String getTimePickup() {
        return timePickup;
    }

    public String getTimeDropoff() {
        return timeDropoff;
    }

    public String getDriver() {
        return driver;
    }

    public String getCost() {
        return cost;
    }

    public String getPageLink() {
        return pageLink;
    }
}
