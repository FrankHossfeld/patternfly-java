package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.card;

/**
 * PatternFly card component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/card">https://www.patternfly.org/v4/documentation/core/components/card</a>
 */
public class Card extends ElementBuilder<HTMLElement, Card>
        implements HtmlContent<HTMLElement, Card> {

    public Card() {
        super(div().css(component(card))
                .get());
    }

    @Override
    public Card that() {
        return this;
    }
}
