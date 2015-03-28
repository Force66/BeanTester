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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.force66.beantester.CallingRegistry;

public class CollectionsBean extends BaseBean {
	
	private Collection 			untypedCollection;
	private List 				untypedList;
	private Map 				untypedMap;
	private Set 				untypedSet;
	private Collection<String> 	typedCollection;
	private List<String> 		typedList;
	private Map<String,String> 	typedMap;
	private Set<String> 		typedSet;
	public Collection getUntypedCollection() {
		CallingRegistry.addCall("getUntypedCollection");
		return untypedCollection;
	}
	public void setUntypedCollection(Collection untypedCollection) {
		CallingRegistry.addCall("setUntypedCollection");
		this.untypedCollection = untypedCollection;
	}
	public List getUntypedList() {
		CallingRegistry.addCall("getUntypedList");
		return untypedList;
	}
	public void setUntypedList(List untypedList) {
		CallingRegistry.addCall("setUntypedList");
		this.untypedList = untypedList;
	}
	public Map getUntypedMap() {
		CallingRegistry.addCall("getUntypedMap");
		return untypedMap;
	}
	public void setUntypedMap(Map untypedMap) {
		CallingRegistry.addCall("setUntypedMap");
		this.untypedMap = untypedMap;
	}
	public Set getUntypedSet() {
		CallingRegistry.addCall("getUntypedSet");
		return untypedSet;
	}
	public void setUntypedSet(Set untypedSet) {
		CallingRegistry.addCall("setUntypedSet");
		this.untypedSet = untypedSet;
	}
	public Collection<String> getTypedCollection() {
		CallingRegistry.addCall("getTypedCollection");
		return typedCollection;
	}
	public void setTypedCollection(Collection<String> typedCollection) {
		CallingRegistry.addCall("setTypedCollection");
		this.typedCollection = typedCollection;
	}
	public List<String> getTypedList() {
		CallingRegistry.addCall("getTypedList");
		return typedList;
	}
	public void setTypedList(List<String> typedList) {
		CallingRegistry.addCall("setTypedList");
		this.typedList = typedList;
	}
	public Map<String, String> getTypedMap() {
		CallingRegistry.addCall("getTypedMap");
		return typedMap;
	}
	public void setTypedMap(Map<String, String> typedMap) {
		CallingRegistry.addCall("setTypedMap");
		this.typedMap = typedMap;
	}
	public Set<String> getTypedSet() {
		CallingRegistry.addCall("getTypedSet");
		return typedSet;
	}
	public void setTypedSet(Set<String> typedSet) {
		CallingRegistry.addCall("setTypedSet");
		this.typedSet = typedSet;
	}

}
