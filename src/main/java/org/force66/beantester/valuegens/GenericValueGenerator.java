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

import org.apache.commons.lang3.ClassUtils;

/**
 * Generic value generator for provided values.
 * @author D. Ashmore
 *
 */
public class GenericValueGenerator implements ValueGenerator<Object> {
	
	private Object[] values;
	
	public GenericValueGenerator(Object[] values) {
		this.values = values;
	}

	@Override
	public Object[] makeValues() {
		return values;
	}

	@Override
	public boolean canGenerate(Class<?> targetClass) {
		if (targetClass == null) return false;
		if (values.length > 0) {
			return ClassUtils.isAssignable(values[0].getClass(), targetClass);
		}
		return false;
	}

}
