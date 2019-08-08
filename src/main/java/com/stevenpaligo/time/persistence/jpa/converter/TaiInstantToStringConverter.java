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

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.threeten.extra.scale.TaiInstant;

/**
 * JPA entity attribute converter for storing {@link org.threeten.extra.scale.TaiInstant}s as string
 * values in a database. The string format is the same as what is produced by <code>TaiInstant</code>'s
 * <code>toString()</code> method.
 * 
 * <p>
 * <strong>Note:</strong> To correctly store the value, the database field must have a length of at
 * least 35 characters.
 * </p>
 * 
 * @author Steven Paligo
 */
@Converter(autoApply = true)
public class TaiInstantToStringConverter implements AttributeConverter<TaiInstant, String> {

  /**
   * {@inheritDoc}
   */
  @Override
  public String convertToDatabaseColumn(TaiInstant instant) {

    if (instant != null) {

      return instant.toString();

    } else {

      return null;
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public TaiInstant convertToEntityAttribute(String value) {

    if (value != null) {

      return TaiInstant.parse(value);

    } else {

      return null;
    }
  }
}
