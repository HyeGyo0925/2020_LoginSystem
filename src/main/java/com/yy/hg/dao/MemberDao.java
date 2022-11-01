package com.yy.hg.dao;

import java.util.List;

import com.yy.hg.model.Member;

// 자동 매퍼 생성 기능
public interface MemberDao
{
	public List<Member> selectList();
	public Member selectByEmail(String email);
	public Member selectById(Long id);
	public int selectCount();
	public void insertMember(final Member member);
	public void deleteMember(Long id);
	public void updateMember(Member member);
}
