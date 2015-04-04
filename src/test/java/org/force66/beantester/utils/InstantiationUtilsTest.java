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

import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.force66.beantester.valuegens.ValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Test;

public class InstantiationUtilsTest {

	@Test
	public void testBasic() {
		Object obj = InstantiationUtils.safeNewInstance(Object.class);
		Assert.assertTrue(obj != null);
		
		try {
			InstantiationUtils.safeNewInstance(XMLGregorianCalendar.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage() != null);
			Assert.assertTrue(e.getMessage().contains("Failed to instantiate bean using newInstance()"));
		}
		
		Assert.assertTrue(new InstantiationUtils() != null);
	}
	
	@Test
	public void testConstructorArgs() {
		Integer obj = (Integer)InstantiationUtils.safeNewInstance(Integer.class, new Object[]{"0"});
		Assert.assertTrue(obj != null);
		Assert.assertTrue(obj.toString().equals("0"));
	}
	
	@Test
	public void testXewXMLGregorianCalendar() {
		XMLGregorianCalendar xc = InstantiationUtils.newXMLGregorianCalendar();
		Assert.assertTrue(xc != null);
	}
	
	@Test
	public void testFindPublicConstructor() {
		Assert.assertTrue(InstantiationUtils.findPublicConstructor(Math.class) == null);
	}
	
	@Test
	public void testInstantiationWithFactory() throws Exception {
		ValueGeneratorFactory factory = new ValueGeneratorFactory();
		try {
			InstantiationUtils.safeNewInstance(factory, Math.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("No public constructor found"));
			Assert.assertTrue(e.getMessage().contains(Math.class.getName()));
		}
		
		try {
			InstantiationUtils.safeNewInstance(factory, TestBean.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("No public constructor found"));
			Assert.assertTrue(e.getMessage().contains(TestField.class.getName()));
		}
		
		Map<Class<?>,ValueGenerator<?>> registeredGeneratorMap = 
				(Map<Class<?>,ValueGenerator<?>>)FieldUtils.readField(factory, "registeredGeneratorMap", true);
		registeredGeneratorMap.put(String.class, null);
		
		InstantiationUtils.safeNewInstance(factory, TestBean2.class);
		
	}
	
	public static class TestBean {
		
		public TestBean(TestField field) {}
		
	}
	
	public static class TestField {
		private TestField()  {}
	}
	
	public static class TestBean2 {
		public TestBean2(String field) {}
	}

}
