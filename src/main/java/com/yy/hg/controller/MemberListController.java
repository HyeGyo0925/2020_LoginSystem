package com.yy.hg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yy.hg.model.Member;
import com.yy.hg.service.MemberListService;

@Controller
public class MemberListController
{
	@Autowired
	private MemberListService memberListService;

	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String view(Model model)
	{
		List<Member> memberList = memberListService.selectAll();
		model.addAttribute("memberList", memberList);
		
		int totalCount = memberListService.count();
		model.addAttribute("totalCount", totalCount);
		
		return "member/list";
	}
}
