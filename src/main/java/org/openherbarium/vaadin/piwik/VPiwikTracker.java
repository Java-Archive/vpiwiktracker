package org.openherbarium.vaadin.piwik;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.CompletableFuture;

import org.apache.http.HttpResponse;
import org.piwik.java.tracking.PiwikRequest;
import org.piwik.java.tracking.PiwikTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;


public class VPiwikTracker {
  private static final Logger LOGGER = LoggerFactory.getLogger(VPiwikTracker.class);

  private final String authToken;
  private final String hostUrl;

  private boolean initialized = false;
  private PiwikTracker piwikTracker;
  private PiwikRequestFactory requestFacotry;

  private int siteId;


  public VPiwikTracker(String authToken, String hostUrl, int siteId) {
    super();
    this.authToken = authToken;
    this.hostUrl = hostUrl;
    this.siteId = siteId;
  }

  public void init(VaadinRequest vaadinRequest) {
    if (vaadinRequest instanceof VaadinServletRequest) {
      piwikTracker = new PiwikTracker(hostUrl);
      VaadinServletRequest vaadinServletRequest = (VaadinServletRequest) vaadinRequest;
      requestFacotry = new PiwikRequestFactory(vaadinServletRequest, authToken, siteId);
      initialized = true;
    } else {
      throw new IllegalStateException("Portlets are not supported.");
    }

  }

  public CompletableFuture<HttpResponse> track(ViewChangeEvent event) {
    checkInitialized();
    try {
      PiwikRequest request = requestFacotry.createPiwikRequest(event);
      return CompletableFuture.supplyAsync(() -> doTrack(request));
    } catch (MalformedURLException e) {
      LOGGER.error("Failure creating piwik request", e);
      return null;
    }
  }

  private void checkInitialized() {
    if (!initialized) {
      throw new IllegalStateException("VPiwikTracker must be initialized, before using it.");
    }
  }

  public CompletableFuture<HttpResponse> track(String actionURL) {
    checkInitialized();
    try {
      PiwikRequest request = requestFacotry.createPiwikRequest(actionURL);
      return CompletableFuture.supplyAsync(() -> doTrack(request));
    } catch (MalformedURLException e) {
      LOGGER.error("Failure creating piwik request", e);
      return null;
    }
  }

  private HttpResponse doTrack(PiwikRequest request) {
    try {
      HttpResponse response = piwikTracker.sendRequest(request);
      LOGGER.debug("Response: {}", response);
      return response;
    } catch (IOException e) {
      LOGGER.error("Failure sending piwik request", e);
      return null;
    }
  }

  public void setUserId(String userId) {
    checkInitialized();
    requestFacotry.setUserId(userId);
  }

  public void setSiteId(int siteId) {
    checkInitialized();
    requestFacotry.setSiteId(siteId);
  }

   /**
    * This should be only used for testing, the instance of the
    * {@link PiwikTracker} is created in the {@link #init(VaadinRequest)}
    * method.
    *
    * @param piwikTracker
    */
  public void setPiwikTracker(PiwikTracker piwikTracker) {
    this.piwikTracker = piwikTracker;
  }
}
