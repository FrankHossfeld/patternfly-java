package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.title;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLHeadingElement;
import org.jboss.gwt.elemento.core.IsElement;

/**
 * PatternFly chip component.
 *
 * @see <a
 *     href="https://www.patternfly.org/v4/documentation/react/components/avatar/">https://www.patternfly.org/v4/documentation/react/components/avatar</a>
 */
public class Title implements IsElement<HTMLElement> {

  private final HTMLHeadingElement root;

  private Title(HeadingLevel headingLevel, Size size, String text, String className) {
    this.root = h(headingLevel.getLevel()).get();
    this.root.textContent = text;
    this.root.classList.add(component(title));
    this.root.classList.add(modifier(size.getSize()));

    if (className != null) {
      this.root.classList.add(className);
    }
  }

  // ------------------------------------------------------ factory methods

  public static Title title(HeadingLevel headingLevel, Size size, String text) {
    return Title.title(headingLevel, size, text, null);
  }

  // ------------------------------------------------------ title instance

  public static Title title(HeadingLevel headingLevel, Size size, String text, String className) {
    return new Title(headingLevel, size, text, className);
  }

  @Override
  public HTMLElement element() {
    return root;
  }

  // ------------------------------------------------------ public API

  public enum HeadingLevel {
    H1(1),
    H2(2),
    H3(3),
    H4(4),
    H5(5),
    H6(6);

    private int level;

    HeadingLevel(int level) {
      this.level = level;
    }

    public int getLevel() {
      return level;
    }
  }

  public enum Size {
    SIZE_4XL("4xl"),
    SIZE_3XL("3xl"),
    SIZE_2XL("2xl"),
    SIZE_XL("xl"),
    SIZE_LG("lg"),
    SIZE_MD("md");

    private String size;

    Size(String size) {
      this.size = size;
    }

    public String getSize() {
      return size;
    }
  }
}
