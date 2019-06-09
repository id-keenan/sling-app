package com.rkeenan.components.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.rkeenan.util.AppointmentUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

@Model(adaptables = SlingHttpServletRequest.class)
public class ChildListModel {
    private List<Resource> children;

    @Inject
    private Resource resource;

    @Inject
    @Optional
    private String resType;

    @PostConstruct
    protected void init() {
        children = new ArrayList<>();
        Resource currentPage = AppointmentUtil.getPage(resource);
        Iterator<Resource> childResources = currentPage.listChildren();
        while (childResources.hasNext()) {
            Resource child = childResources.next();
            if (StringUtils.isNotBlank(resType) && child.isResourceType(resType)) {
                children.add(child);
            } else if (StringUtils.isBlank(resType)){
                children.add(child);
            }
        }
    }

    public List<Resource> getChildren() {
        return children;
    }
}
