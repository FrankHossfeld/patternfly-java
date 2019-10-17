package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.form;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.form;

/**
 * PatternFly form component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/form">https://www.patternfly.org/v4/documentation/core/components/form</a>
 */
public class Form implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public Form() {
        root = form().css(component(form))
                .get();
    }

    public Form group(FormGroup group) {
        root.appendChild(group.get());
        return this;
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
