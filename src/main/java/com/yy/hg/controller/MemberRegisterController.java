package com.yy.hg.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.yy.hg.model.RegisterRequest;
import com.yy.hg.service.MemberRegisterService;

@Controller
@RequestMapping(value = "/member/register")
public class MemberRegisterController
{
	private static final Logger logger = LoggerFactory.getLogger(MemberRegisterController.class);

	@Autowired
	private MemberRegisterService memberRegisterService;

	@RequestMapping(method = RequestMethod.GET)
	public String form()
	{
		return "member/memberRegForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String submit(RegisterRequest registerRequest, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception
	{
		if (bindingResult.hasErrors()) {
			List<ObjectError> list = bindingResult.getAllErrors();
			for (ObjectError e : list) {
				logger.error("ObjectError : " + e);
			}
			throw new Exception("회원가입 실패");
		}
		
		// 업로드 폴더 시스템 물리적 경로 찾기
		String uploadURI = "/uploadfile/memberphoto";
		String dir = request.getSession().getServletContext().getRealPath(uploadURI);
		
		// 업로드 파일의 물리적 저장
		if (!registerRequest.getPhotofile().isEmpty()) {
			registerRequest.getPhotofile().transferTo(new File(dir, registerRequest.getEmail()));
			registerRequest.setPhoto(registerRequest.getEmail());
		}
		
		memberRegisterService.register(registerRequest);
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", "회원가입 성공");
		return "redirect:/member/login";
	}

	@ExceptionHandler(Exception.class)
	public String exHandler(HttpServletRequest request, RedirectAttributes redirectAttributes, Exception ex)
	{
		redirectAttributes.addFlashAttribute("REQ_EMAIL", request.getParameter("email"));
		redirectAttributes.addFlashAttribute("REQ_NAME", request.getParameter("name"));
		redirectAttributes.addFlashAttribute("ERROR_MSG", ex.getMessage());
		return "redirect:" + request.getHeader("referer");
	}
}
