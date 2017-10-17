package org.openherbarium.vaadin.piwik.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class MainView extends CustomComponent implements View {
  public MainView() {
    setCompositionRoot(new Label("MAIN-VIEW"));
  }
}
