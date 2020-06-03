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

import org.force66.beantester.utils.InstantiationUtils;

/**
 *  Makes sure that a bean 'equals' itself
 * @author D. Ashmore
 *
 */
public class IdentityEqualsTest extends BaseBeanTest {
	
	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		Object instance1 = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		boolean answer = instance1.equals(instance1);
		if (answer) {
			this.setFailureReason(null);
		}
		else {
			this.setFailureReason("This bean doesn not equal itself:  bean.equals(bean)");			
		}
		return answer;
	}
	
}
