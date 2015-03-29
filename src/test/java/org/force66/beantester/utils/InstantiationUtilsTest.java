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

import javax.xml.datatype.XMLGregorianCalendar;

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

}
