package org.patternfly.client.components;

import java.util.function.Consumer;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.i;
import static org.jboss.gwt.elemento.core.Elements.insertAfter;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.emptyState;
import static org.patternfly.client.resources.Constants.hidden;
import static org.patternfly.client.resources.Constants.true_;

/**
 * PatternFly empty state component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/emptystate">https://www.patternfly.org/v4/documentation/core/components/emptystate</a>
 */
public class EmptyState implements IsElement<HTMLElement> {

    private final HTMLElement root;
    private final HTMLElement body;
    private HTMLElement secondary;

    public EmptyState(String title, String icon, String body) {
        this(title, icon, b -> b.textContent = body);
    }

    public EmptyState(String title, String icon, HTMLElement body) {
        this(title, icon, b -> b.appendChild(body));
    }

    private EmptyState(String title, String icon, Consumer<HTMLElement> consumer) {
        this.root = div().css(component(emptyState))
                .add(i().css(component(emptyState, Constants.icon), icon)
                        .aria(hidden, true_))
                .add(h(1, title).css(component(Constants.title), modifier(Constants.lg)))
                .add(body = div().css(component(emptyState, Constants.body)).get())
                .get();
        consumer.accept(body);
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    public EmptyState primary(Button primaryButton) {
        insertAfter(primaryButton.element(), body);
        return this;
    }

    public EmptyState primary(HTMLElement element) {
        insertAfter(element, body);
        return this;
    }

    public EmptyState secondary(Button button) {
        if (secondary == null) {
            secondary = div().css(component(emptyState, Constants.secondary))
                    .get();
            root.appendChild(secondary);
        }
        secondary.appendChild(button.element());
        return this;
    }
}
