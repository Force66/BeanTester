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
package org.force66.beantester;

import org.junit.Assert;

import org.force66.beantester.testbeans.CollectionsBean;
import org.force66.beantester.testbeans.DateBean;
import org.force66.beantester.testbeans.PrimitiveTypeBean;
import org.junit.Before;
import org.junit.Test;

public class BeanTesterTest {
	
	BeanTester beanTester;

	@Before
	public void setUp() throws Exception {
		beanTester = new BeanTester();
		CallingRegistry.getCalledSet().clear();
	}

	@Test
	public void testPrimitiveTypes() throws Exception {
		beanTester.testBean(PrimitiveTypeBean.class);
		Assert.assertTrue(CallingRegistry.getCalledSet().size() == 20);
	}
	
	@Test
	public void testCollectionTypes() throws Exception {
		beanTester.testBean(CollectionsBean.class);
		Assert.assertTrue(CallingRegistry.getCalledSet().size() == 20);
	}
	
	@Test
	public void testDateTypes() throws Exception {
		beanTester.testBean(DateBean.class);
		Assert.assertTrue(CallingRegistry.getCalledSet().size() == 16);
	}

}
