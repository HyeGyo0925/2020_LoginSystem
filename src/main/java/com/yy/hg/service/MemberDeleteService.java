package com.yy.hg.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yy.hg.dao.MemberDao;
import com.yy.hg.model.Member;

@Service("MemberDeleteService")
public class MemberDeleteService
{
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	private MemberDao dao;

	@Transactional
	public void delete(Long id)
	{
		dao = sqlSessionTemplate.getMapper(MemberDao.class);
		Member member = dao.selectById(id);
		if (member == null) {
			throw new MemberNotFoundException("회원 없음 : " + id);
		}
		dao.deleteMember(id);
	}
}
