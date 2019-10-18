package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.section;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.mainSection;
import static org.patternfly.client.resources.Constants.page;

public class PageSection extends ElementBuilder<HTMLElement, PageSection>
        implements HtmlContent<HTMLElement, PageSection> {

    public PageSection() {
        super(section()
                .css(component(page, mainSection))
                .get());
    }

    @Override
    public PageSection that() {
        return this;
    }
}
