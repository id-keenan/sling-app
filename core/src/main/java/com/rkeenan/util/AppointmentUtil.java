package com.rkeenan.util;

import java.util.HashMap;
import java.util.Map;

import com.rkeenan.model.AppointmentModel;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.rkeenan.Constants.APPT_DETAIL_PAGE_RES_TYPE;
import static com.rkeenan.Constants.POLICY_PAGE_RES_TYPE;

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
            log.error("Root does not exist.");
            return null;
        }
        try {
            Map<String, Object> policyMap = new HashMap<>();
            policyMap.put(ResourceResolver.PROPERTY_RESOURCE_TYPE, POLICY_PAGE_RES_TYPE);
            Resource policyPath = ResourceUtil.getOrCreateResource(resourceResolver, createPath(root.getPath(), model.getPolicyNum()), policyMap, "", true);
            int numChild = IteratorUtils.size(policyPath.listChildren());
            Resource appointmentResource = ResourceUtil.getOrCreateResource(resourceResolver, createPath(policyPath.getPath(), Integer.toString(numChild + 1)), addResType(model.getPropertyMap()), "", true);
            return appointmentResource.getPath();
        } catch (PersistenceException e) {
            log.error("Error persisting data. User is not logged in (probably)");
        }

        return "";
    }

    public static Resource getAppointmentDetailPage(Resource resource) {
        if (resource == null) {
            return null;
        }
        if (resource.isResourceType(APPT_DETAIL_PAGE_RES_TYPE)) {
            return resource;
        } else {
            return getAppointmentDetailPage(resource.getParent());
        }
    }

    public static Resource getPolicyPage(Resource resource) {
        if (resource == null) {
            return null;
        }
        if (resource.isResourceType(POLICY_PAGE_RES_TYPE)) {
            return resource;
        } else {
            return getPolicyPage(resource.getParent());
        }
    }

    private static Map<String, Object> addResType(Map<String, Object> properties) {
        properties.put(ResourceResolver.PROPERTY_RESOURCE_TYPE, APPT_DETAIL_PAGE_RES_TYPE);
        return properties;
    }

    private static String createPath(String... parts) {
        return StringUtils.join(parts, "/");
    }
}
