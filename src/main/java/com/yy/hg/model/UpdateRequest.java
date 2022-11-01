package com.yy.hg.model;

import org.springframework.web.multipart.MultipartFile;

public class UpdateRequest
{
	private Long id;
	private String name;
	private MultipartFile photofile;

	public UpdateRequest()
	{

	}

	public UpdateRequest(Long id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public MultipartFile getPhotofile()
	{
		return photofile;
	}

	public void setPhotofile(MultipartFile photofile)
	{
		this.photofile = photofile;
	}

	public Member toMember()
	{
		return new Member(id, null, null, name, null, null);
	}
}
