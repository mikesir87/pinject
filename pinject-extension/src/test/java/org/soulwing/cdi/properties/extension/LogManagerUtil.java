/*
 * File created on Sep 5, 2014 
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

import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Static utility method for LogManager configuration.
 *
 * @author Carl Harris
 */
class LogManagerUtil {

  public static void configure() throws IOException {
    LogManager.getLogManager().readConfiguration(
        Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("logging.properties"));
  }

}
