package com.yy.hg.service;

@SuppressWarnings("serial")
public class IdPasswordNotMatchingException extends RuntimeException
{
	public IdPasswordNotMatchingException(String message)
	{
		super(message);
	}
}