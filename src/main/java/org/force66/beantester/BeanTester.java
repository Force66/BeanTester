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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.Validate;
import org.force66.beantester.tests.ClonableTest;
import org.force66.beantester.tests.ComparableTest;
import org.force66.beantester.tests.HashcodeTest;
import org.force66.beantester.tests.IdentityEqualsTest;
import org.force66.beantester.tests.ToStringTest;
import org.force66.beantester.tests.ValuePropertyTest;
import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.utils.InstantiationUtils;
import org.force66.beantester.valuegens.GenericValueGenerator;
import org.force66.beantester.valuegens.InterfaceValueGenerator;

/**
 * <p>Main user class for the BeanTester product.  BeanTester performs monotonous testing on normal
 * java beans for accessors, mutators, equals(), hashcode(), toString() so you don't have to write those tests.</p>
 * 
 * <p>Documentation for the product can be found <a href="https://github.com/Force66/BeanTester">here</a>.</p>
 * @author D. Ashmore
 *
 */
public class BeanTester {
    
    private Set<String> fieldExclusionSet = new HashSet<String>();
    private ValueGeneratorFactory valueGeneratorFactory = new ValueGeneratorFactory();
    private List<BeanTest> beanTestList = new ArrayList<BeanTest>();

    public BeanTester() {
        fieldExclusionSet.add("class"); // Object.class shouldn't be tested.
        beanTestList.add(new IdentityEqualsTest());
        beanTestList.add(new ClonableTest());
        beanTestList.add(new ComparableTest());
        beanTestList.add(new ToStringTest());
        beanTestList.add(new HashcodeTest());
    }
    
    /**
     * Given field will be excluded from property-level testing
     * @param fieldName
     */
    public void addExcludedField(String fieldName) {
        Validate.notEmpty(fieldName, "Null or blank fieldName not allowed.");
        fieldExclusionSet.add(fieldName);
    }
    
    /**
     * Values will be used for property-level testing for fields defined with the given class.
     * @param fieldClass
     * @param values
     */
    public void addTestValues(Class<?> fieldClass, Object[] values) {
    	Validate.notNull(fieldClass, "Null fieldClass not allowed.");
    	Validate.notEmpty(values, "Null values array not allowed.");
    	valueGeneratorFactory.registerGenerator(fieldClass, new GenericValueGenerator(values));
    }
    
    /**
     * Will perform all bean tests against the class provided.  See product 
     * <a href="https://github.com/Force66/BeanTester">documentation</a> for details on the
     * tests performed.
     * @param beanClass
     */
    public void testBean(Class<?> beanClass)  {
    	this.testBean(beanClass, null);
    }
    
    /**
     * Will perform all bean tests against the class provided using the constructor arguments provided.  
     * See product <a href="https://github.com/Force66/BeanTester">documentation</a> for details on the
     * tests performed.
     * @param beanClass
     * @param constructorArgs
     */
    public void testBean(Class<?> beanClass, Object[] constructorArgs)  {
        Validate.notNull(beanClass, "Null beanClass not allowed.");
        testBean(InstantiationUtils.safeNewInstance(beanClass, constructorArgs), constructorArgs);
    }
    
    protected void testBean(Object bean, Object[] constructorArgs) {
        Validate.notNull(bean, "Null bean not allowed.");
        
        try {performBeanTests(bean, constructorArgs);}
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
    
    protected Object[] generateValues(Class<?> type) {

    	ValueGenerator<?> generator = valueGeneratorFactory.forClass(type);
    	if (generator != null) {
    		return generator.makeValues();
    	}
        
        return new Object[0];
    }
    
    protected void performBeanTests(Object bean, Object[] constructorArgs) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    	for (BeanTest test: beanTestList) {
    		if (!test.testBeanClass(bean.getClass(), constructorArgs)) {
    			throw new BeanTesterException("FailedTest")
    			.addContextValue("test", test.getClass().getName())
    			.addContextValue("failure reason", test.getFailureReason());
    		}
    	}
       
    }
    
    protected void performValueTest(Object bean,
            PropertyDescriptor descriptor, Object value) throws IllegalAccessException,
            InvocationTargetException {
    	
    	if (!new ValuePropertyTest().testProperty(bean, descriptor, value)) {
    		throw new BeanTesterException("Property test failed")
    			.addContextValue("fieldName", descriptor.getName())
    			.addContextValue("class", bean.getClass().getName())
    			.addContextValue("test value", value);
    	}

    }

    protected void performNullTest(Object bean,
            PropertyDescriptor descriptor) throws IllegalAccessException,
            InvocationTargetException {
    	if (!new ValuePropertyTest().testProperty(bean, descriptor, null)) {
    		throw new BeanTesterException("Property test failed")
    			.addContextValue("fieldName", descriptor.getName())
    			.addContextValue("class", bean.getClass().getName())
    			.addContextValue("test value", null);
    	}
         
    }

}
