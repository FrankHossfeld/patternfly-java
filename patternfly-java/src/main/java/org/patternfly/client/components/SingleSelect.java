package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.fieldset;
import static org.jboss.gwt.elemento.core.Elements.form;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Constants.fieldset;
import static org.patternfly.client.resources.Constants.form;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.select;
import static org.patternfly.client.resources.Constants.toggle;
import static org.patternfly.client.resources.Dataset.singleSelectItem;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Disable;
import org.patternfly.client.core.HasValue;
import org.patternfly.client.core.SelectHandler;
import org.patternfly.client.resources.CSS;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

/**
 * PatternFly single select component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/select">https://www.patternfly.org/v4/documentation/core/components/select</a>
 */
public class SingleSelect<T> extends ElementBuilder<HTMLDivElement, SingleSelect<T>>
        implements HtmlContent<HTMLDivElement, SingleSelect<T>>, Disable<SingleSelect<T>>, HasValue<T> {

    // ------------------------------------------------------ factory methods

    public static <T> SingleSelect<T> single(String placeholder) {
        return new SingleSelect<>(placeholder, null, false, false);
    }

    public static <T> SingleSelect<T> single(String placeholder, String icon) {
        return new SingleSelect<>(placeholder, icon, false, false);
    }

    public static <T> SingleSelect<T> single(boolean typeahead) {
        return new SingleSelect<>(null, null, false, typeahead);
    }

    public static <T> SingleSelect<T> single(String icon, boolean typeahead) {
        return new SingleSelect<>(null, icon, false, typeahead);
    }

    public static <T> SingleSelect<T> multiple() {
        return new SingleSelect<>(null, null, true, false);
    }

    // ------------------------------------------------------ select instance

    private final CollapseExpandHandler ceh;
    private final ItemDisplay<HTMLButtonElement, T> itemDisplay;
    private T value;
    private SelectHandler<T> onSelect;

    private final HTMLButtonElement button;
    private final HTMLElement text;
    private final HTMLElement menu;

    private SingleSelect(String placeholder, String icon, boolean multiple, boolean typeahead) {
        super(div().css(component(select)).get());
        this.ceh = new CollapseExpandHandler();
        this.itemDisplay = new ItemDisplay<>();

        String labelId = uniqueId(select, label);
        String buttonId = uniqueId(select, Constants.button);

        add(span().id(labelId).attr(hidden, "").textContent(placeholder));
        add(button = button().css(component(select, toggle))
                .id(buttonId)
                .aria(Constants.expanded, false_)
                .aria(Constants.hasPopup, listbox)
                .aria(labelledBy, labelId + " " + buttonId)
                .on(click, e -> ceh.expand(get(), buttonElement(), menuElement()))
                .add(div().css(component(select, toggle, wrapper))
                        .add(text = span().css(component(select, toggle, Constants.text))
                                .textContent(placeholder)
                                .get()))
                .add(i().css(fas(caretDown), component(select, toggle, arrow))
                        .aria(hidden, true_))
                .get());

        HTMLElement menuElement;
        if (multiple) {
            menuElement = div().css(component(select, Constants.menu))
                    .add(form().css(component(form)).attr(novalidate, "")
                            .add(div().css(component(form, group))
                                    .add(menu = fieldset().css(component(component(form, fieldset)))
                                            .aria(label, "Select input")
                                            .get())))
                    .get();
        } else {
            menuElement = ul().css(component(select, Constants.menu))
                    .aria(labelledBy, labelId)
                    .attr(hidden, "")
                    .attr(role, listbox)
                    .get();
            menu = menuElement;
        }
        add(menuElement);

        if (icon != null) {
            insertBefore(span().css(component(select, toggle, Constants.icon))
                    .add(i().css(icon).aria(hidden, true_))
                    .get(),
                    text);
        }
    }

    @Override
    public SingleSelect<T> that() {
        return this;
    }

    private HTMLElement buttonElement() {
        return button;
    }

    private HTMLElement menuElement() {
        return menu;
    }

    // ------------------------------------------------------ public API

    public SingleSelect<T> add(Iterable<T> items) {
        for (T item : items) {
            add(item);
        }
        return this;
    }

    public SingleSelect<T> add(T[] items) {
        for (T item : items) {
            add(item);
        }
        return this;
    }

    public SingleSelect<T> add(T item) {
        HtmlContentBuilder<HTMLButtonElement> button = button()
                .css(component(select, Constants.menu, Constants.item))
                .data(singleSelectItem, itemDisplay.itemId(item))
                .on(click, e -> {
                    ceh.collapse(get(), buttonElement(), menuElement());
                    select(item);
                });
        itemDisplay.display.accept(button, item);
        menu.appendChild(li().attr(role, presentation)
                .add(button)
                .get());
        return this;
    }

    public SingleSelect<T> asString(Function<T, String> asString) {
        itemDisplay.asString = asString;
        return this;
    }

    public SingleSelect<T> display(BiConsumer<HtmlContentBuilder<HTMLButtonElement>, T> display) {
        itemDisplay.display = display;
        return this;
    }

    public SingleSelect<T> select(T item) {
        value = item;
        text.textContent = itemDisplay.asString.apply(item);
        if (onSelect != null) {
            onSelect.onSelect(value);
        }
        return this;
    }

    public SingleSelect<T> up() {
        element.classList.add(CSS.modifier(Constants.top));
        return this;
    }

    @Override
    public SingleSelect<T> disable() {
        button.disabled = true;
        return this;
    }

    @Override
    public SingleSelect<T> enable() {
        button.disabled = false;
        return this;
    }

    @Override
    public T value() {
        return value;
    }

    // ------------------------------------------------------ events

    public SingleSelect<T> onToggle(Consumer<Boolean> onToggle) {
        ceh.onToggle = onToggle;
        return this;
    }

    public SingleSelect<T> onSelect(SelectHandler<T> onSelect) {
        this.onSelect = onSelect;
        return this;
    }
}
