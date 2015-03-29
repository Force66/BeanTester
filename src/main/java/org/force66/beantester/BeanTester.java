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
package org.force66.beantester;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.Validate;
import org.force66.beantester.tests.ClonableTest;
import org.force66.beantester.tests.ComparableTest;
import org.force66.beantester.tests.IdentityEqualsTest;
import org.force66.beantester.tests.ValuePropertyTest;
import org.force66.beantester.utils.InstantiationUtils;
import org.junit.Assert;
import org.mockito.Mockito;

public class BeanTester {
    
    private Set<String> fieldExclusionSet = new HashSet<String>();
    private ValueGeneratorFactory valueGeneratorFactory = new ValueGeneratorFactory();
    private List<BeanTest> beanTestList = new ArrayList<BeanTest>();

    public BeanTester() {
        fieldExclusionSet.add("class"); // Object.class shouldn't be tested.
        beanTestList.add(new IdentityEqualsTest());
        beanTestList.add(new ClonableTest());
        beanTestList.add(new ComparableTest());
    }
    
    public void addExcludedField(String fieldName) {
        Validate.notEmpty(fieldName, "Null or blank fieldName not allowed.");
        fieldExclusionSet.add(fieldName);
    }
    
    public void testBean(Class<?> beanClass)  {
        Validate.notNull(beanClass, "Null beanClass not allowed.");
        testBean(InstantiationUtils.safeNewInstance(beanClass));
    }
    
    protected void testBean(Object bean) {
        Validate.notNull(bean, "Null bean not allowed.");
        
        try {performBeanTests(bean);}
        catch (Exception e) {
            throw new BeanTesterException(e)
            .addContextValue("bean type", bean.getClass().getName());
        }
        
        for (PropertyDescriptor descriptor: PropertyUtils.getPropertyDescriptors(bean)) {
            if ( !fieldExclusionSet.contains(descriptor.getName())) {
                testProperty(bean, descriptor);
            }
        }
    }
    
    protected void testProperty(Class<?> beanClass, String fieldName)  {
        Validate.notNull(beanClass, "Null beanClass not allowed.");
        testProperty(InstantiationUtils.safeNewInstance(beanClass), fieldName);
    }
    
    protected void testProperty(Object bean, String fieldName)  {
        Validate.notNull(bean, "Null bean not allowed.");
        Validate.notEmpty(fieldName, "Null or blank fieldName not allowed.");
        
        PropertyDescriptor descriptor=null;
        try {
			descriptor=PropertyUtils.getPropertyDescriptor(bean, fieldName);
		} catch (Exception e) {
			throw new BeanTesterException("Error determining property descriptor", e)
				.addContextValue("fieldName", fieldName)
				.addContextValue("className", bean.getClass().getName());
		} 
        
        testProperty(bean, descriptor);
    }
    
    protected void testProperty(Object bean, PropertyDescriptor descriptor)  {
        
        try {
            performNullTest(bean, descriptor);
            for (Object value: generateValues(descriptor.getPropertyType())) {
                performValueTest(bean, descriptor, value);
            }
        }
        catch (BeanTesterException are) {
            throw are.addContextValue("bean type", bean.getClass().getName())
                .addContextValue("field", descriptor.getName());
        }
        catch (Exception e) {
            throw new BeanTesterException(e)
            .addContextValue("bean type", bean.getClass().getName())
            .addContextValue("field", descriptor.getName());
        }
    }
    
    protected Object[] generateValues(Class type) {

    	ValueGenerator<?> generator = valueGeneratorFactory.forClass(type);
    	if (generator != null) {
    		return generator.makeValues();
    	}
    	
        if (type.isInterface()) {
        	return new Object[]{Mockito.mock(type)};
        }
        else if (type.isPrimitive()) {
        	if (type.getName().equals("long")) {
                return new Object[]{Long.valueOf(0)};
            }
            else if (type.getName().equals("int")) {
                return new Object[]{Integer.valueOf(0)};
            }
            else if (type.getName().equals("double")) {
                return new Object[]{Double.valueOf(0)};
            }
            else if (type.getName().equals("byte")) {
                return new Object[]{Byte.valueOf("1")};
            }
            else if (type.getName().equals("float")) {
                return new Object[]{Float.valueOf("1")};
            }
            else if (type.getName().equals("short")) {
                return new Object[]{Short.valueOf("1")};
            }
            else if (type.getName().equals("char")) {
                return new Object[]{Character.valueOf('A')};
            }
        }
        else if (Long.class.equals(type)) {
            return new Object[]{Long.valueOf(0)};
        }
        else if (Integer.class.equals(type)) {
            return new Object[]{Integer.valueOf(0)};
        }
        else if (Double.class.equals(type)) {
            return new Object[]{Double.valueOf(0)};
        }
        else if (Byte.class.equals(type)) {
            return new Object[]{Byte.valueOf("1")};
        }
        else if (Float.class.equals(type)) {
            return new Object[]{Float.valueOf("1")};
        }
        else if (Short.class.equals(type)) {
            return new Object[]{Short.valueOf("1")};
        }
        else if (Character.class.equals(type)) {
            return new Object[]{Character.valueOf('A')};
        }
        else if (Timestamp.class.equals(type)) {
            return new Object[]{new Timestamp(System.currentTimeMillis())};
        }
        else if (Time.class.equals(type)) {
            return new Object[]{new Time(System.currentTimeMillis())};
        }
        else if (java.sql.Date.class.equals(type)) {
            return new Object[]{new java.sql.Date(System.currentTimeMillis())};
        }
        else if (XMLGregorianCalendar.class.equals(type)) {
            try {
				return new Object[]{DatatypeFactory.newInstance().newXMLGregorianCalendar()};
			} catch (DatatypeConfigurationException e) {
				throw new BeanTesterException("This shouldn't happen", e);
			}
        }
        else if (type.isEnum()) {
            return type.getEnumConstants();
        }
        else if (type.isArray()) {
            Array.newInstance(type.getComponentType(), 0);
        }
        else if (Class.class.equals(type)) {
        	return new Object[]{Object.class};
        }
        else {
        	return new Object[]{InstantiationUtils.safeNewInstance(type)};
        }
        
        return new Object[0];
    }
    
    protected void performBeanTests(Object bean) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    	for (BeanTest test: beanTestList) {
    		if (!test.testBeanClass(bean.getClass())) {
    			throw new BeanTesterException("FailedTest")
    			.addContextValue("test", test.getClass().getName())
    			.addContextValue("failure reason", test.getFailureReason());
    		}
    	}
 
        Assert.assertTrue("Hashcode test on class " + bean.getClass().getName() + " did not pass", bean.hashCode() == bean.hashCode());
        Assert.assertTrue("ToString test on class " + bean.getClass().getName() + " did not pass", bean.toString() != null);
        
    }
    
    protected void performValueTest(Object bean,
            PropertyDescriptor descriptor, Object value) throws IllegalAccessException,
            InvocationTargetException {
        
		Assert.assertTrue("Field " + descriptor.getName()
				+ " on class " + bean.getClass().getName()
				+ " did not pass value test", 
				new ValuePropertyTest().testProperty(bean, descriptor, value));
    }

    protected void performNullTest(Object bean,
            PropertyDescriptor descriptor) throws IllegalAccessException,
            InvocationTargetException {
    	
    	Assert.assertTrue("Field " + descriptor.getName() + " on class " 
    			+ bean.getClass().getName() + " did not pass null test", 
    			new ValuePropertyTest().testProperty(bean, descriptor, null));
        
    }

}
