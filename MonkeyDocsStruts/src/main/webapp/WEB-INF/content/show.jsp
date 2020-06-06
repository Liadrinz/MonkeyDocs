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
    response.sendRedirect(String.format("http://monkeydoc.liadrinz.cn/rest/%s/%d.json?%s", request.getAttribute("name"), request.getAttribute("id"), builder.toString()));
%>