package org.openherbarium.vaadin.piwik.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class LoginView extends CustomComponent implements View {
  public LoginView() {
    setCompositionRoot(new Label("LOGIN-VIEW"));
  }
}
