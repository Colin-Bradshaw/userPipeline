/**
 * 
 */
package com.ss.usermsvc.dao;

import java.sql.*;
import java.util.List;

/**
 * @author Colin Bradshaw
 *
 */
public abstract class BaseDAO<T> {

	public BaseDAO(){

	}

	public BaseDAO(Connection con){
		this.conn = con;
	}
	protected static Connection conn;

	
	public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int ct = 1;
			for(Object o: vals) {
				pstmt.setObject(ct, o);
				ct++;
			}
		}
		pstmt.execute();
		conn.commit();
		conn.close();
	}
	
	public Integer saveWithPK(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if(vals!=null) {
			int ct = 1;
			for(Object o: vals) {
				pstmt.setObject(ct, o);
				ct++;
			}
		}
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		conn.commit();
		while(rs.next()) {
			Integer ret = rs.getInt(1); //check if this is 0 or 1;
			conn.close();
			return ret;
		}
		conn.close();
		return null;
	}
	
	public List<T> read(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(vals!=null) {
			int ct = 1;
			for(Object o: vals) {
				pstmt.setObject(ct, o);
				ct++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}
	
	abstract protected List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException; 
}
