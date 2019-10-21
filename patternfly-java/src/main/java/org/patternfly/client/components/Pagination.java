package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.div;

/**
 * PatternFly pagination component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/pagination">https://www.patternfly.org/v4/documentation/core/components/pagination</a>
 */
public class Pagination extends ElementBuilder<HTMLDivElement, Pagination>
        implements HtmlContent<HTMLDivElement, Pagination> {

    public Pagination() {
        super(div().get());
    }

    @Override
    public Pagination that() {
        return this;
    }
}
