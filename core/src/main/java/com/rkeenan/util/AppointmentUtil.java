package com.rkeenan.util;

import java.util.HashMap;

import com.rkeenan.model.AppointmentModel;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppointmentUtil {

    private static final Logger log = LoggerFactory.getLogger(AppointmentUtil.class);

    public static final String appointmentRoot = "/content/sling-app/appointments";

    /**
     * Creates the appointment and returns the path to the page to view the appointment.
     *
     * @param model
     * @param resourceResolver
     * @return
     */
    public static String createAppointment(AppointmentModel model, ResourceResolver resourceResolver) {
        Resource root = resourceResolver.getResource(appointmentRoot);
        if (root == null) {
            //shit went wrong, fix it
            return null;
        }
        try {
            Resource policyPath = ResourceUtil.getOrCreateResource(resourceResolver, createPath(root.getPath(), model.getPolicyNum()), new HashMap<>(), "", true);
            int numChild = IteratorUtils.size(policyPath.listChildren());
            Resource appointmentResource = ResourceUtil.getOrCreateResource(resourceResolver, createPath(policyPath.getPath(), Integer.toString(numChild + 1)), model.getPropertyMap(), "", true);
            return appointmentResource.getPath();
        } catch (PersistenceException e) {
            log.error("Error persisting data. User is not logged in (probably)");
        }

        return "";
    }

    private static String createPath(String... parts) {
        return StringUtils.join(parts, "/");
    }
}
