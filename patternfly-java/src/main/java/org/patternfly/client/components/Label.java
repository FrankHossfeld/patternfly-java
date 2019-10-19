package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.label;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Constants;

/**
 * PatternFly chip component.
 *
 * @see <a
 *     href="https://www.patternfly.org/v4/documentation/react/components/label/">https://www.patternfly.org/v4/documentation/react/components/label</a>
 */
public class Label implements IsElement<HTMLElement> {

  public static Label text(String text) {
    return Label.text(text, false);
  }

  public static Label text(String text, boolean compact) {
    return new Label(span().get(), text, compact);
  }

  // ------------------------------------------------------ factory methods

  private final HTMLElement root;

  // ------------------------------------------------------ label instance

  private Label(HTMLElement element, String text, boolean compact) {
    this.root = element;

    this.root.classList.add(component(label));

    root.textContent = text;

    if (compact) {
      root.classList.add(modifier(Constants.compact));
    }
  }

  @Override
  public HTMLElement element() {
    return root;
  }

  // ------------------------------------------------------ public API

  public Label setText(String text) {
    this.root.textContent = text;
    return this;
  }
}
