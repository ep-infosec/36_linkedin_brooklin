/**
 *  Copyright 2019 LinkedIn Corporation. All rights reserved.
 *  Licensed under the BSD 2-Clause License. See the LICENSE file in the project root for license information.
 *  See the NOTICE file in the project root for additional information regarding copyright ownership.
 */
package com.linkedin.datastream.metrics;

import java.util.function.Supplier;

import com.codahale.metrics.Gauge;


/**
 * Wrapper for a gauge with reset of value supplier.
 * This is useful when registering a new Gauge function
 * for an existing Gauge metric.
 */
class ResettableGauge<T> implements Gauge<T> {
  private Supplier<T> _supplier;

  public ResettableGauge() {
  }

  public ResettableGauge(Supplier<T> supplier) {
    _supplier = supplier;
  }

  @Override
  public T getValue() {
    return _supplier.get();
  }

  public void setSupplier(Supplier<T> supplier) {
    _supplier = supplier;
  }
}
