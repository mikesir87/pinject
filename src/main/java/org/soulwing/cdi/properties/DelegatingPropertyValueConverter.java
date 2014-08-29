/*
 * File created on Aug 27, 2014 
 *
 * Copyright (c) 2014 Virginia Polytechnic Institute and State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.soulwing.cdi.properties;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import org.soulwing.cdi.properties.spi.PropertyConverter;

/**
 * A {@link PropertyValueConverter} that delegates to providers located using
 * the {@link ServiceLoader} mechanism.
 *
 * @author Carl Harris
 */
class DelegatingPropertyValueConverter implements PropertyValueConverter {

  private final Set<PropertyConverter> converters = new LinkedHashSet<>();
  
  private final Map<String, PropertyConverter> converterMap = 
      new LinkedHashMap<>();
  
  /**
   * Constructs a new instance.
   */
  DelegatingPropertyValueConverter() {
    for (PropertyConverter converter : 
      ServiceLoader.load(PropertyConverter.class)) {
      converters.add(converter);
      if (converter.getName() != null) {
        converterMap.put(converter.getName(), converter);
      }
    }
  }
  
  
  /**
   * {@inheritDoc}
   */
  public Object convert(String value, Class<?> type) 
      throws UnsupportedTypeException {
    for (PropertyConverter converter : converters) {
      if (!converter.supports(type)) continue;
      return converter.convert(value, null);
    }
    throw new UnsupportedTypeException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object convert(String converterName, String value, Class<?> type)
      throws NoSuchConverterException, UnsupportedTypeException {
    PropertyConverter converter = converterMap.get(converterName);
    if (converter == null) {
      throw new NoSuchConverterException();
    }
    if (!(converter.supports(type))) {
      throw new UnsupportedOperationException();
    }
    return converter.convert(value, null);
  }
  
}