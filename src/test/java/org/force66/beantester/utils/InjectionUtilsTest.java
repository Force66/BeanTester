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
package org.force66.beantester.utils;


import org.force66.beantester.testbeans.DateBean;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InjectionUtilsTest {
	
	ValueGeneratorFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new ValueGeneratorFactory();
	}

	@Test
	public void test() {
		DateBean bean = new DateBean();
		InjectionUtils.injectValues(bean, factory, false);
		Assert.assertTrue(bean.getUtilDateValue() != null);
		Assert.assertTrue(bean.getGregorianCalendarValue() != null);
		Assert.assertTrue(bean.getSqlDateValue() != null);
		Assert.assertTrue(bean.getSqlTimestampValue() != null);
		Assert.assertTrue(bean.getXmlGregorianCalendarValue() != null);
		
		try {
			InjectionUtils.injectValues(new TestBean(), factory, true);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("Error setting property value"));
			Assert.assertTrue(e.getMessage().contains("class="));
			Assert.assertTrue(e.getMessage().contains("field="));
			Assert.assertTrue(e.getMessage().contains("value="));
		}
	}
	
	public static class TestBean {
		
		private String foo;

		public String getFoo() {
			return foo;
		}

		public void setFoo(String foo) {
			throw new RuntimeException ("splat");
		}
		
	}


}
