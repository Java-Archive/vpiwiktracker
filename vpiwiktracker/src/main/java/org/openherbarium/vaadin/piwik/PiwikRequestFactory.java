package org.openherbarium.vaadin.piwik;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;
import javax.servlet.http.HttpServletRequest;
import org.piwik.java.tracking.PiwikRequest;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinServletRequest;

public class PiwikRequestFactory {
  private final String authToken;

  private final String ua;
  private final String url;
  private final String remoteAddr;
  private final String visitorId;

  // not final because you might want to change the siteId after creating the object from DI and
  // your vaadin app is serving for more than one url
  private int siteId;
  // not final because the user might log in later
  private String userId = null;

  public PiwikRequestFactory(VaadinServletRequest vaadinServletRequest, String authToken,
      int siteId) {
    HttpServletRequest servletRequest = vaadinServletRequest.getHttpServletRequest();
    this.ua = servletRequest.getHeader("User-Agent");
    this.url = servletRequest.getRequestURL().toString();
    this.visitorId = PiwikRequest.getRandomHexString(PiwikRequest.ID_LENGTH);
    this.remoteAddr = getClientIpAddress(servletRequest);
    this.authToken = authToken;
    this.siteId = siteId;
  }

  public PiwikRequest createPiwikRequest(String actionUrl) throws MalformedURLException {
    PiwikRequest request = new PiwikRequest(siteId, new URL(url));
    request.setHeaderUserAgent(ua);
    request.setAuthToken(authToken);
    if (userId != null) {
      request.setUserId(userId);
    }
    request.setVisitorId(visitorId);
    request.setVisitorIp(remoteAddr);
    request.setActionUrlWithString(actionUrl);
    return request;
  }

  public PiwikRequest createPiwikRequest(ViewChangeEvent event) throws MalformedURLException {
    String actionUrl = url + "#" + event.getViewName() + "/" + event.getParameters();
    return createPiwikRequest(actionUrl);
  }

  // from https://stackoverflow.com/a/21884642/2602034
  private String getClientIpAddress(HttpServletRequest request) {
    String xForwardedForHeader = request.getHeader("X-Forwarded-For");
    if (xForwardedForHeader == null) {
      return request.getRemoteAddr();
    } else {
      // As of https://en.wikipedia.org/wiki/X-Forwarded-For
      // The general format of the field is: X-Forwarded-For: client, proxy1, proxy2 ...
      // we only want the client
      return new StringTokenizer(xForwardedForHeader, ",").nextToken().trim();
    }
  }

  public void setSiteId(int siteId) {
    this.siteId = siteId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
