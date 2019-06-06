<%@page session="false" %><%
%><%@page import="org.apache.sling.api.resource.ResourceUtil,
                org.apache.sling.api.resource.ValueMap" %><%
%><%@taglib prefix="sling" uri="http://sling.apache.org/taglibs/sling/1.0" %>
<sling:defineObjects/><%
  request.setAttribute("pageResource", resource);
%>
<html>
  <head>
    <title>Content Creator</title>
  </head>

  <body>
    <sling:include path="creator" resourceType="sling-app/components/contentCreator" />
    <script
      src="https://code.jquery.com/jquery-3.4.1.min.js"
      integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
      crossorigin="anonymous"></script>
    <script src="/etc/clientlibs/sling-app/content-creator.js"></script>
  </body>
</html>
