package com.yy.hg.model;

import java.util.Date;

public class Member
{
	private Long id;
	private String email;
	private String password;
	private String name;
	private String registerDate;
	private String photo;

	public Member()
	{

	}

	public Member(String email, String password, String name, String registerDate, String photo)
	{
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
		this.photo = photo;
	}
	
	public Member(Long id, String email, String password, String name, String registerDate, String photo)
	{
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
		this.photo = photo;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public String getEmail()
	{
		return email;
	}

	public String getPassword()
	{
		return password;
	}

	public String getName()
	{
		return name;
	}

	public String getRegisterDate()
	{
		return registerDate;
	}

	public String getPhoto()
	{
		return photo;
	}

	public void setPhoto(String photo)
	{
		this.photo = photo;
	}

	public boolean checkPassword(String reqPassword)
	{
		return password.equals(reqPassword);
	}

	@Override
	public String toString()
	{
		return "회원 정보 [id = " + id + ", email = " + email + ", password = " + password + ", name = " + name
				+ ", registerDate = " + registerDate + ", photo = " + photo + "]";
	}
}