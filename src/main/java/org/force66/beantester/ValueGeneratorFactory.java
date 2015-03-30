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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.Validate;
import org.force66.beantester.utils.InstantiationUtils;
import org.force66.beantester.valuegens.GenericValueGenerator;
import org.force66.beantester.valuegens.PrimitiveValueGenerator;
import org.force66.beantester.valuegens.TemporalValueGenerator;

/**
 * Will determine an appropriate ValueGenerator for a specific class.
 * @author D. Ashmore
 *
 */
public class ValueGeneratorFactory {
	
	private static final ValueGenerator<?>[] STOCK_GENERATORS = new ValueGenerator<?>[]{
		new PrimitiveValueGenerator(Boolean.class, new Object[]{Boolean.TRUE, Boolean.FALSE})
		,new PrimitiveValueGenerator(Long.class, new Object[]{Long.valueOf(0)})
		,new PrimitiveValueGenerator(Integer.class, "int", new Object[]{Integer.valueOf(0)})
		,new PrimitiveValueGenerator(Double.class, new Object[]{Double.valueOf(0)})
		,new PrimitiveValueGenerator(Byte.class, new Object[]{Byte.MIN_VALUE})
		,new PrimitiveValueGenerator(Float.class, new Object[]{Float.valueOf(0)})
		,new PrimitiveValueGenerator(Short.class, new Object[]{ Short.valueOf("0")})
		,new PrimitiveValueGenerator(Character.class, "char", new Object[]{ 'A'})
		,new TemporalValueGenerator(java.util.Date.class)
		,new TemporalValueGenerator(java.sql.Date.class)
		,new TemporalValueGenerator(java.sql.Time.class)
		,new TemporalValueGenerator(java.sql.Timestamp.class)
		,new GenericValueGenerator(new Object[]{InstantiationUtils.newXMLGregorianCalendar()})
	};
	
	private Map<Class<?>,ValueGenerator<?>> registeredGeneratorMap = new HashMap<Class<?>, ValueGenerator<?>>();
	
	public void registerGenerator(Class<?> klass,ValueGenerator<?> generator) {
		Validate.notNull(klass, "Null class not allowed");
		Validate.notNull(generator, "Null generator not allowed");
		registeredGeneratorMap.put(klass, generator);
	}
	
	public ValueGenerator<?> forClass(Class<?> targetClass) {
		Validate.notNull(targetClass, "Null class not allowed");
		ValueGenerator<?> generator = registeredGeneratorMap.get(targetClass);
		if (generator == null) {
			for (ValueGenerator<?> gen: STOCK_GENERATORS) {
				if (gen.canGenerate(targetClass)) {
					registeredGeneratorMap.put(targetClass, gen);
					return gen;
				}
			}
		}
		
		return generator;
	}
	

}
