package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.img;
import static org.patternfly.client.resources.Constants.brand;

/**
 * PatternFly brand component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/brand">https://www.patternfly.org/v4/documentation/core/components/brand</a>
 */
public class Brand implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public Brand(String src) {
        root = img(src).css(brand).get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
