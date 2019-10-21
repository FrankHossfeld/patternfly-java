package org.patternfly.client.components;

import elemental2.dom.HTMLImageElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;

import static org.jboss.gwt.elemento.core.Elements.img;
import static org.patternfly.client.resources.Constants.brand;

/**
 * PatternFly brand component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/brand">https://www.patternfly.org/v4/documentation/core/components/brand</a>
 */
public class Brand extends ElementBuilder<HTMLImageElement, Brand> {

    public Brand(String src) {
        super(img(src).css(brand).get());
    }

    @Override
    public Brand that() {
        return this;
    }
}
