package org.force66.beantester.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class HashcodeEqualsTestTest {

	HashcodeEqualsTest test;
	
	@Before
	public void setup(){
		test = new HashcodeEqualsTest();
	}
	
	@Test
	public void testTestBeanClass() {
		
		assertTrue(test.testBeanClass(TestBean.class, new Object[]{1}));
		assertNull(test.getFailureReason());
		
		assertFalse(test.testBeanClass(TestUnequalBean.class, new Object[]{1}));

	}
	
	public static class TestBean{
		
		int myValue;
		
		public TestBean(int value){

			myValue = value;

		}
		
		@Override
		public boolean equals(Object other){
			return myValue == ((TestBean)other).myValue;
		}
		
		@Override
		public int hashCode(){
			return myValue;
		}
	}
	
	public static class TestUnequalBean{
		static volatile int ourValue;
		int myValue;
		public TestUnequalBean(int value){
			myValue = value;
			if(ourValue <= 0){
				ourValue = value;
				myValue = value;
			}else{
				myValue = ++ourValue;
			}
		}
		
		@Override
		public boolean equals(Object other){
			return true;
		}
		
		@Override
		public int hashCode(){
			return myValue;
		}
	}

}
