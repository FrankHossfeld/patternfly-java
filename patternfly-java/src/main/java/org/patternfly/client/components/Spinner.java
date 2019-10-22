package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.*;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.CSS.Size;

import elemental2.dom.HTMLElement;

public class Spinner extends ElementBuilder<HTMLElement, Spinner>
        implements HtmlContent<HTMLElement, Spinner> {

    Spinner(Size size) {
        super(span().css(component(spinner)).get());
        if (size != null) {
            css().add(size.modifier());
        }
        aria(valueText, "Loading...");
        attr(role, progressbar);
        add(span().css(component(spinner, clipper)));
        add(span().css(component(spinner, leadBall)));
        add(span().css(component(spinner, tailBall)));
    }

    @Override
    public Spinner that() {
        return this;
    }
}
