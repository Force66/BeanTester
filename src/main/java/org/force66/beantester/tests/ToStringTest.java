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

import org.apache.commons.lang3.Validate;
import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.utils.InjectionUtils;
import org.force66.beantester.utils.InstantiationUtils;
import org.force66.beantester.valuegens.ValueGeneratorFactory;

/**
 * Performs a toString test
 * @author D. Ashmore
 *
 */
public class ToStringTest extends BaseBeanTest {
	
	private ValueGeneratorFactory valueGeneratorFactory;
	
	public ToStringTest(ValueGeneratorFactory factory) {
		Validate.notNull(factory, "Null ValueGeneratorFactory not allowed.");
		valueGeneratorFactory=factory;
	}

	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		this.setFailureReason(null);
		Object instance1 = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		try {
			if (instance1.toString() == null) {
				this.setFailureReason("toString() equals null; a favorite pit test plug to see if toString() is being tested properly");
				return false;
			}

			if ("".equals(instance1.toString())) {
				setFailureReason("toString() equals \"\"; a favorite pit test plug to see if toString() is being tested properly");
				return false;
			}
			
			//  Testing if toString() excepts if fields have values....
			InjectionUtils.injectValues(instance1, valueGeneratorFactory, false);
			instance1.toString();
			return true;
		}
		catch (Exception e) {
			throw new BeanTesterException("toString() failed execution", e)
				.addContextValue("bean class", klass.toString());
		}
	}

}
