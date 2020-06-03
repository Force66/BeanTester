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

import java.io.Serializable;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.Validate;
import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.utils.InjectionUtils;
import org.force66.beantester.utils.InstantiationUtils;
import org.force66.beantester.valuegens.ValueGeneratorFactory;

/**
 * Determines if the bean implements Serializable and does a serialization test if needed.
 * @author D. Ashmore
 *
 */
public class SerializableTest extends BaseBeanTest {
	
	private ValueGeneratorFactory valueGeneratorFactory;
	
	public SerializableTest(ValueGeneratorFactory factory) {
		Validate.notNull(factory, "Null ValueGeneratorFactory not allowed.");
		valueGeneratorFactory = factory;
	}

	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		this.setFailureReason(null);
		Object bean = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		if (bean instanceof Serializable) {
			InjectionUtils.injectValues(bean, valueGeneratorFactory, false);
			
			Serializable sBean = (Serializable)bean;
			Serializable sBeanReconstituted = null;
			byte[] serializedObj;
			try {
				serializedObj = SerializationUtils.serialize(sBean);			
				sBeanReconstituted = SerializationUtils.deserialize(serializedObj);
			} catch (Throwable e) {
				this.setFailureReason("Error serializing bean that implements serializable");
				throw new BeanTesterException("Error serializing bean that implements serializable", e)
					.addContextValue("class", klass.getName());
			}
			
			/*
			 * An equals() test is only valid if the bean isn't relying on Object.equals().
			 */
			Method equalsMethod = MethodUtils.getAccessibleMethod(klass, "equals", Object.class);
			if ( !equalsMethod.getDeclaringClass().equals(Object.class) && !sBean.equals(sBeanReconstituted)) {
				this.setFailureReason("Bean implements serializable, but the reconstituted bean doesn't equal it's original");
				throw new BeanTesterException("Bean implements serializable, but the reconstituted bean doesn't equal it's original")
				.addContextValue("class", klass.getName());
			}
		}
		return true;
	}

}
