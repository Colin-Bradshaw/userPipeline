/**
 * 
 */
package com.ss.usermsvc.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Colin Bradshaw
 *
 */
@Service
public class ConnectionUtil {
	public final String driver = "com.mysql.cj.jdbc.Driver";
	public String url = "jdbc:mysql://localhost:3306/utopia";
	public String username = "";
	public String password = "";

	@Bean
	public Connection getConnection() {
		if (System.getenv("UTOPIA_DB_UNAME") != null){
			username = System.getenv("UTOPIA_DB_UNAME");
		}
		if (System.getenv("UTOPIA_DB_PASS") != null){
			password = System.getenv("UTOPIA_DB_PASS");
		}
		if (System.getenv("DB_LOC") != null){
			url = System.getenv("DB_LOC");
		}
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(Boolean.FALSE);
			return conn;
		} catch (ClassNotFoundException c){
			c.printStackTrace();
		} catch (SQLException s){
			s.printStackTrace();
		}
		return null;
	}

}
