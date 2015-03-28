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

import org.apache.commons.lang3.exception.ContextedRuntimeException;

/**
 * Basic runtime exception for the BeanTester product.
 * @author D. Ashmore
 *
 */
public class BeanTesterException extends ContextedRuntimeException {

	private static final long serialVersionUID = 8531575225162635884L;

	public BeanTesterException() {}

	public BeanTesterException(String message) {
		super(message);
	}

	public BeanTesterException(Throwable cause) {
		super(cause);
	}

	public BeanTesterException(String message, Throwable cause) {
		super(message, cause);
	}

}
