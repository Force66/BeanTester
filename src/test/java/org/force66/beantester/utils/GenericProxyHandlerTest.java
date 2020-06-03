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

import java.io.Serializable;
import java.lang.reflect.Proxy;

import javax.naming.Name;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Test;

public class GenericProxyHandlerTest {
	
	GenericProxyHandler proxy;
	TestBean bean;

	@Test
	public void testBasic() throws Exception {

		performRoundTrip(this.makeProxy(Name.class));
		
		Name nameProxy = (Name)this.makeProxy(Name.class);
		Assert.assertTrue( !nameProxy.equals(null));
		Assert.assertTrue( !nameProxy.equals("foo"));
		Assert.assertTrue( new GenericProxyHandler () != null);
		
		try {
			nameProxy.getAll();
			Assert.fail();
		}
		catch (Exception e) {
			Assert.assertTrue(e.getMessage().contains("This is a generic interface proxy with no functionality"));
		}

	}

	private void performRoundTrip(Object obj) {
		bean = new TestBean();
		bean.setFieldValue(obj);
		byte[] serializedObj = SerializationUtils.serialize(bean);	
		Serializable sBeanReconstituted = SerializationUtils.deserialize(serializedObj);
		Assert.assertTrue(bean.equals(sBeanReconstituted));
	}
	
	private Object makeProxy(Class<?> interfaceType) {
		return Proxy.newProxyInstance(
				interfaceType.getClassLoader(),
                new Class[] { interfaceType },
                new GenericProxyHandler(interfaceType));
	}
	
	static class TestBean implements Serializable {
		private Object fieldValue;

		public Object getFieldValue() {
			return fieldValue;
		}

		public void setFieldValue(Object fieldValue) {
			this.fieldValue = fieldValue;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null)  return false;
			if ( !ClassUtils.isAssignable(obj.getClass(), this.getClass())) return false;
			TestBean other = (TestBean) obj;
			return fieldValue.equals(other.fieldValue);
		}
	}

}
