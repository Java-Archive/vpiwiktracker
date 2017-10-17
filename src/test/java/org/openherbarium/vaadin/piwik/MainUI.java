package org.openherbarium.vaadin.piwik;

import org.openherbarium.vaadin.piwik.mock.MockPiwikTracker;
import org.openherbarium.vaadin.piwik.view.LoginView;
import org.openherbarium.vaadin.piwik.view.MainView;
import org.openherbarium.vaadin.piwik.view.OtherView;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

public class MainUI extends UI {
  public static final String OTHER_VIEW = "other";
  public static final String LOGIN_VIEW = "login";
  public static final String START_VIEW = "";

  private VPiwikTracker vPiwikTracker;

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    MainComponent mainComponent = new MainComponent();

    Navigator navigator = new Navigator(this, mainComponent.getContentArea());


    vPiwikTracker =
        new VPiwikTracker("0123456789abcdef01234567890abcde", "https://piwik.dummy.xx", 1);
    vPiwikTracker.init(vaadinRequest);

    navigator.addViewChangeListener(event -> {
      vPiwikTracker.track(event);
      return true;
    });

    // This is only for demo purpose, this prevents us from a lot of exceptions, because there is no
    // piwik instance
    vPiwikTracker.setPiwikTracker(new MockPiwikTracker("https://piwik.dummy.xx"));

    navigator.addView(START_VIEW, MainView.class);
    navigator.addView(LOGIN_VIEW, new LoginView(vPiwikTracker));
    navigator.addView(OTHER_VIEW, OtherView.class);

    setContent(mainComponent);
  }


}
