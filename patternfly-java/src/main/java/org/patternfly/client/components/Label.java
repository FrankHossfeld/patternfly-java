package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.label;

/**
 * PatternFly label component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/react/components/label/">https://www.patternfly.org/v4/documentation/react/components/label</a>
 */
public class Label implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public Label(String text) {
        this(text, false);
    }

    public Label(String text, boolean compact) {
        root = span().css(component(label)).get();
        if (compact) {
            root.classList.add(modifier(Constants.compact));
        }
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    public Label setText(String text) {
        this.root.textContent = text;
        return this;
    }
}
