<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbac.models.*" %>
<%
	ArrayList<Privilege> plist = (ArrayList<Privilege>)session.getAttribute("_plist");
	ArrayList<Privilege> plistAvailable = (ArrayList<Privilege>)session.getAttribute("_plistAvailable");
		
%>

<%--!
	boolean isIn

 --%>

<ul>
	<%
		for(Privilege p:plist){
	%>
	<li><input <% if (p.isIn(plistAvailable)) out.println("checked=\"checked\""); %> type="checkbox" name="privileges" id="p_<%=p.getId() %>" value="<%=p.getId()%>"><%= p.getName() %></input></li>	
	<%	 					
		}	 			
	 %>
</ul>