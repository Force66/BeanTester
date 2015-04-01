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

import java.lang.reflect.Array;

import org.apache.commons.lang3.Validate;

/**
 * Will generate values for arrays.
 * @author D. Ashmore
 *
 */
public class ArrayValueGenerator implements ValueGenerator<Object> {
	
	private Class<?> arrayType;
	
	public ArrayValueGenerator(Class<?> type) {
		Validate.notNull(type, "Null array type not allowed");
		Validate.isTrue(type.isArray(), "Provided class must be an array.  class=%s", type.getName());
		arrayType=type;
	}

	@Override
	public Object[] makeValues() {
		return  new Object[]{Array.newInstance(arrayType.getComponentType(), 0)};
	}

	@Override
	public boolean canGenerate(Class<?> targetClass) {
		if (targetClass == null) return false;
		return targetClass.isArray();
	}

}
