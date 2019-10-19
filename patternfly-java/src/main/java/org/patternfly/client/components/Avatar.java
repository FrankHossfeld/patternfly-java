package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.img;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.avatar;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import org.jboss.gwt.elemento.core.IsElement;

/**
 * PatternFly chip component.
 *
 * @see <a
 *     href="https://www.patternfly.org/v4/documentation/react/components/avatar/">https://www.patternfly.org/v4/documentation/react/components/avatar</a>
 */
public class Avatar implements IsElement<HTMLElement> {

  private final HTMLElement root;

  private Avatar(HTMLImageElement element, String src, String alt, String className) {
    element.src = src;

    if (alt != null) {
      element.alt = alt;
    }

    this.root = element;

    this.root.classList.add(component(avatar));

    if (className != null) {
      this.root.classList.add(className);
    }
  }

  // ------------------------------------------------------ factory methods

  public static Avatar avatar(String src, String alt) {
    return Avatar.avatar(src, alt, null);
  }

  // ------------------------------------------------------ avatar instance

  public static Avatar avatar(String src, String alt, String className) {
    return new Avatar(img().get(), src, alt, className);
  }

  @Override
  public HTMLElement element() {
    return root;
  }

  // ------------------------------------------------------ public API

}
