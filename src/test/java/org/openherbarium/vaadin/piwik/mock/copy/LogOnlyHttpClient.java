package org.openherbarium.vaadin.piwik.mock.copy;

import java.io.IOException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A http client which only logs, but does not send.
 * 
 */
public class LogOnlyHttpClient implements HttpClient {
  private static final Logger LOGGER = LoggerFactory.getLogger(LogOnlyHttpClient.class);

  @Override
  public HttpParams getParams() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ClientConnectionManager getConnectionManager() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HttpResponse execute(HttpUriRequest request) throws IOException, ClientProtocolException {
    LOGGER.info("Would send request {}", request);
    return new BasicHttpResponse(
        new BasicStatusLine(new ProtocolVersion("http", 1, 0), 206, "bla"));
  }

  @Override
  public HttpResponse execute(HttpUriRequest request, HttpContext context)
      throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HttpResponse execute(HttpHost target, HttpRequest request)
      throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public HttpResponse execute(HttpHost target, HttpRequest request, HttpContext context)
      throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler)
      throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T execute(HttpUriRequest request, ResponseHandler<? extends T> responseHandler,
      HttpContext context) throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T execute(HttpHost target, HttpRequest request,
      ResponseHandler<? extends T> responseHandler) throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T> T execute(HttpHost target, HttpRequest request,
      ResponseHandler<? extends T> responseHandler, HttpContext context)
      throws IOException, ClientProtocolException {
    // TODO Auto-generated method stub
    return null;
  }

}
