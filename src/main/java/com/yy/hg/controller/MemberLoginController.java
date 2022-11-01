package com.yy.hg.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yy.hg.model.LoginRequest;
import com.yy.hg.model.Member;
import com.yy.hg.service.MemberLoginService;

@Controller
@RequestMapping(value = "/member/login")
public class MemberLoginController
{
	private static final Logger logger = LoggerFactory.getLogger(MemberLoginController.class);

	@Autowired
	private MemberLoginService memberLoginService;

	@RequestMapping(method = RequestMethod.GET)
	public String form()
	{
		return "member/loginForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(LoginRequest loginRequest, BindingResult bindingResult, HttpSession session,
			HttpServletResponse response, RedirectAttributes redirectAttributes) throws Exception
	{
		if (bindingResult.hasErrors()) {
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError e : list) {
				logger.error("ObjectError : " + e);
			}
			throw new Exception("로그인 실패");
		}

		Member member = memberLoginService.login(loginRequest);

		if (loginRequest.getSaveEmail() != null) { // 이메일 저장시 쿠키 생성
			Cookie cookie = new Cookie("SAVEEMAIL", member.getEmail());
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
		}
		else { // 이메일 저장 아닐시엔 쿠키 삭제
			Cookie cookie = new Cookie("SAVEEMAIL", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		session.setAttribute("MEMBER", member.getEmail());

		logger.info(member.toString()); // 확인용 로그 출력
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", "로그인 성공");

		return "redirect:./list";
	}

	@ExceptionHandler(Exception.class)
	public String exHandler(HttpServletRequest request, RedirectAttributes redirectAttributes, Exception ex)
	{
		redirectAttributes.addFlashAttribute("REQ_EMAIL", request.getParameter("email"));
		redirectAttributes.addFlashAttribute("ERROR_MSG", ex.getMessage());
		return "redirect:" + request.getHeader("referer");
	}
}
