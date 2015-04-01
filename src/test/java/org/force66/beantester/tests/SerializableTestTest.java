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

import java.io.Serializable;

import org.force66.beantester.testbeans.CollectionsBean;
import org.force66.beantester.testbeans.PrimitiveTypeBean;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializableTestTest {
	
	SerializableTest test;
	ValueGeneratorFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new ValueGeneratorFactory();
		test = new SerializableTest(factory);
	}

	@Test
	public void testBasic()  throws Exception {
		try {
			test.testBeanClass(TestBean.class, null);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("Bean implements serializable, but the reconstituted bean doesn't equal it's original"));
		}
		
		test.testBeanClass(PrimitiveTypeBean.class, null);
	}
	
	public static class TestBean extends CollectionsBean implements Serializable {

		private static final long serialVersionUID = 8919772156024418655L;
		
	}

}
