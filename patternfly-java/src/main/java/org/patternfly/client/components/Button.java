package org.patternfly.client.components;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.ClickHandler;
import org.patternfly.client.core.Disable;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.i;
import static org.jboss.gwt.elemento.core.Elements.span;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;

/**
 * PatternFly button component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/button">https://www.patternfly.org/v4/documentation/core/components/button</a>
 */
public class Button implements Disable<Button>, IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    public static Button button(String text) {
        return new Button(Elements.button().css(modifier(primary)).textContent(text));
    }

    public static Button button(HTMLElement element) {
        return new Button(Elements.button().css(modifier(primary)).add(element));
    }

    public static Button link(String text) {
        return new Button(Elements.button().css(modifier(link)).textContent(text));
    }

    public static Button link(HTMLElement element) {
        return new Button(Elements.button().css(modifier(link)).add(element));
    }

    public static Button link(String text, String href) {
        return new Button(Elements.a(href).textContent(text));
    }

    public static Button link(String text, String href, String target) {
        return new Button(Elements.a(href).attr("target", target).textContent(text));
    }

    public static Button link(HTMLElement element, String href) {
        return new Button(Elements.a(href).add(element));
    }

    public static Button link(HTMLElement element, String href, String target) {
        return new Button(Elements.a(href).attr("target", target).add(element));
    }

    public static Button icon(String icon, String label) {
        return new Button(Elements.button().css(modifier(plain)).aria(Constants.label, label)
                .add(i().css(icon)));
    }

    public static Button icon(HTMLElement icon, String label) {
        return new Button(Elements.button().css(modifier(plain)).aria(Constants.label, label)
                .add(icon));
    }

    public static Button inline(String text) {
        return new Button(Elements.button().css(modifier(inline), modifier(link)).textContent(text));
    }

    public static Button control(String text) {
        return new Button(Elements.button().css(modifier(control)).textContent(text));
    }

    public static Button control(HTMLElement element) {
        return new Button(Elements.button().css(modifier(control)).add(element));
    }

    public static Button control(String icon, String label) {
        return new Button(Elements.button().css(modifier(control)).aria(Constants.label, label)
                .add(i().css(icon)));
    }

    public static Button control(HTMLElement icon, String label) {
        return new Button(Elements.button().css(modifier(control)).aria(Constants.label, label)
                .add(icon));
    }


    // ------------------------------------------------------ button instance

    private final HTMLElement root;
    private final HTMLButtonElement button;
    private final HTMLAnchorElement a;
    private ClickHandler clickHandler;

    @SuppressWarnings("unchecked")
    private Button(HtmlContentBuilder builder) {
        root = builder.css(component(Constants.button))
                .on(click, e -> {
                    if (clickHandler != null) {
                        clickHandler.onClick(this);
                    }
                })
                .get();
        if (root.tagName.equalsIgnoreCase("button")) {
            a = null;
            button = (HTMLButtonElement) root;
        } else {
            a = (HTMLAnchorElement) root;
            button = null;
        }
    }

    @Override
    public HTMLElement element() {
        return root;
    }


    public HTMLButtonElement buttonElement() {
        return button;
    }

    public HTMLAnchorElement linkElement() {
        return a;
    }


    // ------------------------------------------------------ public API

    public Button prepend(String icon) {
        return prepend(i().css(icon).get());
    }

    public Button prepend(HTMLElement icon) {
        HTMLElement element = span().css(component(Constants.button, Constants.icon)).add(icon).get();
        root.insertBefore(element, root.firstChild);
        return this;
    }

    public Button type(Type type) {
        if (button != null) {
            button.setAttribute("type", type.attributeValue);
        }
        return this;
    }

    @Override
    public Button disable() {
        if (button != null) {
            button.disabled = true;
        } else if (a != null) {
            a.classList.add(disabled);
            a.setAttribute(tabindex, _1);
        }
        return this;
    }

    @Override
    public Button enable() {
        if (button != null) {
            button.disabled = false;
        } else if (a != null) {
            a.classList.remove(disabled);
            a.removeAttribute(tabindex);
        }
        return this;
    }


    // ------------------------------------------------------ modifiers

    public Button active() {
        root.classList.add(modifier(active));
        return this;
    }

    public Button block() {
        root.classList.add(modifier(block));
        return this;
    }

    public Button danger() {
        root.classList.add(modifier(danger));
        return this;
    }

    public Button expanded() {
        root.classList.add(modifier(expanded));
        return this;
    }

    public Button focus() {
        root.classList.add(modifier(focus));
        return this;
    }

    public Button primary() {
        root.classList.add(modifier(primary));
        return this;
    }

    public Button secondary() {
        root.classList.add(modifier(secondary));
        return this;
    }

    public Button tertiary() {
        root.classList.add(modifier(tertiary));
        return this;
    }

    /** Removes modifiers added by @{@link #active()}, @{@link #expanded()} or @{@link #focus()} */
    public Button clear() {
        root.classList.remove(modifier(active));
        root.classList.remove(modifier(focus));
        root.classList.remove(modifier(expanded));
        return this;
    }


    // ------------------------------------------------------ event handler

    public Button onClick(ClickHandler<Button> clickHandler) {
        this.clickHandler = clickHandler;
        return this;
    }


    // ------------------------------------------------------ inner classes


    public enum Type {
        SUBMIT("submit"), RESET("reset"), DEFAULT("default");

        private final String attributeValue;

        Type(String attributeValue) {
            this.attributeValue = attributeValue;
        }

        String attributeValue() {
            return attributeValue;
        }
    }
}
