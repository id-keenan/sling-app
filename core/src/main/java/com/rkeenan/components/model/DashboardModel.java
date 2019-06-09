package com.rkeenan.components.model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.rkeenan.util.AppointmentUtil;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = SlingHttpServletRequest.class)
public class DashboardModel {
    @Inject
    private Resource resource;

    private String link;
    private boolean show;

    @PostConstruct
    protected void init() {
        Resource dashboardPage = AppointmentUtil.getDashboardPage(resource);
        if (resource.getParent() != null && resource.getParent().getPath().equals(dashboardPage.getPath())) {
            this.show = false;
        } else {
            this.link = dashboardPage.getPath();
            this.show = true;
        }
    }

    public String getLink() {
        return link;
    }

    public boolean isShow() {
        return show;
    }
}
