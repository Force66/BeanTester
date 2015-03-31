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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ToStringTestTest {
	
	ToStringTest test;

	@Before
	public void setUp() throws Exception {
		test = new ToStringTest();
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
	}
	
	public static class TestBean {
		
		private static RuntimeException exceptionToThrow=null;

		@Override
		public String toString() {
			if (exceptionToThrow != null) {
				throw exceptionToThrow;
			}
			return super.toString();
		}
		
	}

}
