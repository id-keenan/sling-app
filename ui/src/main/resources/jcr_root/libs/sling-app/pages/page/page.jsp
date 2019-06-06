<%@page session="false" %><%
%><%@page import="org.apache.sling.api.resource.ResourceUtil,
                org.apache.sling.api.resource.ValueMap" %><%
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %>
<sling:defineObjects/><%
  request.setAttribute("pageResource", resource);
%>
<html>
  <head>
    <title><%= resource.getValueMap().get("title", "Title") %></title>
  </head>

  <body>
    <sling:include path="form" resourceType="sling-app/components/form" />
    <sling:include path="output" resourceType="sling-app/components/output" />
  </body>
</html>
