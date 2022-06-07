package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql =
				" SELECT no, name "
				+ "FROM user "
				+ "WHERE email = ? "
				+ "  AND password = ?";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
						
			if(rs.next()) {				
				UserVo userVo = new UserVo();
				userVo.setNo(rs.getLong(1));
				userVo.setName(rs.getString(2));
		
				result = new UserVo();
				result = userVo;
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;	
	}
	
	public UserVo findByNo(Long no) {
		UserVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			connection = getConnection();
			
			String sql =
				" SELECT "
				+ "	no, name, email, gender "
				+ "FROM user "
				+ "WHERE "
				+ " no = ? ";
			pstmt = connection.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
						
			if(rs.next()) {				
				UserVo userVo = new UserVo();
				userVo.setNo(rs.getLong(1));
				userVo.setName(rs.getString(2));
				userVo.setEmail(rs.getString(3));
				userVo.setGender(rs.getString(4));
		
				result = new UserVo();
				result = userVo;
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}	
		
		return result;
	}
	
	public boolean insert(UserVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		try {
			connection = getConnection();
				
			String sql = " insert  "
					+ " into user "
					+ " values (null, ?, ?, ?, ?,now()); ";
			pstmt = connection.prepareStatement(sql);			
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getEmail());
			pstmt.setString(3,vo.getPassword());
			pstmt.setString(4,vo.getGender());
			
					
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	

	public void update(UserVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		try {
			connection = getConnection();
			String sql =null;
			if(vo.getPassword() == "") {
				sql = " UPDATE user "
						+ "SET  "
						+ "    name = ?, "
						+ "    gender = ? "
						+ "WHERE\r\n"
						+ "    email = ? ";
				pstmt = connection.prepareStatement(sql);			
				pstmt.setString(1,vo.getName());
				pstmt.setString(2,vo.getGender());
				pstmt.setString(3,vo.getEmail());
			}else {
				sql = " UPDATE user "
						+ "SET  "
						+ "    name = ?, "
						+ "    gender = ?, "
						+ "    password = ? "
						+ "WHERE\r\n"
						+ "    email = ? ";
				
				pstmt = connection.prepareStatement(sql);			
				pstmt.setString(1,vo.getName());
				pstmt.setString(2,vo.getGender());
				pstmt.setString(3,vo.getPassword());
				pstmt.setString(4,vo.getEmail());
			}	
								
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateNotInPassword(UserVo vo) {
		Connection connection = null;
		PreparedStatement pstmt = null;
				
		try {
			connection = getConnection();
				
			String sql = " UPDATE user "
					+ "SET  "
					+ "    name = ?, "
					+ "    gender = ? "
					+ "WHERE\r\n"
					+ "    email = ? ";
			pstmt = connection.prepareStatement(sql);			
			pstmt.setString(1,vo.getName());
			pstmt.setString(2,vo.getGender());
			pstmt.setString(3,vo.getEmail());
								
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	
	/* DataBase Connection */
	private static Connection getConnection() throws SQLException{
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mysql://192.168.10.31:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
			
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: " + e);
		}
		return connection;		
	}


	
	

}
