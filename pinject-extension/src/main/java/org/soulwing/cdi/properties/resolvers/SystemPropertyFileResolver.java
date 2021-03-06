/*
 * File created on Aug 30, 2014 
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

import java.net.URL;
import java.util.logging.Logger;

import org.soulwing.cdi.properties.converters.UrlPropertyConverter;
import org.soulwing.cdi.properties.spi.PropertyResolver;

/**
 * A {@link PropertyResolver} that loads a property file referenced by a
 * System property.
 *
 * @author Michael Irwin
 */
public class SystemPropertyFileResolver implements PropertyResolver {

  private static final Logger logger = Logger.getLogger(
      SystemPropertyFileResolver.class.getName());

  private static final String PROPERTY_NAME = "pinject.properties";
  private static final int PRIORITY = -2;

  private final PropertiesSet propertiesSet = new PropertiesSet();

  /**
   * {@inheritDoc}
   */
  @Override
  public void init() throws Exception {
    String propertyValue = System.getProperty(PROPERTY_NAME);
    if (propertyValue == null) {
      logger.info("System property lookup for '" + PROPERTY_NAME + "' returned nothing");
      return;
    }

    logger.info("System property lookup for '" + PROPERTY_NAME + "' returned: " + propertyValue);
    UrlPropertyConverter converter = new UrlPropertyConverter();
    String[] locations = propertyValue.split("\\s*(,|\\s)\\s*");

    for (String location : locations) {
      logger.info("loading bean properties from " + location);
      propertiesSet.load((URL) converter.convert(location, null));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void destroy() throws Exception {
    propertiesSet.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getPriority() {
    return PRIORITY;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String resolve(String name) {
    return propertiesSet.getProperty(name);
  }

}
