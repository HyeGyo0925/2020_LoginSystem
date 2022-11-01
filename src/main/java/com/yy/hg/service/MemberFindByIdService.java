package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.Member;

@Service("MemberFindByIdService")
public class MemberFindByIdService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private MemberDao dao;
	
	@Transactional
	public Member findById(Long id)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectById(id);
		if (member == null) {
			throw new MemberNotFoundException("회원 없음 : " + id);
		}
		return member;
	}
	
	// @Autowired
	// private MemberDao memberDao;
	//
	// public Member findById(Long id)
	// {
	// Member member = memberDao.selectById(id);
	// if (member == null) {
	// throw new MemberNotFoundException("회원 없음 : " + id);
	// }
	// return member;
	// }
}
