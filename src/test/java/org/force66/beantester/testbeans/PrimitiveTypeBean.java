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

import org.force66.beantester.CallingRegistry;

public class PrimitiveTypeBean extends BaseBean {
	
	private short   shortValue;
	private int     intValue;
	private long    longValue;
	private float   floatValue;
	private double  doubleValue;
	private byte    byteValue;
	private boolean booleanValue;
	private char    charValue;
	public short getShortValue() {
		CallingRegistry.addCall("getShortValue");
		return shortValue;
	}
	public void setShortValue(short shortValue) {
		CallingRegistry.addCall("setShortValue");
		this.shortValue = shortValue;
	}
	public int getIntValue() {
		CallingRegistry.addCall("getIntValue");
		return intValue;
	}
	public void setIntValue(int intValue) {
		CallingRegistry.addCall("setIntValue");
		this.intValue = intValue;
	}
	public long getLongValue() {
		CallingRegistry.addCall("getLongValue");
		return longValue;
	}
	public void setLongValue(long longValue) {
		CallingRegistry.addCall("setLongValue");
		this.longValue = longValue;
	}
	public float getFloatValue() {
		CallingRegistry.addCall("getFloatValue");
		return floatValue;
	}
	public void setFloatValue(float floatValue) {
		CallingRegistry.addCall("setFloatValue");
		this.floatValue = floatValue;
	}
	public double getDoubleValue() {
		CallingRegistry.addCall("getDoubleValue");
		return doubleValue;
	}
	public void setDoubleValue(double doubleValue) {
		CallingRegistry.addCall("setDoubleValue");
		this.doubleValue = doubleValue;
	}
	public byte getByteValue() {
		CallingRegistry.addCall("getByteValue");
		return byteValue;
	}
	public void setByteValue(byte byteValue) {
		CallingRegistry.addCall("setByteValue");
		this.byteValue = byteValue;
	}
	public boolean isBooleanValue() {
		CallingRegistry.addCall("isBooleanValue");
		return booleanValue;
	}
	public void setBooleanValue(boolean booleanValue) {
		CallingRegistry.addCall("setBooleanValue");
		this.booleanValue = booleanValue;
	}
	public char getCharValue() {
		CallingRegistry.addCall("getCharValue");
		return charValue;
	}
	public void setCharValue(char charValue) {
		CallingRegistry.addCall("setCharValue");
		this.charValue = charValue;
	}

}
