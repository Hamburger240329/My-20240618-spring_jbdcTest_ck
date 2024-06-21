package com.ham1142.command;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.ui.Model;

import com.ham1142.jdbc.dao.MemberDao;
import com.ham1142.jdbc.dto.MemberDto;

public class MListCommand implements MCommand {

	@Override
	public int execute(Model model) {
		// TODO Auto-generated method stub
		
		MemberDao memberDao = new MemberDao();
		ArrayList<MemberDto> mDtos = memberDao.Listmember();
		
		model.addAttribute("mDtos", mDtos);
		
		return 0;
	}

}
