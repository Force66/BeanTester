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

import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.utils.InstantiationUtils;

/**
 * Hashcode test.
 * @author D. Ashmore
 *
 */
public class HashcodeTest extends BaseBeanTest {

	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		Object instance1 = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		this.setFailureReason(null);
		
		int instance1HashValue;
		try {instance1HashValue=instance1.hashCode();}
		catch (Exception e) {
			throw new BeanTesterException("hashcode() failed execution", e)
				.addContextValue("bean class", klass.toString());
		}
		
		if (instance1.hashCode() == 0 ) {
			this.setFailureReason("Hashcode value of 0 generally indicates a non-proper implementation.  class="+klass.getName());
			return false;
		}
		
		/*
		 * With no changes, the hashcode value should remain constant for all time.  10,000 birds don't
		 * make a summer, but...
		 */
		for (int i = 0; i < 10000; i++) {
			if (instance1HashValue != instance1.hashCode()) {
				this.setFailureReason("Instances that don't change should produce the same hashcode value over time.  class="+klass.getName());
				return false;
			}
		}
		return true;
	}

}
