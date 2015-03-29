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

import org.apache.commons.lang3.reflect.MethodUtils;
import org.force66.beantester.BeanTesterException;
import org.force66.beantester.utils.InstantiationUtils;

/**
 * Determines if the bean implements Comparable and does a primitive compareTo test if needed.
 * @author D. Ashmore
 *
 */
public class ComparableTest extends BaseBeanTest {

	@Override
	public boolean testBeanClass(Class<?> klass) {
		Object bean = InstantiationUtils.safeNewInstance(klass);
		if (bean instanceof Comparable) {
			Integer compareResult = -1;
			try {
				compareResult = (Integer)MethodUtils.invokeMethod(bean, "compareTo", bean);
			} catch (Exception e) {
				throw new BeanTesterException("Bean implements Comparable, but compareTo() execution errors", e)
				.addContextValue("class", klass.getName());
			} 
			
			if (compareResult.intValue() != 0) {
				this.setFailureReason("bean.compareTo(bean) should be 0");
				return false;
			}
			else {
				this.setFailureReason(null);
			}
			
			/*
			 * I would also like to do a test comparing two bean instances with different attribute values to verify
			 * that the compareTo isn't zero, but there's no way to determine which attributes are considered for the compare.
			 */
		}
		return true;
	}

}
