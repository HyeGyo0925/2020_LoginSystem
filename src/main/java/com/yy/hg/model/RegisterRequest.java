package com.yy.hg.model;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class RegisterRequest
{
	private String email;
	private String password;
	private String confirmPassword;
	private String name;
	private String photo;
	private MultipartFile photofile;

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public MultipartFile getPhotofile()
	{
		return photofile;
	}

	public void setPhotofile(MultipartFile photofile)
	{
		this.photofile = photofile;
	}

	public boolean isPasswordEqualToConfirmPassword()
	{
		return password.equals(confirmPassword);
	}

	public Member toMember()
	{
		return new Member(email, password, name, String.valueOf(new Date()), photo);
	}

	@Override
	public String toString()
	{
		return "회원 가입 정보 [email = " + email + ", password = " + password + ", confirmPassword = " + confirmPassword
				+ ", name = " + name + ", photo = " + photo + "]";
	}
}