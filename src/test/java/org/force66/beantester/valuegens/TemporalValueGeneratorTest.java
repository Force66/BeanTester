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

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

public class TemporalValueGeneratorTest {
	
	TemporalValueGenerator generator;

	@Test
	public void testBasic() throws Exception {
		generator = new TemporalValueGenerator(Date.class);
		Assert.assertTrue(generator.canGenerate(Date.class));
		Assert.assertTrue(!generator.canGenerate(Long.class));
		Assert.assertTrue(!generator.canGenerate(null));
	}

}
