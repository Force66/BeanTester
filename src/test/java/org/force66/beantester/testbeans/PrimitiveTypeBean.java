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

import java.io.Serializable;

import org.force66.beantester.CallingRegistry;

public class PrimitiveTypeBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = -687007182169761400L;
	
	private short   shortValue;
	private int     intValue;
	private long    longValue;
	private float   floatValue;
	private double  doubleValue;
	private byte    byteValue;
	private boolean booleanValue;
	private char    charValue;
	
	private short[]   shortArrayValue;
	private int[]     intArrayValue;
	private long[]    longArrayValue;
	private float[]   floatArrayValue;
	private double[]  doubleArrayValue;
	private byte[]    byteArrayValue;
	private boolean[] booleanArrayValue;
	private char[]    charArrayValue;
	
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
	public short[] getShortArrayValue() {
		return shortArrayValue;
	}
	public void setShortArrayValue(short[] shortArrayValue) {
		this.shortArrayValue = shortArrayValue;
	}
	public int[] getIntArrayValue() {
		return intArrayValue;
	}
	public void setIntArrayValue(int[] intArrayValue) {
		this.intArrayValue = intArrayValue;
	}
	public long[] getLongArrayValue() {
		return longArrayValue;
	}
	public void setLongArrayValue(long[] longArrayValue) {
		this.longArrayValue = longArrayValue;
	}
	public float[] getFloatArrayValue() {
		return floatArrayValue;
	}
	public void setFloatArrayValue(float[] floatArrayValue) {
		this.floatArrayValue = floatArrayValue;
	}
	public double[] getDoubleArrayValue() {
		return doubleArrayValue;
	}
	public void setDoubleArrayValue(double[] doubleArrayValue) {
		this.doubleArrayValue = doubleArrayValue;
	}
	public byte[] getByteArrayValue() {
		return byteArrayValue;
	}
	public void setByteArrayValue(byte[] byteArrayValue) {
		this.byteArrayValue = byteArrayValue;
	}
	public boolean[] getBooleanArrayValue() {
		return booleanArrayValue;
	}
	public void setBooleanArrayValue(boolean[] booleanArrayValue) {
		this.booleanArrayValue = booleanArrayValue;
	}
	public char[] getCharArrayValue() {
		return charArrayValue;
	}
	public void setCharArrayValue(char[] charArrayValue) {
		this.charArrayValue = charArrayValue;
	}

}
