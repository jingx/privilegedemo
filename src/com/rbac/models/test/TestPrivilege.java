package com.rbac.models.test;

import java.util.ArrayList;

import com.rbac.models.Privilege;

import junit.framework.TestCase;

public class TestPrivilege extends TestCase {

	public void testGetAll() {
		Privilege p = new Privilege();
		ArrayList<Privilege> list = p.getAll();
		assertNotNull(list);
		assertEquals(6, list.size());
		assertEquals("Del", list.get(1).getName());
		assertEquals(2, list.get(1).getId());	
	}
	


}
