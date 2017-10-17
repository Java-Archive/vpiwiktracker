package org.openherbarium.vaadin.piwik.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

public class OtherView extends CustomComponent implements View {
  public OtherView() {
    setCompositionRoot(new Label("OTHER-VIEW"));
  }
}
