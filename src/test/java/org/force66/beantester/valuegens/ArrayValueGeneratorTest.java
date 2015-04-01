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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ArrayValueGeneratorTest {
	
	ArrayValueGenerator generator;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBasic() throws Exception {
		try{
			generator = new ArrayValueGenerator(null);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage() != null);
			Assert.assertTrue(e.getMessage().contains("Null array type not allowed"));
		}
		
		try{
			generator = new ArrayValueGenerator(String.class);
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage() != null);
			Assert.assertTrue(e.getMessage().contains("java.lang.String"));
		}
		
		String[] strArray = new String[0];
		generator = new ArrayValueGenerator(strArray.getClass());
		Object[] objArray = generator.makeValues();
		Assert.assertTrue(objArray.length >= 1);
		Assert.assertTrue(objArray[0] instanceof String[]);
	}
	
	@Test
	public void testCanGenerate() throws Exception {
		String[] strArray = new String[0];
		generator = new ArrayValueGenerator(strArray.getClass());
		
		Assert.assertTrue(generator.canGenerate(strArray.getClass()));
		Assert.assertTrue( !generator.canGenerate(String.class));
	}

}
