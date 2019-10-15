package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.div;

/**
 * PatternFly pagination component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/pagination">https://www.patternfly.org/v4/documentation/core/components/pagination</a>
 */
public class Pagination implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public Pagination() {
        root = div().get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
