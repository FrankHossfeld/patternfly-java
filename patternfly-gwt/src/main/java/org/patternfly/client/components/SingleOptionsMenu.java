package org.patternfly.client.components;

import java.util.function.BiConsumer;
import java.util.function.Function;

import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Disable;
import org.patternfly.client.core.HasValue;
import org.patternfly.client.core.SelectHandler;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.toggle;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Dataset.singleOptionsMenuCheck;
import static org.patternfly.client.resources.Dataset.singleOptionsMenuItem;

/**
 * PatternFly options menu component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/optionsmenu">https://www.patternfly.org/v4/documentation/core/components/optionsmenu</a>
 */
public class SingleOptionsMenu<T> implements HasValue<T>, Disable<SingleOptionsMenu<T>>, IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    public static <T> SingleOptionsMenu<T> text(String text) {
        return new SingleOptionsMenu<>(text, null, false);
    }

    public static <T> SingleOptionsMenu<T> icon(String icon) {
        return new SingleOptionsMenu<>(null, icon, false);
    }

    public static <T> SingleOptionsMenu<T> plain(String text) {
        return new SingleOptionsMenu<>(text, null, true);
    }


    // ------------------------------------------------------ options menu instance

    private final HTMLElement root;
    private final HTMLButtonElement button;
    private final HTMLElement plain;
    private final HTMLElement menu;

    private final CollapseExpandBlock<SingleOptionsMenu<T>> ceb;
    private final ItemVisualizationBlock<HTMLButtonElement, T> ivb;
    private T value;
    private SelectHandler<T> onSelect;

    private SingleOptionsMenu(String text, String icon, boolean plain) {
        this.ceb = new CollapseExpandBlock<>(this);
        this.ivb = new ItemVisualizationBlock<>();

        String buttonId = Elements.uniqueId(optionsMenu, Constants.button);
        HtmlContentBuilder<HTMLButtonElement> buttonBuilder = button()
                .id(buttonId)
                .aria(expanded, false_)
                .aria(hasPopup, listbox)
                .on(click, e -> ceb.expand(element(), buttonElement(), menuElement()));

        HTMLElement trigger;
        if (icon != null) {
            this.plain = null;
            this.button = buttonBuilder.css(component(optionsMenu, toggle), modifier(Constants.plain))
                    .add(span().css(component(optionsMenu, toggle, Constants.text)).textContent(text))
                    .add(i().css(fas(caretDown), component(optionsMenu, toggle, Constants.icon))
                            .aria(hidden, true_))
                    .get();
            trigger = button;

        } else { // text != null
            if (plain) {
                this.plain = div().css(component(optionsMenu, toggle), modifier(Constants.plain),
                        modifier(Constants.text))
                        .add(span().css(component(optionsMenu, toggle, Constants.text))
                                .textContent(text))
                        .add(button = buttonBuilder.css(component(optionsMenu, toggle, Constants.button))
                                .aria(label, text)
                                .add(i().css(fas(caretDown)).aria(hidden, true_))
                                .get())
                        .get();
                trigger = this.plain;

            } else {
                this.plain = null;
                this.button = buttonBuilder.css(component(optionsMenu, toggle))
                        .aria(label, text)
                        .add(span().css(component(optionsMenu, toggle, Constants.text)).textContent(text))
                        .add(i().css(fas(caretDown), component(optionsMenu, toggle, Constants.icon))
                                .aria(hidden, true_))
                        .get();
                trigger = button;
            }
        }

        root = div().css(component(optionsMenu))
                .add(trigger)
                .add(menu = ul().css(component(optionsMenu, Constants.menu))
                        .aria(Constants.labelledBy, buttonId)
                        .attr(hidden, "")
                        .attr(role, Constants.menu)
                        .get())
                .get();
    }

    private HTMLElement buttonElement() {
        return button;
    }

    private HTMLElement menuElement() {
        return menu;
    }

    @Override
    public HTMLElement element() {
        return root;
    }


    // ------------------------------------------------------ public API

    public SingleOptionsMenu<T> add(Iterable<T> items) {
        for (T item : items) {
            add(item);
        }
        return this;
    }

    public SingleOptionsMenu<T> add(T[] items) {
        for (T item : items) {
            add(item);
        }
        return this;
    }

    public SingleOptionsMenu<T> add(T item) {
        String itemId = ivb.itemId(item);
        HtmlContentBuilder<HTMLButtonElement> button = button()
                .css(component(optionsMenu, Constants.menu, Constants.item))
                .attr(tabindex, _1)
                .data(singleOptionsMenuItem, itemId)
                .on(click, e -> {
                    ceb.collapse(element(), buttonElement(), menuElement());
                    select(item);
                });
        ivb.display.accept(button, item);
        HTMLElement icon;
        button.add(icon = i().css(
                fas(check), component(optionsMenu, Constants.menu, Constants.item, Constants.icon))
                .aria(hidden, true_)
                .data(singleOptionsMenuCheck, itemId)
                .get());
        setVisible(icon, false);

        menu.appendChild(li().attr(role, menuitem)
                .add(button)
                .get());
        return this;
    }

    public SingleOptionsMenu<T> asString(Function<T, String> asString) {
        ivb.asString = asString;
        return this;
    }

    public SingleOptionsMenu<T> display(BiConsumer<HtmlContentBuilder<HTMLButtonElement>, T> display) {
        ivb.display = display;
        return this;
    }

    public SingleOptionsMenu<T> select(T item) {
        value = item;
        String itemId = ivb.itemId(item);
        stream(menu.querySelectorAll("." + component(optionsMenu, Constants.menu, Constants.item, icon)))
                .filter(htmlElements())
                .map(asHtmlElement())
                .forEach(e -> setVisible(e, itemId.equals(e.dataset.get(singleOptionsMenuCheck))));

        if (onSelect != null) {
            onSelect.onSelect(value);
        }
        return this;
    }

    public SingleOptionsMenu<T> up() {
        root.classList.add(modifier(top));
        return this;
    }

    public SingleOptionsMenu<T> right() {
        menu.classList.add(modifier(alignRight));
        return this;
    }

    @Override
    public SingleOptionsMenu<T> disable() {
        button.disabled = true;
        if (plain != null) {
            plain.classList.add(modifier(Constants.disabled));
        }
        return this;
    }

    @Override
    public SingleOptionsMenu<T> enable() {
        button.disabled = false;
        if (plain != null) {
            plain.classList.remove(modifier(Constants.disabled));
        }
        return this;
    }

    @Override
    public T value() {
        return value;
    }


    // ------------------------------------------------------ events

    public SingleOptionsMenu<T> onToggle(BiConsumer<SingleOptionsMenu<T>, Boolean> onToggle) {
        ceb.onToggle = onToggle;
        return this;
    }

    public SingleOptionsMenu<T> onSelect(SelectHandler<T> onSelect) {
        this.onSelect = onSelect;
        return this;
    }
}
