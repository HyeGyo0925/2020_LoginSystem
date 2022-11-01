package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.Member;

@Service("MemberFindByEmailService")
public class MemberFindByEmailService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private MemberDao dao;
	
	@Transactional
	public Member findByEmail(String email)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectByEmail(email);
		if (member == null) {
			throw new MemberNotFoundException("회원 없음 : " + email);
		}
		return member;
	}
	
	// @Autowired
	// private MemberDao memberDao;
	//
	// public Member findByEmail(String email)
	// {
	// Member member = memberDao.selectByEmail(email);
	// if (member == null) {
	// throw new MemberNotFoundException("회원 없음 : " + email);
	// }
	// return member;
	// }
}
