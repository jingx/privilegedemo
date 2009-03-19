<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.rbac.models.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'assignPrivileges.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/applications.css">
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
		function refresh_p(roleid){
			var id = roleid.split("_")[1];
			$("#privileges").load("servlet/RefreshPrivilegesServlet?roleid="+id);
		}
	
	
	</script>
  </head>
  
  <body id="assign_privileges">
	<% 
		ArrayList<Role> rlist = (ArrayList<Role>)session.getAttribute("rlist");
		ArrayList<Privilege> plist = (ArrayList<Privilege>)session.getAttribute("plist");
	 %>
	 
	 <form method="post" action="servlet/SaveRolePrivilegesServlet">
	 <div id="framework">
	 	<div id="roles">
	 		<ul>
	 			<%
	 				for(Role r:rlist){
	 			%>
	 			<li><input onclick="refresh_p(this.id);" type="radio" name="role" id="role_<%=r.getId() %>" value="<%=r.getId()%>"><%= r.getName() %></input></li>	
	 			<%	 					
	 				}	 			
	 			 %>
	 		</ul>
	 	</div>
	 	<div id="privileges">
	 		<ul>
	 			<%
	 				for(Privilege p:plist){
	 			%>
	 			<li><input type="checkbox" name="privileges" id="p_<%=p.getId() %>" value="<%=p.getId()%>"><%= p.getName() %></input></li>	
	 			<%	 					
	 				}	 			
	 			 %>
	 		</ul>
	 	</div>	 
	 	
	 </div>
	 <div><input type="submit" /></div>
	 
	 </form>
	 
  </body>
</html>
