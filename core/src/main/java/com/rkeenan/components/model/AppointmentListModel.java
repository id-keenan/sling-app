package com.rkeenan.components.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rkeenan.model.AppointmentModel;
import com.rkeenan.util.AppointmentUtil;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = SlingHttpServletRequest.class)
public class AppointmentListModel {

    private String policyNum;
    private List<AppointmentModel> appointments;

    public AppointmentListModel(SlingHttpServletRequest request) {
        Resource currentPage = request.getResource();
        Resource policyPage = AppointmentUtil.getPolicyPage(currentPage);
        policyNum = policyPage.getName();
        appointments = new ArrayList<>();
        Iterator<Resource> children = policyPage.listChildren();
        while (children.hasNext()) {
            appointments.add(children.next().adaptTo(AppointmentModel.class));
        }
    }

    public String getPolicyNum() {
        return policyNum;
    }

    public List<AppointmentModel> getAppointments() {
        return appointments;
    }
}
