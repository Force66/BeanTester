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

import org.force66.beantester.valuegens.PrimitiveValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ValueGeneratorFactoryTest {
	
	ValueGeneratorFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new ValueGeneratorFactory();
	}

	@Test
	public void testRegisterGenerator() throws Exception {
		try {
			factory.registerGenerator(null, null);
			Assert.fail();
		}
		catch (Exception e) {
			// NoOp
		}
		
		try {
			factory.registerGenerator(String.class, null);
			Assert.fail();
		}
		catch (Exception e) {
			// NoOp
		}
		
		ValueGenerator generator = Mockito.mock(ValueGenerator.class);
		factory.registerGenerator(String.class, generator);
		Assert.assertTrue(generator == factory.forClass(String.class));

	}
	
	@Test
	public void testForClass() throws Exception {
		try {
			factory.forClass(null);
			Assert.fail();
		}
		catch (Exception e) {
			// NoOp
		}
		
		Assert.assertTrue(factory.forClass(Boolean.class) instanceof PrimitiveValueGenerator);
	}

}
