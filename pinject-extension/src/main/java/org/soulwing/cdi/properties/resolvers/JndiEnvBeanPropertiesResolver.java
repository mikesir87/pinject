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
package org.soulwing.cdi.properties.resolvers;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;
import javax.naming.NamingException;

import org.soulwing.cdi.properties.converters.UrlPropertyConverter;
import org.soulwing.cdi.properties.spi.PropertyResolver;

/**
 * A resolver that looks up a JNDI environment variable and (if found)
 * interprets it as a space and/or comma delimited list of URLs to 
 * properties files to consult (in the given order) for name resolution.
 *
 * @author Carl Harris
 */
public class JndiEnvBeanPropertiesResolver implements PropertyResolver {

  private static final Logger logger = Logger.getLogger(
      JndiEnvBeanPropertiesResolver.class.getName());
  
  private static final String BINDING =
      "java:comp/env/beans.properties.location";

  private static final int PRIORITY = -4;
  
  private final PropertiesSet propertiesSet = new PropertiesSet();
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void init() throws IOException, NamingException {
    UrlPropertyConverter converter = new UrlPropertyConverter();
    Object boundValue = JndiObjectLocator.getInstance().lookup(BINDING);
    if (boundValue == null) return;
    String[] locations = boundValue.toString().split("\\s*(,|\\s)\\s*");
    
    for (String location : locations) {
      logger.info("loading bean properties from " + location);
      propertiesSet.load((URL) converter.convert(location, null));
    }
  }

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

  @Override
  public String resolve(String name) {
    return propertiesSet.getProperty(name);
  }

}
