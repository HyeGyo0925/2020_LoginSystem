package com.yy.hg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yy.hg.service.MemberDeleteService;

@Controller
public class MemberDeleteController
{
	@Autowired
	private MemberDeleteService memberDeleteService;
	
	@RequestMapping(value = "/member/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable("id") Long memId, RedirectAttributes redirectAttributes)
	{
		memberDeleteService.delete(memId);
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", memId + "번 회원 삭제 성공");
		return "redirect:/member/list";
	}
	
	@ExceptionHandler(Exception.class)
	public String exHandler(RedirectAttributes redirectAttributes, Exception ex)
	{
		redirectAttributes.addFlashAttribute("ERROR_MSG", ex.getMessage());
		return "redirect:/member/list";
	}
}
