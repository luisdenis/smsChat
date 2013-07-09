<%@ include file="/WEB-INF/jsp/jspf/taglibs.jsp" %>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.blve.persistence.ResultList"%>
<%@page import="com.blve.smschat.domain.SubscribeaTbl"%>
<%@page import="java.util.List"%>
<%@page import="com.blve.smschat.domain.SubscribebTbl" %>

	<% 	
	ResultList resultsList = (ResultList)request.getAttribute("results");
	List<SubscribeaTbl> subscribeaTbls =  resultsList.getList();
	Gson gson = new Gson();
	String json = gson.toJson(subscribeaTbls);
	out.println(json);	     
	%>
