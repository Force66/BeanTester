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
package org.force66.beantester.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.ConstructorUtils;
import org.apache.commons.lang3.Validate;
import org.force66.beantester.valuegens.ValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;

/**
 * Helper for class instantiation
 * @author D. Ashmore
 *
 */
public class InstantiationUtils {
	
	public static Object safeNewInstance(Class<?> klass) {
		return safeNewInstance(klass, null);
	}
	
	public static Object safeNewInstance(ValueGeneratorFactory factory, Class<?> klass) {
		Validate.notNull(klass, "Null class not allowed.");
		Constructor<?> ctor = findPublicConstructor(klass);
		if (ctor == null) {
			throw new BeanTesterException("No public constructor found -- value generation isn't possible")
				.addContextValue("class", klass.getName());
		}
		if (ctor.getParameterTypes().length == 0) {
			return safeNewInstance(klass, null);
		}
		
		ValueGenerator<?> generator = null;
		List<Object> constructorArgs = new ArrayList<Object>();
		for (Class<?> argType: ctor.getParameterTypes()) {
			generator = factory.forClass(argType);
			if (generator == null) {
				throw new BeanTesterException("Value can't be generated for constructor argument")
					.addContextValue("class", klass.getName())
					.addContextValue("constructor", ctor)
					.addContextValue("argument type", argType.getName());
			}
			constructorArgs.add(generator.makeValues()[0]);
		}
		
		return safeNewInstance(klass, constructorArgs.toArray());
	}
	
	public static Constructor<?> findPublicConstructor(Class<?> klass) {
		Validate.notNull(klass, "Null class not allowed.");
		Constructor<?> nullConstructor = ConstructorUtils.getAccessibleConstructor(klass, new Class<?>[0]);
		if (nullConstructor != null) {
			return nullConstructor;
		}
		
		Constructor<?>[] constructorArray = klass.getConstructors();
		for (Constructor<?> constructor: constructorArray) {
			if (Modifier.isPublic(constructor.getModifiers())) {
				return constructor;
			}
		}
		
		return null;
	}
	
	/**
	 * newInstance() execution properly wrapped with exception handling.
	 * @param klass
	 * @return
	 */
	public static Object safeNewInstance(Class<?> klass, Object[] constructorArgs) {
		Validate.notNull(klass, "Null class not allowed.");
		try {
			return ConstructorUtils.invokeConstructor(klass, constructorArgs);
		} catch (Exception e) {
			throw new BeanTesterException("Failed to instantiate bean using newInstance()", e)
				.addContextValue("className", klass.getName());
		} 
	}
	
	public static XMLGregorianCalendar newXMLGregorianCalendar() {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
		} catch (DatatypeConfigurationException e) {
			throw new BeanTesterException("This shouldn't happen", e);
		}
	}

}
