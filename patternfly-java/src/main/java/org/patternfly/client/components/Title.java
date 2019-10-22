package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.title;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.CSS.Size;

import elemental2.dom.HTMLHeadingElement;

/**
 * PatternFly title component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/react/components/title/">https://www.patternfly.org/v4/documentation/react/components/title</a>
 */
public class Title extends ElementBuilder<HTMLHeadingElement, Title>
        implements HtmlContent<HTMLHeadingElement, Title> {

    Title(int level, String text, Size size) {
        super(h(level, text).css(component(title), size.modifier()).get());
    }

    @Override
    public Title that() {
        return this;
    }
}
