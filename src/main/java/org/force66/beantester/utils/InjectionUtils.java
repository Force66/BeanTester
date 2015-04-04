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

import java.beans.PropertyDescriptor;

import org.apache.commons.beanutils.PropertyUtils;
import org.force66.beantester.valuegens.ValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;
/**
 * Injects a given bean with generated values.
 * @author D. Ashmore
 *
 */
public class InjectionUtils {
	
	public static void injectValues(Object bean, ValueGeneratorFactory valueGeneratorFactory, boolean reportExceptions) {
		
		ValueGenerator<?> generator;
		Object[] generatedValues = null;
		for (PropertyDescriptor descriptor: PropertyUtils.getPropertyDescriptors(bean)) {
			try {
				generator = valueGeneratorFactory.forClass(descriptor.getPropertyType());
				if (generator != null && descriptor.getWriteMethod() != null) {
					generatedValues = generator.makeValues();
					
						descriptor.getWriteMethod().invoke(bean, generatedValues[0]);
					
				}
			} catch (Exception e) {
				if (reportExceptions) {
					throw new BeanTesterException("Error setting property value", e)
						.addContextValue("class", bean.getClass().getName())
						.addContextValue("field", descriptor.getName())
						.addContextValue("value", generatedValues != null && generatedValues.length > 0 ? generatedValues[0] : null);
				}
			} 
		}
	}
	
}
