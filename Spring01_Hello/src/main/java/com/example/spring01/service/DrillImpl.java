package com.example.spring01.service;

import org.springframework.stereotype.Component;

@Component
public class DrillImpl implements Drill{

	@Override
	public void hole() {
		System.out.println("윙~~~");
		
	}

}
