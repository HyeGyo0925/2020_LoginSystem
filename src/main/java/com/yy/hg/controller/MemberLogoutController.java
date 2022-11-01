package com.yy.hg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MemberLogoutController
{
	private static final Logger logger = LoggerFactory.getLogger(MemberLogoutController.class);

	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes redirectAttributes) throws Exception
	{
		if (session.getAttribute("MEMBER") == null) {
			logger.error("로그인 중이 아닙니다.");
			throw new Exception("로그인 중이 아닙니다.");
		}
		session.removeAttribute("MEMBER");
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", "로그아웃 성공");
		return "redirect:/";
	}

	@ExceptionHandler(Exception.class)
	public String exHandler(HttpServletRequest request, RedirectAttributes redirectAttributes, Exception ex)
	{	
		redirectAttributes.addFlashAttribute("ERROR_MSG", ex.getMessage());
		return "redirect:" + request.getHeader("referer");
	}
}
