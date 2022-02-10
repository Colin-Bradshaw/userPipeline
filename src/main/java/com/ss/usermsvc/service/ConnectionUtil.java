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
	public String password = "s";

	@Bean
	public Connection getConnection() {
		if (System.getenv("UTOPIA-DB-UNAME") != null){
			username = System.getenv("UTOPIA-DB-UNAME");
		}
		if (System.getenv("UTOPIA-DB-PASS") != null){
			password = System.getenv("UTOPIA-DB-PASS");
		}
		if (System.getenv("DB-LOC") != null){
			url = System.getenv("DB-LOC");
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
