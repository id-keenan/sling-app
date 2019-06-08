package com.rkeenan.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkeenan.model.AppointmentModel;
import com.rkeenan.model.AppointmentResponseModel;
import com.rkeenan.util.AppointmentUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

@SlingServlet(paths = "/appointment-creator")
public class AppointmentCreator extends SlingAllMethodsServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> params = request.getParameterMap();
        AppointmentModel appointment = new AppointmentModel(params);
        String appointmentPath = AppointmentUtil.createAppointment(appointment, request.getResourceResolver());
        AppointmentResponseModel responseModel;
        if (StringUtils.isNotBlank(appointmentPath)) {
            responseModel = new AppointmentResponseModel(true, appointmentPath, "");
        } else {
            responseModel = new AppointmentResponseModel(false, "", "Failed to create appointment.");
        }
        response.getWriter().write(objectMapper.writeValueAsString(responseModel));
    }

}
