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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.force66.beantester.utils.BeanTesterException;

public class ValuePropertyTest implements BeanPropertyTest {

	@Override
	public boolean testProperty(Object bean, PropertyDescriptor descriptor,
			Object value) {
		Validate.notNull(bean, "Null bean not allowed");
		Validate.notNull(descriptor, "Null PropertyDescriptor not allowed");
		
		boolean answer = true;
		if (descriptor.getPropertyType().isPrimitive() && value == null) {
			return answer;  // Null test doesn't apply
		}
		
		boolean fieldExists = FieldUtils.getField(bean.getClass(), descriptor.getName(), true) != null;
		
		try {
			if (descriptor.getWriteMethod() != null ) {
				descriptor.getWriteMethod()
						.invoke(bean, new Object[] { value });	
				answer = testReadValue(bean, descriptor, value);
			}		
			else if (fieldExists) {
				FieldUtils.writeField(bean, descriptor.getName(), value, true);
				answer = testReadValue(bean, descriptor, value);
			}
			
		} catch (Exception e) {
			throw new BeanTesterException("Failed executing assignment test for accessor/mutator", e)
			 .addContextValue("property", descriptor)
			 .addContextValue("value class", value == null ? null : value.getClass().getName())
			 .addContextValue("value", value);
		} 
		return answer;
	}

	private boolean testReadValue(Object bean, PropertyDescriptor descriptor,
			Object value) throws IllegalAccessException,
			InvocationTargetException {
		boolean answer = true;
		if (descriptor.getReadMethod() != null) {
			if (value == null) {
				answer = descriptor.getReadMethod().invoke(bean) == null;
			}
			else {
				answer = value.equals(descriptor.getReadMethod().invoke(bean));
			}
					
		}
		return answer;
	}

}
