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

import java.lang.reflect.Constructor;

import org.apache.commons.lang3.Validate;
import org.force66.beantester.utils.BeanTesterException;

public class TemporalValueGenerator implements ValueGenerator<Object> {
	
	private Class<?> temporalType;
	private Constructor<?> longConstructor;
	
	public TemporalValueGenerator(Class<?> type) {
		this.temporalType = type;
		Constructor<?>[] allConstructors = type.getDeclaredConstructors();
		for (Constructor<?> ctor : allConstructors) {
			if (ctor.getParameterTypes().length == 1 
					&& ctor.getParameterTypes()[0].getName().equals("long")) {
				longConstructor = ctor;
			}
		}
		
		Validate.notNull(longConstructor, "Not a temporal object with a long constructor.   class=" +type.getName());
	}

	@Override
	public Object[] makeValues() {
		Object obj = null;
		try {
			obj = longConstructor.newInstance(System.currentTimeMillis());
		} catch (Exception e) {
			throw new BeanTesterException("Error instantiating temporal object with long constructor")
				.addContextValue("class", temporalType.getName());
		} 
		return new Object[]{obj};
	}

	@Override
	public boolean canGenerate(Class<?> targetClass) {
		return temporalType.equals(targetClass);
	}

}
