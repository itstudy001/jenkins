package com.adamsoft.jenkins;

import org.springframework.stereotype.Service;

@Service
public class Calculator 
{

	public int sum(int a, int b)
	{
		System.out.println(a);
		return a + b;
	}
}
