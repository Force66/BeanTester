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
package org.force66.beantester.valuegens;

import java.sql.Statement;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InterfaceValueGeneratorTest {
	
	InterfaceValueGenerator generator;

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testBasic() throws Exception {
		try{
			generator = new InterfaceValueGenerator(null);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage() != null);
			Assert.assertTrue(e.getMessage().contains("Null interface type not allowed"));
		}
		
		try{
			generator = new InterfaceValueGenerator(String.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage() != null);
			Assert.assertTrue(e.getMessage().contains("class=java.lang.String"));
		}
		
		generator = new InterfaceValueGenerator(Statement.class);
		Assert.assertTrue(Statement.class.equals(FieldUtils.readField(generator, "interfaceType", true)));
		
		Object[] values = generator.makeValues();
		Assert.assertTrue(values != null);
		Assert.assertTrue(values.length == 1);
		Assert.assertTrue(values[0] instanceof Statement);
	}

}
