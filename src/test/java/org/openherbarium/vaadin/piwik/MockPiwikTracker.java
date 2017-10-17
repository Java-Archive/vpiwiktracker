package org.openherbarium.vaadin.piwik;

import org.apache.http.client.HttpClient;
import org.openherbarium.vaadin.piwik.mock.LogOnlyHttpClient;
import org.piwik.java.tracking.PiwikTracker;

public class MockPiwikTracker extends PiwikTracker {
  public MockPiwikTracker(String hostUrl, String proxyHost, int proxyPort) {
    super(hostUrl, proxyHost, proxyPort);
  }

  public MockPiwikTracker(String hostUrl) {
    super(hostUrl);
  }

  @Override
  protected HttpClient getHttpClient() {
    return new LogOnlyHttpClient();
  }
}
