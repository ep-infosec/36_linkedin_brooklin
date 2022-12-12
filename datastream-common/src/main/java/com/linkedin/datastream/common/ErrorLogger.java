/**
 *  Copyright 2019 LinkedIn Corporation. All rights reserved.
 *  Licensed under the BSD 2-Clause License. See the LICENSE file in the project root for license information.
 *  See the NOTICE file in the project root for additional information regarding copyright ownership.
 */
package com.linkedin.datastream.common;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;


/**
 * Helper utility methods for error logging.
 */
public class ErrorLogger {
  private final Logger _logger;

  /**
   * Construct an ErrorLogger
   * @param logger the actual logger to use
   */
  public ErrorLogger(Logger logger) {
    Validate.notNull(logger, "null logger");
    _logger = logger;
  }

  /**
   * Log error and throw DatastreamRuntimeException with inner exception
   * @param log logger object
   * @param msg error message
   * @param t inner exception
   */
  public static void logAndThrowDatastreamRuntimeException(Logger log, String msg, Throwable t) {
    if (t != null) {
      log.error(msg, t);
      throw new DatastreamRuntimeException(msg, t);
    } else {
      log.error(msg);
      throw new DatastreamRuntimeException(msg);
    }
  }

  /**
   * Log error and throw DatastreamRuntimeException with inner exception
   * @param log logger object
   * @param msg error message
   */
  public static void logAndThrowDatastreamRuntimeException(Logger log, String msg) {
    logAndThrowDatastreamRuntimeException(log, msg, null);
  }

  /**
   * Log error and throw DatastreamRuntimeException with inner exception
   * @param msg error message
   * @param t inner exception
   */
  public void logAndThrowDatastreamRuntimeException(String msg, Throwable t) {
    logAndThrowDatastreamRuntimeException(_logger, msg, t);
  }

  /**
   * Log error and throw DatastreamRuntimeException with inner exception
   * @param msg error message
   */
  public void logAndThrowDatastreamRuntimeException(String msg) {
    logAndThrowDatastreamRuntimeException(_logger, msg);
  }
}
