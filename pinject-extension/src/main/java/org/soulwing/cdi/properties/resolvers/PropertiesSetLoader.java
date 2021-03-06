/*
 * File created on Sep 7, 2014 
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
package org.soulwing.cdi.properties.resolvers;

import java.io.IOException;

/**
 * An object that loads a {@link PropertiesSet} from some resource.
 *
 * @author Carl Harris
 */
interface PropertiesSetLoader {

  /**
   * Loads a properties set for the path specified by the given property
   * reference.
   * @param ref property reference
   * @return properties set
   * @throws IOException if an error occurs in loading the properties
   */
  PropertiesSet load(PropertyRef ref) throws IOException;
  
}
