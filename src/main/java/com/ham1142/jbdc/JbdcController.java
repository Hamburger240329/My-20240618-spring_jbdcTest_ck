package com.ham1142.jbdc;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ham1142.jdbc.dao.MemberDao;

@Controller
public class JbdcController {
	
	@RequestMapping(value = "/test")
	public void test() {
		
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/spring-projectdb";
		String username = "root";
		String password = "12345";
		
		Connection conn = null;
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, username, password);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/join")
	public String join() {
		return "join";
	}
	@RequestMapping(value = "/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		MemberDao memberDao = new MemberDao();
		
		int success = memberDao.joinMember(mid, mpw, mname, memail);
		//success 값이 1이면 sql문 실행 성공 아니면 실패
		
		if(success == 1) {//회원 가입 성공
			model.addAttribute("mid", mid);
			model.addAttribute("mname", mname);
			
			return "joinOk";
		} else {
			model.addAttribute("error", "회원가입이 실패하였습니다. 다시 시도해주세요");
			
			return "join";
//			return "redirect:join";
		}
		
	}
	
	
}
