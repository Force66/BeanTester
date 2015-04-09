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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.force66.beantester.tests.AccessorMutatorTest;
import org.force66.beantester.tests.BeanTest;
import org.force66.beantester.tests.ClonableTest;
import org.force66.beantester.tests.ComparableTest;
import org.force66.beantester.tests.HashcodeEqualsTest;
import org.force66.beantester.tests.HashcodeTest;
import org.force66.beantester.tests.IdentityEqualsTest;
import org.force66.beantester.tests.SerializableTest;
import org.force66.beantester.tests.ToStringTest;
import org.force66.beantester.valuegens.ValueGeneratorFactory;

/**
 * Default configuration for the BeanTester product.
 * 
 * <p>Default field exclusions</p>
 * <li>class -- Object.class isn't a property that should be tested.</li>
 * 
 * <p>Default Bean Tests</p>
 * <li>IdentityEqualsTest -- See {@link IdentityEqualsTest}.</li>
 * <li>ClonableTest -- See {@link ClonableTest}.</li>
 * <li>ComparableTest -- See {@link ComparableTest}.</li>
 * <li>ToStringTest -- See {@link ToStringTest}.</li>
 * <li>HashcodeTest -- See {@link HashcodeTest}.</li>
 * <li>HashcodeEqualsTest -- See {@link HashcodeEqualsTest}.</li>
 * <li>SerializableTest -- See {@link SerializableTest}.</li>
 * <li>AccessorMutatorTest -- See {@link AccessorMutatorTest}.</li>
 * @author D. Ashmore
 *
 */
public class DefaultBeanTesterConfiguration implements BeanTesterConfiguration {
	
	private Set<String> fieldExclusionSet = new HashSet<String>();
	private List<BeanTest> beanTestList = new ArrayList<BeanTest>();
	private ValueGeneratorFactory valueGeneratorFactory;
	
	public DefaultBeanTesterConfiguration()  {
		 fieldExclusionSet.add("class"); // Object.class shouldn't be tested.
		 
		 valueGeneratorFactory = new ValueGeneratorFactory();
		 
		 beanTestList.add(new IdentityEqualsTest());
	     beanTestList.add(new ClonableTest());
	     beanTestList.add(new ComparableTest());
	     beanTestList.add(new ToStringTest(valueGeneratorFactory));
	     beanTestList.add(new HashcodeTest());
	     beanTestList.add(new HashcodeEqualsTest());
	     beanTestList.add(new SerializableTest(valueGeneratorFactory));
	     beanTestList.add(new AccessorMutatorTest(valueGeneratorFactory, fieldExclusionSet));
	}

	/* (non-Javadoc)
	 * @see org.force66.beantester.BeanTesterConfiguration#getFieldExclusionSet()
	 */
	@Override
	public Set<String> getFieldExclusionSet() {
		return fieldExclusionSet;
	}

	/* (non-Javadoc)
	 * @see org.force66.beantester.BeanTesterConfiguration#getBeanTestList()
	 */
	@Override
	public List<BeanTest> getBeanTestList() {
		return beanTestList;
	}

	/* (non-Javadoc)
	 * @see org.force66.beantester.BeanTesterConfiguration#getValueGeneratorFactory()
	 */
	@Override
	public ValueGeneratorFactory getValueGeneratorFactory() {
		return valueGeneratorFactory;
	}

}
