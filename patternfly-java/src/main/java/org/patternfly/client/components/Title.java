package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.title;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import elemental2.dom.HTMLHeadingElement;

/**
 * PatternFly title component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/react/components/title/">https://www.patternfly.org/v4/documentation/react/components/title</a>
 */
public class Title extends ElementBuilder<HTMLHeadingElement, Title>
        implements HtmlContent<HTMLHeadingElement, Title> {

    public Title(int level, String text, Size size) {
        super(h(level, text).css(component(title), modifier(size.size)).get());
    }

    @Override
    public Title that() {
        return this;
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
