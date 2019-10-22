package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.Size.md;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;

/**
 * PatternFly card component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/card">https://www.patternfly.org/v4/documentation/core/components/card</a>
 */
public class Card extends ElementBuilder<HTMLDivElement, Card>
        implements HtmlContent<HTMLDivElement, Card> {

    Card() {
        super(div().css(component(card)).get());
    }

    @Override
    public Card that() {
        return this;
    }

    public Card compact() {
        element.classList.add(modifier(compact));
        return this;
    }

    public Card hoverable() {
        element.classList.add(modifier(hoverable));
        return this;
    }

    // ------------------------------------------------------ inner classes

    public static Head head() {
        return new Head();
    }

    public static class Head extends ElementBuilder<HTMLElement, Head>
            implements HtmlContent<HTMLElement, Head> {

        private Head() {
            super(div().css(component(card, head)).get());
        }

        @Override
        public Head that() {
            return this;
        }
    }

    public static Header header() {
        return new Header();
    }

    public static class Header extends ElementBuilder<HTMLElement, Header>
            implements HtmlContent<HTMLElement, Header> {

        private Header() {
            super(div().css(component(card, header), component(title), md.modifier()).get());
        }

        @Override
        public Header that() {
            return this;
        }
    }

    public static Body body() {
        return new Body();
    }

    public static class Body extends ElementBuilder<HTMLElement, Body>
            implements HtmlContent<HTMLElement, Body> {

        private Body() {
            super(div().css(component(card, body)).get());
        }

        @Override
        public Body that() {
            return this;
        }
    }

    public static Footer footer() {
        return new Footer();
    }

    public static class Footer extends ElementBuilder<HTMLElement, Footer>
            implements HtmlContent<HTMLElement, Footer> {

        private Footer() {
            super(div().css(component(card, footer)).get());
        }

        @Override
        public Footer that() {
            return this;
        }
    }
}
