package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.Size.md;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import elemental2.dom.HTMLDivElement;

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

    public static class Head extends ElementBuilder<HTMLDivElement, Head>
            implements HtmlContent<HTMLDivElement, Head> {

        private Head() {
            super(div().css(component(card, head)).get());
        }

        @Override
        public Head that() {
            return this;
        }

        public static Actions actions() {
            return new Actions();
        }

        public static class Actions extends ElementBuilder<HTMLDivElement, Actions>
                implements HtmlContent<HTMLDivElement, Actions> {

            private Actions() {
                super(div().css(component(card, actions)).get());
            }

            @Override
            public Actions that() {
                return this;
            }
        }
    }

    public static Header header() {
        return new Header();
    }

    public static class Header extends ElementBuilder<HTMLDivElement, Header>
            implements HtmlContent<HTMLDivElement, Header> {

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

    public static class Body extends ElementBuilder<HTMLDivElement, Body>
            implements HtmlContent<HTMLDivElement, Body> {

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

    public static class Footer extends ElementBuilder<HTMLDivElement, Footer>
            implements HtmlContent<HTMLDivElement, Footer> {

        private Footer() {
            super(div().css(component(card, footer)).get());
        }

        @Override
        public Footer that() {
            return this;
        }
    }
}
