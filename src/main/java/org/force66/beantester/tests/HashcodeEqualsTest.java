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
 * HashcodeEquals test.
 * @author D. Greenfield
 *
 */
public class HashcodeEqualsTest extends BaseBeanTest {

	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		Object instance1 = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		Object instance2 = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		this.setFailureReason(null);
		
		try{
			if(instance1.equals(instance2)  && 
			   instance1.hashCode() != instance2.hashCode()){
				this.setFailureReason("Instances that equal should produce the same hashcode.  class="+klass.getName());
				return false;
			}
		}catch(Exception e){
			throw new BeanTesterException("hashcode() failed to execute", e).
			addContextValue("bean class", klass.toString());
		}

		return true;
	}

}
