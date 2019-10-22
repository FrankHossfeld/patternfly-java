package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.div;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import elemental2.dom.HTMLDivElement;

/**
 * PatternFly pagination component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/pagination">https://www.patternfly.org/v4/documentation/core/components/pagination</a>
 */
public class Pagination extends ElementBuilder<HTMLDivElement, Pagination>
        implements HtmlContent<HTMLDivElement, Pagination> {

    Pagination() {
        super(div().get());
    }

    @Override
    public Pagination that() {
        return this;
    }
}
