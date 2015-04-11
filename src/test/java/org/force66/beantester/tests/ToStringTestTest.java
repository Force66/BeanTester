/*
 * This software is licensed under the Apache License, Version 2.0
 * (the "License") agreement; you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.force66.beantester.tests;

import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToStringTestTest {
	
	ToStringTest test;
	ValueGeneratorFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new ValueGeneratorFactory();
		test = new ToStringTest(factory);
	}

	@Test
	public void test() {
		Assert.assertTrue(test.testBeanClass(TestBean.class, null) == true);
		
		TestBean.exceptionToThrow = new RuntimeException("splat");
		try {
			test.testBeanClass(TestBean.class, null);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("toString() failed execution"));
		}
		
		TestBean.exceptionToThrow = null;
		TestBean.calledWithValue = false;
		test.testBeanClass(TestBean.class, null);
		Assert.assertTrue(TestBean.calledWithValue);
		
		TestBean.exceptionToThrow = null;
		TestBean.fieldExceptionToThrow = new RuntimeException("splat");
		test.setFailureReason("foo");
		Assert.assertTrue(test.testBeanClass(TestBean.class, null) == true);
		Assert.assertTrue(test.getFailureReason() == null);
		
		TestBean.toStringNullInd = true;		
		Assert.assertTrue(test.testBeanClass(TestBean.class, null) == false);
		Assert.assertTrue(test.getFailureReason() != null);
		
	}
	
	public static class TestBean {
		
		private static RuntimeException exceptionToThrow=null;
		private static RuntimeException fieldExceptionToThrow=null;
		private static boolean toStringNullInd = false;
		private static boolean calledWithValue = false;
		private String bogusField;

		@Override
		public String toString() {
			if (exceptionToThrow != null) {
				throw exceptionToThrow;
			}
			if (toStringNullInd)   return null;
			return super.toString();
		}

		public String getBogusField() {
			return bogusField;
		}

		public void setBogusField(String bogusField) {
			if (fieldExceptionToThrow != null) {
				throw fieldExceptionToThrow;
			}
			if (bogusField != null) {
				calledWithValue = true;
			}
			this.bogusField = bogusField;
		}
		
	}

}
