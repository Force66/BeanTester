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
 * Determines if the bean implements Clonable and does a clone test if needed.
 * @author D. Ashmore
 *
 */
public class ClonableTest extends BaseBeanTest {
	
	@Override
	public boolean testBeanClass(Class<?> klass) {
		Object bean = InstantiationUtils.safeNewInstance(klass);
		if (bean instanceof Cloneable) {
			Object clone = null;
			try {
				clone = MethodUtils.invokeMethod(bean, "clone", null);
			} catch (Exception e) {
				throw new BeanTesterException("Bean implements Clonable, but clone() execution errors", e)
					.addContextValue("class", klass.getName());
			} 
			
			if (clone == bean) {
				this.setFailureReason("Clone should *not* be the same instance as the original bean, but is:  clone == bean");
				return false;
			}
			else {
				this.setFailureReason(null);
			}
			
			/*
			 * Note:  I would like to also make sure the class of the clone is the same as the original and that they equal
			 * each other.  However, according to the the Javadoc on Object.clone(), this isn't strictly required.
			 */
		}
		return true;
	}

}
