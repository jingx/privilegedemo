package com.rbac.models.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.rbac.models.Role;

public class TestRole extends TestCase {
	
	public void testGetAll(){
		Role r = new Role();
		ArrayList<Role> list = r.getAll();
		assertNotNull(list);
		assertEquals("Admin", list.get(0).getName());
		assertEquals("User", list.get(1).getName());

		
	}

}
