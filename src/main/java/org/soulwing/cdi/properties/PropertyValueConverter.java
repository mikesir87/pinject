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


/**
 * An object that converts a string representation of a property value to
 * an object of a target type.
 *
 * @author Carl Harris
 */
interface PropertyValueConverter {

  /**
   * Converts the given value to an instance of the given type.
   * @param value the subject value
   * @param type the target type
   * @return object representation of the given value
   * @throws UnsupportedTypeException
   */
  Object convert(String value, Class<?> type) 
      throws UnsupportedTypeException;

  /**
   * Converts the given value to an instance of the given type using a
   * named converter.
   * @param converter name of the converter to use
   * @param value the subject value
   * @param type the target type
   * @return object representation of the given value
   * @throws UnsupportedTypeException
   * @throws NoSuchConverterException
   */
  Object convert(String converter, String value, Class<?> type)
      throws NoSuchConverterException, UnsupportedTypeException;
  
}