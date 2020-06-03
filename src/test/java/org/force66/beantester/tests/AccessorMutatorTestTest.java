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



import org.force66.beantester.DefaultBeanTesterConfiguration;
import org.force66.beantester.testbeans.OddTypesBean;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccessorMutatorTestTest {
	
	AccessorMutatorTest test;
	ValueGeneratorFactory factory;
	DefaultBeanTesterConfiguration config;

	@Before
	public void setUp() throws Exception {
		factory = new ValueGeneratorFactory();
		config = new DefaultBeanTesterConfiguration();
		test = new AccessorMutatorTest(factory, config.getFieldExclusionSet());
	}

	@Test
	public void testBasic() throws Exception {
		test.setFailureReason("foo");
		boolean result = test.testBeanClass(OddTypesBean.class, null);
		Assert.assertTrue(test.getFailureReason() == null);
		Assert.assertTrue(result);
		
		try {
			result = test.testBeanClass(TestBean.class, null);
			Assert.fail();
		} catch (Exception e) {
			// NoOp
		}
		
		result = test.testBeanClass(TestBean2.class, null);
		Assert.assertTrue(test.getFailureReason() != null);
		Assert.assertTrue(!result);
		
		result = test.testBeanClass(TestBean3.class, null);
		Assert.assertTrue(TestBean3.notNullSetInd);
		Assert.assertTrue(TestBean3.nullSetInd);
	}
	
	public static class TestBean {
		
		public String getExceptionTest() {
			throw new RuntimeException("splat");
		}
		
	}
	
	public static class TestBean2 {
		
		private String foo;

		public String getFoo() {
			return "foo";
		}

		public void setFoo(String foo) {
			this.foo = foo;
		}
		
	}
	
	public static class TestBean3 {
		private String foo;
		private static boolean nullSetInd = false;
		private static boolean notNullSetInd = false;

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			if (foo == null) {
				nullSetInd = true;
			}
			else {
				notNullSetInd = true;
			}
			this.foo = foo;
		}
	}

}
