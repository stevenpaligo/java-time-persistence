/*
 * The author licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevenpaligo.time.persistence.jpa.converter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.scale.TaiInstant;

public class TaiInstantToStringConverterTests {

  @Test
  @DisplayName("TaiInstantToStringConverter: convertToDatabaseColumn() accepts null")
  public void test1() {

    // the call to convertToDatabaseColumn(...) will accept a null
    assertDoesNotThrow(() -> {
      new TaiInstantToStringConverter().convertToDatabaseColumn(null);
    });


    // the call to convertToDatabaseColumn(...) returns null when given a null
    assertNull(new TaiInstantToStringConverter().convertToDatabaseColumn(null));
  }


  @Test
  @DisplayName("TaiInstantToStringConverter: convertToDatabaseColumn() conversion checks")
  public void test2() {

    // simple test of correctness
    assertEquals("123.000456789s(TAI)", new TaiInstantToStringConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(123, 456789)));
  }


  @Test
  @DisplayName("TaiInstantToStringConverter: convertToEntityAttribute() accepts null")
  public void test3() {

    // the call to convertToEntityAttribute(...) will accept a null
    assertDoesNotThrow(() -> {
      new TaiInstantToStringConverter().convertToEntityAttribute(null);
    });


    // the call to convertToEntityAttribute(...) returns null when given a null
    assertNull(new TaiInstantToStringConverter().convertToEntityAttribute(null));
  }


  @Test
  @DisplayName("TaiInstantToStringConverter: convertToEntityAttribute() conversion checks")
  public void test4() {

    // simple test of correctness
    assertEquals(TaiInstant.ofTaiSeconds(123, 456789), new TaiInstantToStringConverter().convertToEntityAttribute("123.000456789s(TAI)"));
  }
}
