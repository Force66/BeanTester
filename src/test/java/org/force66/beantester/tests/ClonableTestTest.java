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

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClonableTestTest {
	
	ClonableTest test;

	@Before
	public void setUp() throws Exception {
		test = new ClonableTest();
	}

	@Test
	public void testBasic() {
		Assert.assertTrue(test.testBeanClass(TestBean.class) == false);
		Assert.assertTrue(StringUtils.isNotEmpty(test.getFailureReason()));
		
		TestBean.returnYourselfAsClone=false;
		Assert.assertTrue(test.testBeanClass(TestBean.class) == true);
		Assert.assertTrue(StringUtils.isEmpty(test.getFailureReason()));
		
		TestBean.exceptionToThrow = new RuntimeException("splat");
		try {
			test.testBeanClass(TestBean.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("clone() execution errors"));
		}
	}
	
	public static class TestBean implements Cloneable {
		
		private static RuntimeException exceptionToThrow=null;
		private static boolean returnYourselfAsClone = true;

		@Override
		public Object clone() throws CloneNotSupportedException {
			if (exceptionToThrow != null) {
				throw exceptionToThrow;
			}
			if (returnYourselfAsClone) {
				return this;
			}
			return super.clone();
		}
		
	}

}
