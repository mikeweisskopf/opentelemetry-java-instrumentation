/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.httpurlconnection;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

import io.opentelemetry.instrumentation.api.instrumenter.http.HttpClientAttributesGetter;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;
import java.net.HttpURLConnection;
import java.util.List;
import javax.annotation.Nullable;

class HttpUrlHttpAttributesGetter
    implements HttpClientAttributesGetter<HttpURLConnection, Integer> {

  @Override
  public String method(HttpURLConnection connection) {
    return connection.getRequestMethod();
  }

  @Override
  public String url(HttpURLConnection connection) {
    return connection.getURL().toExternalForm();
  }

  @Override
  public List<String> requestHeader(HttpURLConnection connection, String name) {
    String value = connection.getRequestProperty(name);
    return value == null ? emptyList() : singletonList(value);
  }

  @Override
  public String flavor(HttpURLConnection connection, @Nullable Integer statusCode) {
    return SemanticAttributes.HttpFlavorValues.HTTP_1_1;
  }

  @Override
  public Integer statusCode(
      HttpURLConnection connection, Integer statusCode, @Nullable Throwable error) {
    return statusCode;
  }

  @Override
  public List<String> responseHeader(
      HttpURLConnection connection, Integer statusCode, String name) {
    String value = connection.getHeaderField(name);
    return value == null ? emptyList() : singletonList(value);
  }
}
