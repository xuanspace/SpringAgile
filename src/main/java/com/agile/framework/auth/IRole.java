package com.agile.framework.auth;

import com.agile.model.Role;

public interface IRole {

	// The key of the current role
	Integer key();
	
	// Add a child.
	IRole addChild(String child);

	// Get role's children
	IRole getChildren();
	
	// Checks if a  
	boolean hasChildren();
	
	// Add permission to the role.
	IRole addPermission(String name);
	
	// Checks if a permission exists for this role or any child roles.
	boolean hasPermission(String name);
	
	// Get the name of the role.
	String getName();
	
	// Get the role parent.
	IRole getParent();
	
	// Set the role parent.
	IRole setParent(Role role);
	
}
