package org.openherbarium.vaadin.piwik;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class MainComponent extends HorizontalLayout {
  private Button startButton =
      new Button("Start", event -> UI.getCurrent().getNavigator().navigateTo(MainUI.START_VIEW));
  private Button loginButton =
      new Button("Login", event -> UI.getCurrent().getNavigator().navigateTo(MainUI.LOGIN_VIEW));
  private Button otherButton =
      new Button("Other", event -> UI.getCurrent().getNavigator().navigateTo(MainUI.OTHER_VIEW));
  private VerticalLayout buttonlayout = new VerticalLayout(startButton, loginButton, otherButton);

  private Panel contentArea = new Panel();

  public MainComponent() {
    addComponent(buttonlayout);
    addComponent(contentArea);
  }

  public Panel getContentArea() {
    return contentArea;
  }
}
