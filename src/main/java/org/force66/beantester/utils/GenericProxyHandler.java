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

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.lang3.ClassUtils;

/**
 * Basic interface proxy that handles involvement for equals() and serialization 
 * @author D. Ashmore
 *
 */
public class GenericProxyHandler implements InvocationHandler, Serializable {
	
	private Class<?> interfaceType;
	
	public GenericProxyHandler()  {}
	public GenericProxyHandler(Class<?> interfaceType)  {
		this.interfaceType = interfaceType;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		if ("equals".equals(method.getName())) {
			Object other = args[0];
			if (other == null)  return false;
			if (other instanceof Proxy) {
				GenericProxyHandler otherProxyHandler = (GenericProxyHandler)Proxy.getInvocationHandler(other);
				return interfaceType.equals(otherProxyHandler.interfaceType);
			}
			return false;
		}
		else if (method.getDeclaringClass().equals(Object.class)) {
			return method.invoke(this, args);
		}
		throw new BeanTesterException("This is a generic interface proxy with no functionality")
			.addContextValue("method", method.getName());
	}
	
	private void writeObject(java.io.ObjectOutputStream out)
		     throws IOException {
		out.writeObject(interfaceType);
		out.close();
	}
	
	 private void readObject(java.io.ObjectInputStream in)
	     throws IOException, ClassNotFoundException {		 
		 interfaceType = (Class<?>)in.readObject();
		 in.close();
	 }
	 private void readObjectNoData()
	     throws ObjectStreamException {
		 interfaceType=null;
	 }
	
}