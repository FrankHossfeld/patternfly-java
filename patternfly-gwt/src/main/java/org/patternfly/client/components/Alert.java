package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Callback;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;

/**
 * PatternFly alert component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/alert">https://www.patternfly.org/v4/documentation/core/components/alert</a>
 */
public class Alert implements IsElement<HTMLElement> {

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
    private Callback onClose;

    private final HTMLElement titleElement;
    private final HTMLElement root;

    private Alert(Type type, String title) {
        this.type = type;
        this.title = title;

        HtmlContentBuilder<HTMLDivElement> builder = div().css(component(alert));
        if (type.modifier != null) {
            builder.css(type.modifier);
        }
        builder.add(div().css(component(alert, icon)).add(i().css(type.icon)).aria(hidden, true_))
                .add(this.titleElement = h(4).css(component(alert, Constants.title))
                        .add(span().css("pf-screen-reader").textContent(type.aria))
                        .add(title)
                        .get());
        this.root = builder.get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }


    // ------------------------------------------------------ public API

    public Alert inline() {
        root.classList.add(modifier(inline));
        return this;
    }

    public Alert description(String description) {
        return description(p().textContent(description).get());
    }

    public Alert description(HTMLElement description) {
        HTMLElement element = (HTMLElement) root.querySelector("." + component(alert, Constants.description));
        if (element == null) {
            element = div().css(component(alert, Constants.description)).get();
            insertAfter(element, titleElement);
        }
        removeChildrenFrom(element);
        element.appendChild(description);
        return this;
    }

    public Alert closable() {
        String label = "close " + type.aria + title;
        return action(Button.icon(fas("times"), label).element(), this::close);
    }

    public void close() {
        failSafeRemoveFromParent(root);
        if (onClose != null) {
            onClose.call();
        }
    }

    public Alert action(String action, Callback callback) {
        return action(Button.link(action).element(), callback);
    }

    public Alert action(HTMLElement action, Callback callback) {
        HTMLElement element = (HTMLElement) root.querySelector("." + component(alert, Constants.action));
        if (element == null) {
            element = div().css(component(alert, Constants.action)).get();
            root.appendChild(element);
        }
        removeChildrenFrom(element);
        element.appendChild(action);
        bind(action, click, e -> callback.call());
        return this;
    }


    // ------------------------------------------------------ events

    public Alert onClose(Callback onClose) {
        this.onClose = onClose;
        return this;
    }

    // ------------------------------------------------------ inner classes


    enum Type {
        DEFAULT(fas("bell"), null, "default alert:"),
        INFO(fas("info-circle"), modifier(info), "info alert:"),
        SUCCESS(fas("check-circle"), modifier(success), "success alert:"),
        WARNING(fas("exclamation-triangle"), modifier(warning), "sarning alert:"),
        DANGER(fas("exclamation-circle"), modifier(danger), "sanger alert:");

        private final String icon;
        private final String modifier;
        private final String aria;

        Type(String icon, String modifier, String aria) {
            this.icon = icon;
            this.modifier = modifier;
            this.aria = aria;
        }
    }
}
