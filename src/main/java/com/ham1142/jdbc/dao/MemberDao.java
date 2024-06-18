package com.ham1142.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import com.ham1142.jdbc.dto.MemberDto;

public class MemberDao {

	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/spring-projectdb";
	String username = "root";
	String password = "12345";
	
	// 회원가입, 회원 탈퇴, 회원리스트조회, 회원검색, 회원정보수정
	
	//1. 회원가입 매서드 만들기 - 반환타입없이 일단 만들어보기
	//public void joinMember(HttpServletRequest request) { // 회원가입 매소드
	public void joinMember(String mid, String mpw , String mname, String memail) {
		
		String sql = "INSERT INTO members(mid, mpw, mname, memail) VALUES(?,?,?,?)";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			pstmt.setString(3, mname);
			pstmt.setString(4, memail);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();					
				}
				if(conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	
}
