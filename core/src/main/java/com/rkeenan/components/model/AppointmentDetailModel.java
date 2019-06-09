package com.rkeenan.components.model;

import com.rkeenan.model.AppointmentModel;
import com.rkeenan.util.AppointmentUtil;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = SlingHttpServletRequest.class)
public class AppointmentDetailModel {

    private AppointmentModel appointmentModel;

    public AppointmentDetailModel(SlingHttpServletRequest request) {
        Resource detailPage = AppointmentUtil.getAppointmentDetailPage(request.getResource());
        appointmentModel = detailPage.adaptTo(AppointmentModel.class);
    }

    public AppointmentModel getAppointment() {
        return this.appointmentModel;
    }
}
