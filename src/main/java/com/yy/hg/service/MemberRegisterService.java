package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.Member;
import com.yy.hg.model.RegisterRequest;

@Service("MemberRegisterService")
public class MemberRegisterService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	private MemberDao dao;
	
	@Transactional
	public void register(RegisterRequest req)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectByEmail(req.getEmail());
		if (member != null) {
			throw new AlreadyExistingMemberException("이메일 중복 : " + req.getEmail());
		}
		dao.insertMember(req.toMember());
	}
	
	// @Autowired
	// private MemberDao memberDao;
	//
	// public void register(RegisterRequest req) {
	// Member member = memberDao.selectByEmail(req.getEmail());
	// if (member != null) {
	// throw new AlreadyExistingMemberException("이메일 중복 : " + req.getEmail());
	// }
	// memberDao.insert(req.toMember());
	// }
}
