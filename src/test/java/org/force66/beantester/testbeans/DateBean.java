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
import java.util.GregorianCalendar;

import javax.xml.datatype.XMLGregorianCalendar;

import org.force66.beantester.CallingRegistry;

public class DateBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = -6175954579083249160L;
	
	private java.util.Date			utilDateValue;
	private java.sql.Date			sqlDateValue;
	private java.sql.Time			sqlTimeValue;
	private java.sql.Timestamp  	sqlTimestampValue;
	private GregorianCalendar		gregorianCalendarValue;
	private XMLGregorianCalendar	xmlGregorianCalendarValue;
	public java.util.Date getUtilDateValue() {
		CallingRegistry.addCall("getUtilDateValue");
		return utilDateValue;
	}
	public void setUtilDateValue(java.util.Date utilDateValue) {
		CallingRegistry.addCall("setUtilDateValue");
		this.utilDateValue = utilDateValue;
	}
	public java.sql.Date getSqlDateValue() {
		CallingRegistry.addCall("getSqlDateValue");
		return sqlDateValue;
	}
	public void setSqlDateValue(java.sql.Date sqlDateValue) {
		CallingRegistry.addCall("setSqlDateValue");
		this.sqlDateValue = sqlDateValue;
	}
	public java.sql.Time getSqlTimeValue() {
		CallingRegistry.addCall("getSqlTimeValue");
		return sqlTimeValue;
	}
	public void setSqlTimeValue(java.sql.Time sqlTimeValue) {
		CallingRegistry.addCall("setSqlTimeValue");
		this.sqlTimeValue = sqlTimeValue;
	}
	public java.sql.Timestamp getSqlTimestampValue() {
		CallingRegistry.addCall("getSqlTimestampValue");
		return sqlTimestampValue;
	}
	public void setSqlTimestampValue(java.sql.Timestamp sqlTimestampValue) {
		CallingRegistry.addCall("setSqlTimestampValue");
		this.sqlTimestampValue = sqlTimestampValue;
	}
	public GregorianCalendar getGregorianCalendarValue() {
		CallingRegistry.addCall("getGregorianCalendarValue");
		return gregorianCalendarValue;
	}
	public void setGregorianCalendarValue(GregorianCalendar gregorianCalendarValue) {
		CallingRegistry.addCall("setGregorianCalendarValue");
		this.gregorianCalendarValue = gregorianCalendarValue;
	}
	public XMLGregorianCalendar getXmlGregorianCalendarValue() {
		CallingRegistry.addCall("getXmlGregorianCalendarValue");
		return xmlGregorianCalendarValue;
	}
	public void setXmlGregorianCalendarValue(
			XMLGregorianCalendar xmlGregorianCalendarValue) {
		CallingRegistry.addCall("setXmlGregorianCalendarValue");
		this.xmlGregorianCalendarValue = xmlGregorianCalendarValue;
	}

}
