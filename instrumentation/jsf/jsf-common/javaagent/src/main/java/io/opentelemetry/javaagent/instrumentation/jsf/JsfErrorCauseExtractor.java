/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.jsf;

import io.opentelemetry.instrumentation.api.instrumenter.ErrorCauseExtractor;
import javax.faces.FacesException;

public class JsfErrorCauseExtractor implements ErrorCauseExtractor {

  @Override
  public Throwable extract(Throwable error) {
    while (error.getCause() != null && error instanceof FacesException) {
      error = error.getCause();
    }
    return ErrorCauseExtractor.jdk().extract(error);
  }
}
