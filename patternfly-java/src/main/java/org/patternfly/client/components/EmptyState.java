package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.i;
import static org.jboss.gwt.elemento.core.Elements.insertAfter;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.card;
import static org.patternfly.client.resources.Constants.emptyState;
import static org.patternfly.client.resources.Constants.hidden;
import static org.patternfly.client.resources.Constants.true_;

/**
 * PatternFly empty state component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/emptystate">https://www.patternfly.org/v4/documentation/core/components/emptystate</a>
 */
public class EmptyState extends ElementBuilder<HTMLDivElement, EmptyState>
        implements HtmlContent<HTMLDivElement, EmptyState> {

    // ------------------------------------------------------ factory methods

    public static EmptyState emptyState(String icon, String title) {
        return new EmptyState(icon, title);
    }

    public static EmptyState loading() {
        // TODO Implement me!
        return new EmptyState("", "NYI");
    }

    public static EmptyState noResults() {
        // TODO Implement me!
        return new EmptyState("", "NYI");
    }


    // ------------------------------------------------------ empty state instance

    private HTMLElement body;
    private HTMLElement secondary;

    public EmptyState(String icon, String title) {
        super(div().css(component(emptyState)).get());
        add(i().css(component(emptyState, Constants.icon), icon).aria(hidden, true_));
        add(h(1, title).css(component(Constants.title), modifier(Constants.lg)));
    }

    @Override
    public EmptyState that() {
        return this;
    }


    // ------------------------------------------------------ pubic API

    public EmptyState body(String body) {
        return body(body().textContent(body));
    }

    public EmptyState body(Body body) {
        return add(this.body = body.get());
    }

    public EmptyState primary(Button button) {
        return primary(button.get());
    }

    public EmptyState primary(HTMLElement element) {
        if (body == null) {
            add(element);
        } else {
            insertAfter(element, body);
        }
        return this;
    }

    public EmptyState secondary(Button button) {
        if (secondary == null) {
            add(secondary = div().css(component(emptyState, Constants.secondary)).get());
        }
        secondary.appendChild(button.get());
        return this;
    }


    // ------------------------------------------------------ inner classes

    public static Body body() {
        return new Body();
    }

    public static class Body extends ElementBuilder<HTMLElement, Body>
            implements HtmlContent<HTMLElement, Body> {

        private Body() {
            super(div().css(component(card, Constants.body)).get());
        }

        @Override
        public Body that() {
            return this;
        }
    }
}
