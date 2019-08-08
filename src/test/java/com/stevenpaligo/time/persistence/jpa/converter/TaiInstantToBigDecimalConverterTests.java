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
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.threeten.extra.scale.TaiInstant;

public class TaiInstantToBigDecimalConverterTests {

  @Test
  @DisplayName("TaiInstantToBigDecimalConverter: convertToDatabaseColumn() accepts null")
  public void test1() {

    // the call to convertToDatabaseColumn(...) will accept a null
    assertDoesNotThrow(() -> {
      new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(null);
    });


    // the call to convertToDatabaseColumn(...) returns null when given a null
    assertNull(new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(null));
  }


  @Test
  @DisplayName("TaiInstantToBigDecimalConverter: convertToDatabaseColumn() conversion checks")
  public void test2() {

    // simple test of correctness
    assertEquals(BigDecimal.valueOf(123456789000L, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(123, 456789000)));


    // the instant has only seconds
    assertEquals(BigDecimal.valueOf(123000000000L, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(123, 0)));


    // the instant has only nanoseconds
    assertEquals(BigDecimal.valueOf(123, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(0, 123)));


    // the instant's nanosecods have 9 digits
    assertEquals(BigDecimal.valueOf(99123456789L, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(99, 123456789)));


    // the instant's nanosecods have less than 9 digits
    assertEquals(BigDecimal.valueOf(99000000123L, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(99, 123)));


    // the instant is negative
    assertEquals(BigDecimal.valueOf(-60000000123L, 9), new TaiInstantToBigDecimalConverter().convertToDatabaseColumn(TaiInstant.ofTaiSeconds(-60, 123)));
  }


  @Test
  @DisplayName("TaiInstantToBigDecimalConverter: convertToEntityAttribute() accepts null")
  public void test3() {

    // the call to convertToEntityAttribute(...) will accept a null
    assertDoesNotThrow(() -> {
      new TaiInstantToBigDecimalConverter().convertToEntityAttribute(null);
    });


    // the call to convertToEntityAttribute(...) returns null when given a null
    assertNull(new TaiInstantToBigDecimalConverter().convertToEntityAttribute(null));
  }


  @Test
  @DisplayName("TaiInstantToBigDecimalConverter: convertToEntityAttribute() conversion checks")
  public void test4() {

    // simple test of correctness
    assertEquals(TaiInstant.ofTaiSeconds(123, 456789000), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(123456789000L, 9)));


    // the instant has only seconds
    assertEquals(TaiInstant.ofTaiSeconds(123, 0), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(123000000000L, 9)));


    // the instant has only nanoseconds
    assertEquals(TaiInstant.ofTaiSeconds(0, 123), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(123, 9)));


    // the instant's nanosecods have 9 digits
    assertEquals(TaiInstant.ofTaiSeconds(99, 123456789), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(99123456789L, 9)));


    // the instant's nanosecods have less than 9 digits
    assertEquals(TaiInstant.ofTaiSeconds(99, 123), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(99000000123L, 9)));


    // the instant is negative
    assertEquals(TaiInstant.ofTaiSeconds(-60, -123), new TaiInstantToBigDecimalConverter().convertToEntityAttribute(BigDecimal.valueOf(-60000000123L, 9)));
  }
}
