package com.chl.main;

import java.util.Arrays;

import com.chl.anno.Inheritable;
import com.chl.anno.UnInheritable;

public class AnnotationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Super sub = new Sub();
		System.out.println(Arrays.toString(sub.getClass().getAnnotations()));

	}
	
	@UnInheritable
	@Inheritable
	public static class Super{
		
	}
	
	public static class Sub extends Super{
		
	}

}
