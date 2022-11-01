package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.LoginRequest;
import com.yy.hg.model.Member;

@Service("MemberLoginService")
public class MemberLoginService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private MemberDao dao;

	@Transactional
	public Member login(LoginRequest req)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectByEmail(req.getEmail());
		if (member == null) {
			throw new MemberNotFoundException("회원 없음 : " + req.getEmail());
		}
		if (!member.checkPassword(req.getPassword())) {
			throw new IdPasswordNotMatchingException("비밀번호 다름");
		}
		return member;
	}
}

