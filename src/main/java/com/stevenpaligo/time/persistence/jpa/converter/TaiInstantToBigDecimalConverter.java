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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.threeten.extra.scale.TaiInstant;

/**
 * JPA entity attribute converter for storing {@link org.threeten.extra.scale.TaiInstant}s as exact precision
 * values in a database (i.e. <code>DECIMAL</code> or <code>NUMERIC</code> types). The number of TAI seconds
 * is encoded as the value's whole part and the number of nanoseconds is the value's fractional part.
 * 
 * <p>
 * <strong>Note:</strong> To correctly store the value, the database field must have a precision of 28 and a scale of 9.
 * </p>
 * 
 * @author Steven Paligo
 */
@Converter(autoApply = true)
public class TaiInstantToBigDecimalConverter implements AttributeConverter<TaiInstant, BigDecimal> {

  /**
   * {@inheritDoc}
   */
  @Override
  public BigDecimal convertToDatabaseColumn(TaiInstant instant) {

    if (instant != null) {

      BigDecimal seconds = new BigDecimal(instant.getTaiSeconds());
      BigDecimal nanos = new BigDecimal(BigInteger.valueOf(instant.getNano()), 9);
      BigDecimal combinedValue;


      // combine the seconds and nanoseconds
      if (seconds.compareTo(BigDecimal.ZERO) >= 0) {
        combinedValue = seconds.add(nanos);
      } else {
        combinedValue = seconds.subtract(nanos);
      }


      // ensure the correct scale
      combinedValue = combinedValue.setScale(9, RoundingMode.UNNECESSARY);


      return combinedValue;


    } else {


      return null;
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public TaiInstant convertToEntityAttribute(BigDecimal value) {

    if (value != null) {

      long seconds = value.divide(BigDecimal.ONE).longValueExact();
      long nanos = value.remainder(BigDecimal.ONE).movePointRight(9).longValueExact();

      return TaiInstant.ofTaiSeconds(seconds, nanos);


    } else {


      return null;
    }
  }
}
