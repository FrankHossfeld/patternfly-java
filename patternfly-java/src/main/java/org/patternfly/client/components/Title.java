package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLHeadingElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.title;

/**
 * PatternFly title component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/react/components/title/">https://www.patternfly.org/v4/documentation/react/components/title</a>
 */
public class Title implements IsElement<HTMLElement> {

    private final HTMLHeadingElement root;

    public Title(int level, String text, Size size) {
        root = h(level, text).css(component(title), modifier(size.size)).get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }


    // ------------------------------------------------------ inner classes


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
    }
}
