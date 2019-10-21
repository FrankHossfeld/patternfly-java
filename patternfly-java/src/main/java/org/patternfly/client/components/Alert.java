package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Constants.label;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.core.Callback;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

/**
 * PatternFly alert component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/alert">https://www.patternfly.org/v4/documentation/core/components/alert</a>
 */
public class Alert extends ElementBuilder<HTMLDivElement, Alert>
        implements HtmlContent<HTMLDivElement, Alert> {

    // ------------------------------------------------------ factory methods

    public static Alert default_(String title) {
        return new Alert(Type.DEFAULT, title);
    }

    public static Alert info(String title) {
        return new Alert(Type.INFO, title);
    }

    public static Alert success(String title) {
        return new Alert(Type.SUCCESS, title);
    }

    public static Alert warning(String title) {
        return new Alert(Type.WARNING, title);
    }

    public static Alert danger(String title) {
        return new Alert(Type.DANGER, title);
    }

    // ------------------------------------------------------ alert instance

    private final Type type;
    private final String title;
    private Callback callback;

    private final HTMLElement titleElement;

    private Alert(Type type, String title) {
        super(div().css(component(alert)).aria(label, type.aria).get());
        this.type = type;
        this.title = title;

        if (type.modifier != null) {
            element.classList.add(type.modifier);
        }
        add(div().css(component(alert, icon)).add(i().css(type.icon).aria(hidden, true_)))
                .add(this.titleElement = h(4).css(component(alert, Constants.title))
                        .add(span().css("pf-screen-reader").textContent(type.aria))
                        .add(title)
                        .get());
    }

    @Override
    public Alert that() {
        return this;
    }

    // ------------------------------------------------------ public API

    public Alert inline() {
        return css(modifier(inline));
    }

    public Alert description(String description) {
        return add(description().textContent(description));
    }

    public Alert closable() {
        String label = "close " + type.aria + ": " + title;
        return action(Button.icon(fas("times"), label).get(), this::close);
    }

    public void close() {
        failSafeRemoveFromParent(element);
        if (callback != null) {
            callback.call();
        }
    }

    public Alert action(String action, Callback callback) {
        return action(Button.link(action).get(), callback);
    }

    public Alert action(HTMLElement action, Callback callback) {
        bind(action, click, e -> callback.call());
        return add(div().css(component(alert, Constants.action))
                .add(action));
    }

    // ------------------------------------------------------ events

    public Alert onClose(Callback callback) {
        this.callback = callback;
        return this;
    }

    // ------------------------------------------------------ internals

    boolean hasClose() {
        String selector = "." + component(alert, action) + " ." + fas("times");
        return element.querySelector(selector) != null;
    }

    // ------------------------------------------------------ inner classes

    enum Type {
        DEFAULT(fas("bell"), null, "default alert"),
        INFO(fas("info-circle"), modifier(info), "info alert"),
        SUCCESS(fas("check-circle"), modifier(success), "success alert"),
        WARNING(fas("exclamation-triangle"), modifier(warning), "warning alert"),
        DANGER(fas("exclamation-circle"), modifier(danger), "danger alert");

        private final String icon;
        private final String modifier;
        private final String aria;

        Type(String icon, String modifier, String aria) {
            this.icon = icon;
            this.modifier = modifier;
            this.aria = aria;
        }
    }

    public static Description description() {
        return new Description();
    }

    public static class Description extends ElementBuilder<HTMLElement, Description>
            implements HtmlContent<HTMLElement, Description> {

        private Description() {
            super(div().css(component(alert, description)).get());
        }

        @Override
        public Description that() {
            return this;
        }
    }
}
