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
package org.force66.beantester.testbeans;

import java.sql.Statement;

public class OddTypesBean extends BaseBean {
	
	public static enum OddEnum {ONE, TWO, THREE};
	
	private Statement interfaceType;
	private String[]  stringArray;
	private String    noAccessor;
	private String    noMutator;
	private String misnamedAccessorsMutators;
	private Class<?>   fieldClass;
	private OddEnum		oddEnumField;
	

	public Statement getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(Statement interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String[] getStringArray() {
		return stringArray;
	}

	public void setStringArray(String[] stringArray) {
		this.stringArray = stringArray;
	}

	public String getNoMutator() {
		return noMutator;
	}

	public void setNoAccessor(String noAccessor) {
		this.noAccessor = noAccessor;
	}

	public String getMisnamed() {
		return misnamedAccessorsMutators;
	}

	public void setMisnamed(String misnamedAccessorsMutators) {
		this.misnamedAccessorsMutators = misnamedAccessorsMutators;
	}

	public Class<?> getFieldClass() {
		return fieldClass;
	}

	public void setFieldClass(Class<?> fieldClass) {
		this.fieldClass = fieldClass;
	}

	public OddEnum getOddEnumField() {
		return oddEnumField;
	}

	public void setOddEnumField(OddEnum oddEnumField) {
		this.oddEnumField = oddEnumField;
	}

}
