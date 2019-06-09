package com.rkeenan.components.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.rkeenan.model.AppointmentModel;
import com.rkeenan.util.AppointmentUtil;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables = SlingHttpServletRequest.class)
public class PolicyAppointmentListModel {

    private String policyNum;
    private String policyPageLink;
    private List<AppointmentModel> appointments;

    @Inject
    private Resource resource;

    @Inject
    @Optional
    private Resource currentResource;

    @PostConstruct
    protected void init() {
        if (currentResource == null) {
            currentResource = resource;
        }
        Resource policyPage = AppointmentUtil.getPolicyPage(currentResource);
        if (policyPage == null) {
            return;
        }
        policyNum = policyPage.getName();
        policyPageLink = policyPage.getPath();
        appointments = new ArrayList<>();
        Iterator<Resource> children = policyPage.listChildren();
        while (children.hasNext()) {
            appointments.add(children.next().adaptTo(AppointmentModel.class));
        }
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public String getPolicyPageLink() {
        return policyPageLink;
    }

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }
}
