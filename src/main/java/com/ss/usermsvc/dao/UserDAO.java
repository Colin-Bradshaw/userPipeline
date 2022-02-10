/**
 * 
 */
package com.ss.usermsvc.dao;

import com.ss.usermsvc.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Colin Bradshaw
 *
 */
public class UserDAO extends BaseDAO<User> {
	public UserDAO(){

	}
	public UserDAO(Connection conn){
		super(conn);
	}


	@Override
	protected List<User> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		ArrayList<User> usr = new ArrayList<User>();
		while(rs.next()) {
			usr.add(new User(rs.getInt("id"), rs.getInt("role_id"), rs.getString("given_name"), rs.getString("family_name"), 
					rs.getString("username"), rs.getString("email"), rs.getString("password"), rs.getString("phone")));
		}
		return usr;
	}
}
