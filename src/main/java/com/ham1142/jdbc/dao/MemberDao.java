package com.ham1142.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.ham1142.command.MCommand;
import com.ham1142.jdbc.dto.MemberDto;
import com.mysql.cj.xdevapi.Result;

public class MemberDao {

	String driverName = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/spring_projectdb";
	String username = "root";
	String password = "12345";
	
	// 회원가입, 회원 탈퇴, 회원리스트조회, 회원검색, 회원정보수정
	
	//1. 회원가입 매서드 만들기 - 반환타입없이 일단 만들어보기
	public int joinMember(String mid, String mpw , String mname, String memail) { // 회원가입 매소드
		
		String sql = "INSERT INTO members(mid, mpw, mname, memail) VALUES(?,?,?,?)";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int success = 0;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			pstmt.setString(2, mpw);
			pstmt.setString(3, mname);
			pstmt.setString(4, memail);
			
			success = pstmt.executeUpdate();
			
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
	
	return success;
	}
	
	
	
public int deleteMember(String mid) { // 회원탈퇴 매소드
		
		String sql = "DELETE FROM members WHERE mid=?";
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int success = 0;
		
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mid);
			
			success = pstmt.executeUpdate();
			
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
	
	return success;
	}

public MemberDto searchMember(String searchId) { // 회원정보조회 매소드
	
	String sql = "SELECT * FROM members WHERE mid=?";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberDto memberDto = null; // 선언부는 밖으로 빼고 초기화는 안으로
	
	
	try {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, username, password);
		pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, searchId);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {//참이면 레코드 1개가 반환되었다는 뜻
			String mid = rs.getString("mid");
			String mpw = rs.getString("mpw");
			String mname = rs.getString("mname");
			String memail = rs.getString("memail");
			String mdate = rs.getString("mdate"); // 시간이지만 String 해도 괜찬음
			
			memberDto = new MemberDto(mid, mpw, mname, memail, mdate);
			//초기화는 안으로(초기화)
		} 		
			
			
			
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			
			if(rs != null) {
				rs.close();
			}
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

return memberDto;
}

public ArrayList<MemberDto> Listmember(){ // 모든 회원리스트 조회 메소드
	
	String sql = "SELECT * FROM members";

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	MemberDto memberDto = null; // 선언부는 밖으로 빼고 초기화는 안으로
	ArrayList<MemberDto> memberDtos = new ArrayList<MemberDto>(); // < >이라고 되어있으면 컬렉션 자료타입 - ArrayList : 여러개의 DTO를 하나로 묶기 위한 자료타입
	
	try {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, username, password);
		pstmt = conn.prepareStatement(sql);

		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String mid = rs.getString("mid");
			String mpw = rs.getString("mpw");
			String mname = rs.getString("mname");
			String memail = rs.getString("memail");
			String mdate = rs.getString("mdate"); // 시간이지만 String 해도 괜찬음
			
			
			memberDto = new MemberDto(mid, mpw, mname, memail, mdate);
			//초기화는 안으로(초기화)
			memberDtos.add(memberDto);	
		} 		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			
			if(rs != null) {
				rs.close();
			}
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

	return memberDtos;

}

}
	