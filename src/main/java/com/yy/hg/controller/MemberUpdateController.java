package com.yy.hg.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yy.hg.model.Member;
import com.yy.hg.model.UpdateRequest;
import com.yy.hg.service.MemberFindByIdService;
import com.yy.hg.service.MemberUpdateService;

@Controller
public class MemberUpdateController
{
	@Autowired
	private MemberUpdateService memberUpdateService;
	
	@Autowired
	private MemberFindByIdService memberFindByIdService;

	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.GET)
	public String form(@PathVariable("id") Long memId, Model model)
	{
		Member member = memberFindByIdService.findById(memId);
		model.addAttribute("name", member.getName());
		model.addAttribute("id", memId);
		return "member/memberUpdateForm";
	}

	@RequestMapping(value = "/member/update/{id}", method = RequestMethod.POST)
	public String update(@PathVariable("id") Long memId, UpdateRequest updateRequest,
			RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception
	{
		// 이메일 주소 얻기
		Member member = memberFindByIdService.findById(memId);
		
		// 업로드 폴더 시스템 물리적 경로 찾기
		String uploadURI = "/uploadfile/memberphoto";
		String dir = request.getSession().getServletContext().getRealPath(uploadURI);

		// 업로드 파일의 물리적 저장
		if (!updateRequest.getPhotofile().isEmpty()) {
			updateRequest.getPhotofile().transferTo(new File(dir, member.getEmail()));
		}

		memberUpdateService.update(updateRequest);
		redirectAttributes.addFlashAttribute("SUCCESS_MSG", memId + "번 회원 수정 성공");
		return "redirect:/member/list";
	}

	@ExceptionHandler(Exception.class)
	public String exHandler(HttpServletRequest request, RedirectAttributes redirectAttributes, Exception ex)
	{
		redirectAttributes.addFlashAttribute("ERROR_MSG", ex.getMessage());
		return "redirect:" + request.getHeader("referer");
	}
}
