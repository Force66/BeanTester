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

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.utils.InstantiationUtils;
import org.force66.beantester.valuegens.ValueGenerator;
import org.force66.beantester.valuegens.ValueGeneratorFactory;

/**
 * Tests all bean accessors and mutators.
 * @author D. Ashmore
 *
 */
public class AccessorMutatorTest extends BaseBeanTest {
	
	private Set<String> fieldExclusionSet;
	private ValueGeneratorFactory valueGeneratorFactory;
	
	public AccessorMutatorTest(ValueGeneratorFactory factory, Set<String> fieldExclusions) {
		Validate.notNull(fieldExclusions, "Null fieldExclusionSet not allowed.");
		Validate.notNull(factory, "Null ValueGeneratorFactory not allowed.");
		valueGeneratorFactory = factory;
		fieldExclusionSet = fieldExclusions;
	}

	@Override
	public boolean testBeanClass(Class<?> klass, Object[] constructorArgs) {
		this.setFailureReason(null);
		Object bean = InstantiationUtils.safeNewInstance(klass, constructorArgs);
		for (PropertyDescriptor descriptor: PropertyUtils.getPropertyDescriptors(bean)) {
            if ( !fieldExclusionSet.contains(descriptor.getName())) {
                try {testProperty(bean, descriptor);}
                catch (TestFailureException e) {
                	this.setFailureReason(e.getMessage());
                	return false;
                }
            }
        }
		
		return true;
	}
	
	protected void testProperty(Object bean, PropertyDescriptor descriptor)  {
        
        try {
            performNullTest(bean, descriptor);
            for (Object value: generateValues(descriptor.getPropertyType())) {
                performValueTest(bean, descriptor, value);
            }
        }
        catch (TestFailureException are) {
            throw are;
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
    
	protected void performValueTest(Object bean,
            PropertyDescriptor descriptor, Object value) throws IllegalAccessException,
            InvocationTargetException {
    	
    	if (!new ValuePropertyTest().testProperty(bean, descriptor, value)) {
    		throw new TestFailureException("Property test failed")
    			.addContextValue("fieldName", descriptor.getName())
    			.addContextValue("class", bean.getClass().getName())
    			.addContextValue("test value", value);
    	}

    }

    protected void performNullTest(Object bean,
            PropertyDescriptor descriptor) throws IllegalAccessException,
            InvocationTargetException {
    	if (!new ValuePropertyTest().testProperty(bean, descriptor, null)) {
    		throw new TestFailureException("Property test failed")
    			.addContextValue("fieldName", descriptor.getName())
    			.addContextValue("class", bean.getClass().getName())
    			.addContextValue("test value", null);
    	}
         
    }
    
    protected Object[] generateValues(Class<?> type) {

    	ValueGenerator<?> generator = valueGeneratorFactory.forClass(type);
    	if (generator != null) {
    		return generator.makeValues();
    	}
        
        return new Object[0];
    }
    
    static class TestFailureException extends ContextedRuntimeException {
    	public TestFailureException(String message) {
    		super(message);
    	}
    }
    

}
