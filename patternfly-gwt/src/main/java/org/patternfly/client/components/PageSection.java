package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.CSS;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.section;

public class PageSection extends ElementBuilder<HTMLElement, PageSection>
        implements HtmlContent<HTMLElement, PageSection> {

    public PageSection() {
        super(section()
                .css(CSS.component(Constants.page, Constants.mainSection))
                .get());
    }

    @Override
    public PageSection that() {
        return this;
    }
}
