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

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.Validate;

/**
 * Helper for class instantiation
 * @author D. Ashmore
 *
 */
public class InstantiationUtils {
	
	/**
	 * newInstance() execution properly wrapped with exception handling.
	 * @param klass
	 * @return
	 */
	public static Object safeNewInstance(Class<?> klass) {
		Validate.notNull(klass, "Null class not allowed.");
		try {
			return klass.newInstance();
		} catch (Exception e) {
			throw new BeanTesterException("Failed to instantiate bean using newInstance()", e)
				.addContextValue("className", klass.getName());
		} 
	}
	
	public static XMLGregorianCalendar newXMLGregorianCalendar() {
		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendar();
		} catch (DatatypeConfigurationException e) {
			throw new BeanTesterException("This shouldn't happen", e);
		}
	}

}
