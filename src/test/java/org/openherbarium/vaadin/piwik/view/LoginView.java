package org.openherbarium.vaadin.piwik.view;

import org.openherbarium.vaadin.piwik.MainUI;
import org.openherbarium.vaadin.piwik.VPiwikTracker;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginView extends CustomComponent implements View {
  public LoginView(VPiwikTracker vPiwikTracker) {

    VerticalLayout layout = new VerticalLayout();
    layout.addComponent(new Label("LOGIN-VIEW"));
    layout.addComponent(new Button("Login", event -> {
      vPiwikTracker.setUserId("A-logged-in-user");
      UI.getCurrent().getNavigator().navigateTo(MainUI.START_VIEW);
    }));
    layout.addComponent(new Button("Logut", event -> {
      vPiwikTracker.setUserId(null);
      UI.getCurrent().getNavigator().navigateTo(MainUI.START_VIEW);
    }));
    setCompositionRoot(layout);
  }
}
