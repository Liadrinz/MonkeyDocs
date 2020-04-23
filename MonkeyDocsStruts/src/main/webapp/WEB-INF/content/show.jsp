<%@ page import="java.util.Enumeration" %>
<%
    Enumeration<String> paramNames = request.getParameterNames();
    StringBuilder builder = new StringBuilder();
    while (paramNames.hasMoreElements()) {
        String key = paramNames.nextElement();
        String val = request.getParameter(key);
        builder.append(key);
        builder.append("=");
        builder.append(val);
        if (paramNames.hasMoreElements())
            builder.append("&");
    }
    response.sendRedirect(String.format("%d.json?%s", request.getAttribute("id"), builder.toString()));
%>