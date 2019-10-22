package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.label;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLElement;

/**
 * PatternFly label component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/react/components/label/">https://www.patternfly.org/v4/documentation/react/components/label</a>
 */
public class Label extends ElementBuilder<HTMLElement, Label>
        implements HtmlContent<HTMLElement, Label> {

    Label(String text) {
        this(text, false);
    }

    Label(String text, boolean compact) {
        super(span().css(component(label)).textContent(text).get());
        if (compact) {
            element.classList.add(modifier(Constants.compact));
        }
    }

    @Override
    public Label that() {
        return this;
    }
}
