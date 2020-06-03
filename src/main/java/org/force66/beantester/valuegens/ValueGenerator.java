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
package org.force66.beantester.valuegens;

/**
 * Generates a value of a specific type.
 * @author D. Ashmore
 *
 * @param <T>
 */
public interface ValueGenerator<T> {
	
	/**
	 * Will emit a value guaranteed not to have been emitted before.  Will return null if all possible
	 * values have been emitted.
	 * @return
	 */
	public T[] makeValues();
	
	/**
	 * Determines if this generator can generate the target class.
	 * @param targetClass
	 * @return
	 */
	public boolean canGenerate(Class<?> targetClass);

}
