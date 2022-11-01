package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.Member;
import com.yy.hg.model.UpdateRequest;

@Service("MemberUpdateService")
public class MemberUpdateService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private MemberDao dao;

	@Transactional
	public void update(UpdateRequest req)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectById(req.getId());
		if (member == null) {
			throw new MemberNotFoundException("회원 없음 : " + req.getId());
		}
		dao.updateMember(req.toMember());
	}
}
