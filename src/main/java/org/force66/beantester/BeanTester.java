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

import org.apache.commons.lang3.Validate;
import org.force66.beantester.tests.BeanTest;
import org.force66.beantester.utils.BeanTesterException;
import org.force66.beantester.valuegens.GenericValueGenerator;

/**
 * <p>Main user class for the BeanTester product.  BeanTester performs monotonous testing on normal
 * java beans for accessors, mutators, equals(), hashcode(), toString() so you don't have to write those tests.</p>
 * 
 * <p>Documentation for the product can be found <a href="https://github.com/Force66/BeanTester">here</a>.</p>
 * @author D. Ashmore
 *
 */
public class BeanTester {
    
	private BeanTesterConfiguration configuration = null;

    public BeanTester() {
    	this( new DefaultBeanTesterConfiguration() );
    }
    public BeanTester(BeanTesterConfiguration config) {
    	Validate.notNull(config, "Null BeanTesterConfiguration not allowed.");
    	configuration = config;
    }
    
    /**
     * Given field will be excluded from property-level testing
     * @param fieldName
     */
    public void addExcludedField(String fieldName) {
        Validate.notEmpty(fieldName, "Null or blank fieldName not allowed.");
        configuration.getFieldExclusionSet().add(fieldName);
    }
    
    /**
     * Values will be used for property-level testing for fields defined with the given class.
     * @param fieldClass
     * @param values
     */
    public void addTestValues(Class<?> fieldClass, Object[] values) {
    	Validate.notNull(fieldClass, "Null fieldClass not allowed.");
    	Validate.notEmpty(values, "Null values array not allowed.");
    	configuration.getValueGeneratorFactory()
    		.registerGenerator(fieldClass, new GenericValueGenerator(values));
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
        for (BeanTest test: configuration.getBeanTestList()) {
    		if (!test.testBeanClass(beanClass, constructorArgs)) {
    			throw new BeanTesterException("FailedTest")
    			.addContextValue("test", test.getClass().getName())
    			.addContextValue("failure reason", test.getFailureReason());
    		}
    	}
    }

}
