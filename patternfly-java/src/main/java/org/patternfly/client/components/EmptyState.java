package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.core.Callback;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.i;
import static org.jboss.gwt.elemento.core.Elements.insertAfter;
import static org.jboss.gwt.elemento.core.Elements.insertFirst;
import static org.patternfly.client.components.Button.button;
import static org.patternfly.client.resources.CSS.Size.lg;
import static org.patternfly.client.resources.CSS.Size.sm;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.Constants.*;

/**
 * PatternFly empty state component.
 *
 * @see <a
 *      href=https://www.patternfly.org/v4/documentation/core/components/emptystate">https://www.patternfly.org/v4/documentation/core/components/emptystate</a>
 */
public class EmptyState extends ElementBuilder<HTMLDivElement, EmptyState>
        implements HtmlContent<HTMLDivElement, EmptyState> {

    // ------------------------------------------------------ factory methods

    public static EmptyState emptyState(String icon, String title) {
        return new EmptyState(icon, title);
    }

    public static EmptyState spinner() {
        return spinner("Loading");
    }

    public static EmptyState spinner(String title) {
        EmptyState loading = new EmptyState(null, title).large();
        insertFirst(loading.get(),
                div().css(component(emptyState, icon))
                        .add(Components.spinner())
                        .get());
        return loading;
    }

    public static EmptyState noResults(Callback callback) {
        return noResults("No results found", callback);
    }

    public static EmptyState noResults(String title, Callback callback) {
        return new EmptyState(fas("search"), title).large()
                .body("No results match the filter criteria. Remove all filters or clear all filters to show results.")
                .primary(Button.link("Clear all filters").onClick(callback));
    }

    // ------------------------------------------------------ empty state instance

    private HTMLElement body;
    private HTMLElement primaryContainer;
    private HTMLElement secondaryContainer;

    private EmptyState(String icon, String title) {
        super(div().css(component(emptyState)).get());
        if (icon != null) {
            add(i().css(component(emptyState, Constants.icon), icon).aria(hidden, true_));
        }
        add(Components.title(1, title, lg));
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

    public EmptyState primary(String text, Callback callback) {
        return primary(button(text).primary().onClick(callback).get());
    }

    public EmptyState primary(Button button) {
        return primary(button.get());
    }

    public EmptyState primary(HTMLElement element) {
        if (primaryContainer == null) {
            primaryContainer = div().css(component(emptyState, primary)).get();
            if (body == null) {
                add(primaryContainer);
            } else {
                insertAfter(primaryContainer, body);
            }
        }
        primaryContainer.appendChild(element);
        return this;
    }

    public EmptyState secondary(String text, Callback callback) {
        return secondary(Button.link(text).onClick(callback).get());
    }

    public EmptyState secondary(Button button) {
        return secondary(button.get());
    }

    public EmptyState secondary(HTMLElement element) {
        if (secondaryContainer == null) {
            add(secondaryContainer = div().css(component(emptyState, Constants.secondary)).get());
        }
        secondaryContainer.appendChild(element);
        return this;
    }

    public EmptyState small() {
        return css(sm.modifier());
    }

    public EmptyState large() {
        return css(lg.modifier());
    }

    // ------------------------------------------------------ inner classes

    public static Body body() {
        return new Body();
    }

    public static class Body extends ElementBuilder<HTMLElement, Body>
            implements HtmlContent<HTMLElement, Body> {

        private Body() {
            super(div().css(component(emptyState, Constants.body)).get());
        }

        @Override
        public Body that() {
            return this;
        }
    }
}
