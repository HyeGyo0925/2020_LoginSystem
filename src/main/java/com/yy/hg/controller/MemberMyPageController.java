package com.yy.hg.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yy.hg.model.Member;
import com.yy.hg.service.MemberFindByEmailService;
import com.yy.hg.service.MemberFindByIdService;

@Controller
public class MemberMyPageController
{
	@Autowired
	private MemberFindByEmailService memberFindByEmailService;
	
	@Autowired
	private MemberFindByIdService memberFindByIdService;
	
	@RequestMapping(value = "/member/mypage", method = RequestMethod.GET)
	public String view(Model model, HttpSession session)
	{
		String email = (String) session.getAttribute("MEMBER");
		Member member = memberFindByEmailService.findByEmail(email);
		model.addAttribute("loginInfo", member);
		return "member/myPage";
	}
	
	// @PathVariable : 경로의 특정 위치 값이 고정되지 않고 달라질 때 사용하는 것
	@RequestMapping(value = "/member/mypage/{id}", method = RequestMethod.GET)
	public String viewById(@PathVariable("id") Long memId, Model model)
	{
		Member member = memberFindByIdService.findById(memId);
		model.addAttribute("loginInfo", member);
		return "member/myPage";
	}
	
	// @PathVariable : 경로의 특정 위치 값이 고정되지 않고 달라질 때 사용하는 것
	@RequestMapping(value = "/member/memberInfo/{id}", method = RequestMethod.GET)
	public String memberViewById(@PathVariable("id") Long memId, Model model)
	{
		Member member = memberFindByIdService.findById(memId);
		model.addAttribute("loginInfo", member);
		return "member/memberInfo";
	}
}
