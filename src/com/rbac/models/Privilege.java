package com.rbac.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class Privilege implements IPrivilege {
	
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Privilege> getAll(){
		ArrayList<Privilege> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			ps = conn.prepareStatement("SELECT id, name, path FROM \"Privileges\"");
			rs = ps.executeQuery();
			list = new ArrayList();
			while (rs.next()) {
				list.add(new Privilege(rs.getInt("id"), rs.getString("name"), rs.getString("path")));
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
	
	
	private int id = 0;
	private String name = null;
	private String path = null;
	private ArrayList<Role> roles = null;
	public Privilege(int id, String name, String path) {
		super();
		this.id = id;
		this.name = name;
		this.path = path;
	}
	public Privilege() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public static ArrayList<Privilege> findByRoleId(int rid) {
		ArrayList<Privilege> list = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			ps = conn.prepareStatement("SELECT id,name,path FROM \"Privileges\" WHERE id in (SELECT p_id FROM Roles_Privileges WHERE r_id=?)");
			ps.setInt(1, rid);
			rs = ps.executeQuery();
			list = new ArrayList();
			while (rs.next()) {
				list.add(new Privilege(rs.getInt("id"), rs.getString("name"), rs.getString("path")));
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
	public static void deleteByRoleId(int rid) {
		Connection conn = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.20:1521:NETCOP", "scott", "tiger");
			ps = conn.prepareStatement("DELETE FROM Roles_Privileges WHERE r_id=?");
			ps.setInt(1, rid);
			result = ps.executeUpdate();

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
			
		
	}
	
	
	public boolean isIn(ArrayList<Privilege> plist){
		for(Privilege xxx : plist){
			if(xxx.id == this.id){
				return true;
			}
		}
		return false;
	}
	

	
	
	
	
	
	
}
