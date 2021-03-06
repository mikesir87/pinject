/*
 * File created on Aug 27, 2014 
 *
 * Copyright (c) 2014 Carl Harris, Jr.
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
package org.soulwing.cdi.properties.extension;


/**
 * An exception thrown when the target type for a property injection has no
 * associated converter.
 *
 * @author Carl Harris
 */
class UnsupportedTypeException extends Exception {

  private static final long serialVersionUID = -8403584692907056644L;

  /**
   * Constructs a new instance.
   */
  UnsupportedTypeException() {
  }

  /**
   * Constructs a new instance.
   * @param memberName fully-qualified member name of the injection point
   * @param type type of the member
   */
  <T> UnsupportedTypeException(String memberName, Class<T> type) {
    super(memberName + " has type " + type.getName()
        + " for which there is no converter available");
  }
  
}
