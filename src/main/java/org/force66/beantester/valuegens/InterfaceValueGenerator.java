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

import java.lang.reflect.Proxy;

import org.apache.commons.lang3.Validate;
import org.force66.beantester.utils.GenericProxyHandler;

/**
 * Will generate proxy values for interfaces used as properties
 * @author D. Ashmore
 *
 */
public class InterfaceValueGenerator implements ValueGenerator<Object> {
	
	private Class<?> interfaceType;
	
	public InterfaceValueGenerator(Class<?> type) {
		Validate.notNull(type, "Null interface type not allowed");
		Validate.isTrue(type.isInterface(), "Provided class must be an interface.  class=%s", type.getName());
		interfaceType=type;
	}

	@Override
	public Object[] makeValues() {
		return new Object[]{Proxy.newProxyInstance(
				interfaceType.getClassLoader(),
                new Class[] { interfaceType },
                new GenericProxyHandler(interfaceType))};
	}

	@Override
	public boolean canGenerate(Class<?> targetClass) {
		if (targetClass == null) return false;
		return targetClass.isInterface();
	}

}
