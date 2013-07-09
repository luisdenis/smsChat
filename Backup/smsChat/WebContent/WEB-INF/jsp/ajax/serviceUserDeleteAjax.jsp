<%@page import="com.google.gson.Gson"%>

<%
String result = (String)request.getAttribute("result");
Gson gson = new Gson();
String json = gson.toJson(result);
out.println(json);	     
 %>
 