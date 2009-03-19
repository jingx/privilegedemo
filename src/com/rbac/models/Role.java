package com.rbac.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Role implements IRole {
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Role> getAll() {
		ArrayList<Role> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			ps = conn.prepareStatement("SELECT id, name FROM Roles");
			rs = ps.executeQuery();
			list = new ArrayList();
			while (rs.next()) {
				list.add(new Role(rs.getInt("id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs = null;
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}finally{
				try {
					if(ps!=null){
						ps.close();
						ps = null;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}finally{
					try {
						if(conn!=null){
							conn.close();
							conn = null;
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
			
		}
		return list;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	
	private int id = 0;
	private String name = null;
	private ArrayList<Privilege> privileges = null;
	
	
	
	public ArrayList<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(ArrayList<Privilege> privileges) {
		this.privileges = privileges;
	}

	public Role(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Role findById(int roleId) {
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			ps = conn.prepareStatement("SELECT id, name FROM Roles WHERE id=?");
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			 
			while (rs.next()) {
				role = new Role(rs.getInt("id"), rs.getString("name"));
				role.privileges = Privilege.findByRoleId(role.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
					rs = null;
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}finally{
				try {
					if(ps!=null){
						ps.close();
						ps = null;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}finally{
					try {
						if(conn!=null){
							conn.close();
							conn = null;
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
			
		}
		return role;
	}

	public boolean assignPrivileges(String[] pris) {
		boolean isSuccess = false;
		
		// 1 delete from Roles_Privileges where r_id=?
		Privilege.deleteByRoleId(this.id);
		// 2 insert into Roles_Privileges
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			if(pris != null)
				for(String p : pris){
					ps = conn.prepareStatement("INSERT INTO Roles_Privileges VALUES(Seq_rpid.nextval,?,?)");
					ps.setInt(1, this.id);
					ps.setInt(2, Integer.parseInt(p));
					ps.executeUpdate();
					
					ps.close();//////////////////
					ps = null;
				}
			
			isSuccess = true;
		

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
				try {
					if(ps!=null){
						ps.close();
						ps = null;
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}finally{
					try {
						if(conn!=null){
							conn.close();
							conn = null;
						}
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
				}
			
			
		}
		
		return isSuccess;
	}
	

	
	
	

}
