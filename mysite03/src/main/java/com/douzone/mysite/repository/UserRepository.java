package com.douzone.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo findByEmailAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.findByEmailAndPassword", vo);
	}
	
	public UserVo findByNo(Long userNo) {
		return sqlSession.selectOne("user.findByNo", userNo);
	}
	
	public boolean insert(UserVo vo) {
		return sqlSession.insert("user,insert", vo) == 1;
	}
	

	public boolean update(UserVo vo) {
		return sqlSession.update("user.update", vo) == 1;
	}

//	public void updateNotInPassword(UserVo vo) {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//				
//		try {
//			connection = dataSource.getConnection();
//				
//			String sql = " UPDATE user "
//					+ "SET  "
//					+ "    name = ?, "
//					+ "    gender = ? "
//					+ "WHERE\r\n"
//					+ "    email = ? ";
//			pstmt = connection.prepareStatement(sql);			
//			pstmt.setString(1,vo.getName());
//			pstmt.setString(2,vo.getGender());
//			pstmt.setString(3,vo.getEmail());
//								
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		} finally {
//			try {
//				if(pstmt != null) {
//					pstmt.close();
//				}
//				if(connection != null) {
//					connection.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
