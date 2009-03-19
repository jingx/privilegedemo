package com.rbac.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rbac.models.Privilege;

public class RefreshPrivilegesServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String roleStr = request.getParameter("roleid");
		int roleid = Integer.parseInt(roleStr);
		ArrayList<Privilege> plist = new Privilege().getAll();
		ArrayList<Privilege> plistAvailable = Privilege.findByRoleId(roleid);
		
		HttpSession session = request.getSession();
		
		session.setAttribute("_plist", plist);
		session.setAttribute("_plistAvailable", plistAvailable);
		
		
		response.sendRedirect("../_privileges.jsp");
		
	}



}
