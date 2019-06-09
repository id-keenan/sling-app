package com.rkeenan.components.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

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
    private Pattern resTypePattern;

    @Inject
    private Resource resource;

    @Inject
    @Optional
    private String resType;

    @Inject
    @Optional
    private boolean recursive;

    @PostConstruct
    protected void init() {
        resTypePattern = Pattern.compile(resType);
        Resource currentPage = AppointmentUtil.getPage(resource);
        children = generateChildList(currentPage);
    }

    private List<Resource> generateChildList(Resource parent) {
        List<Resource> output = new ArrayList<>();
        Iterator<Resource> childResources = parent.listChildren();
        while (childResources.hasNext()) {
            Resource child = childResources.next();
            if (StringUtils.isNotBlank(resType) && matchesResourceType(child)) {
                output.add(child);
                if (recursive) {
                    output.addAll(generateChildList(child));
                }
            } else if (StringUtils.isBlank(resType)){
                output.add(child);
                if (recursive) {
                    output.addAll(generateChildList(child));
                }
            }
        }
        return output;
    }

    private boolean matchesResourceType(Resource input) {
        return resTypePattern.matcher(input.getResourceType()).matches();
    }

    public List<Resource> getChildren() {
        return children;
    }
}
