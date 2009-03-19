package com.rbac.models;

import java.util.ArrayList;

public interface IRole {
	ArrayList<Role> getAll();

	boolean assignPrivileges(String[] privileges);
}
