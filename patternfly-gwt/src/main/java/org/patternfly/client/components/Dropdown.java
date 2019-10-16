package org.patternfly.client.components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLIElement;
import elemental2.dom.HTMLLabelElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Disable;
import org.patternfly.client.core.SelectHandler;
import org.patternfly.client.resources.Constants;

import static jsinterop.base.Js.cast;
import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.input;
import static org.jboss.gwt.elemento.core.Elements.label;
import static org.jboss.gwt.elemento.core.Elements.section;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.change;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.jboss.gwt.elemento.core.InputType.checkbox;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.header;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Dataset.dropdownGroup;
import static org.patternfly.client.resources.Dataset.dropdownItem;

/**
 * PatternFly dropdown component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/dropdown">https://www.patternfly.org/v4/documentation/core/components/dropdown</a>
 */
public class Dropdown<T> implements Disable<Dropdown<T>>, IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    public static <T> Dropdown<T> text(String label) {
        return new Dropdown<>(label, null, false, false, false);
    }

    public static <T> Dropdown<T> text(String label, boolean grouped) {
        return new Dropdown<>(label, null, false, grouped, false);
    }

    public static <T> Dropdown<T> kebab() {
        return new Dropdown<>(null, fas("ellipsis-v"), false, false, false);
    }

    public static <T> Dropdown<T> kebab(boolean grouped) {
        return new Dropdown<>(null, fas("ellipsis-v"), false, grouped, false);
    }

    public static <T> Dropdown<T> icon(String icon) {
        return new Dropdown<>(null, icon, false, false, false);
    }

    public static <T> Dropdown<T> icon(String icon, boolean grouped) {
        return new Dropdown<>(null, icon, false, grouped, false);
    }

    public static <T> Dropdown<T> split() {
        return new Dropdown<>(null, null, false, false, true);
    }

    public static <T> Dropdown<T> split(boolean grouped) {
        return new Dropdown<>(null, null, false, grouped, true);
    }

    public static <T> Dropdown<T> split(String label) {
        return new Dropdown<>(label, null, false, false, true);
    }

    public static <T> Dropdown<T> split(String label, boolean grouped) {
        return new Dropdown<>(label, null, false, grouped, true);
    }

    public static <T> Dropdown<T> custom() {
        return new Dropdown<>(null, null, true, false, true);
    }


    // ------------------------------------------------------ dropdown instance

    private final HTMLElement toggle;
    private final HTMLInputElement input;
    private final HTMLButtonElement button;
    private final HTMLElement menu;
    private final HTMLElement root;

    private final boolean custom;
    private final boolean grouped;
    private final boolean split;
    private final CollapseExpandBlock<Dropdown<T>> ceb;
    private final ItemVisualizationBlock<HTMLButtonElement, T> ivb;
    private Consumer<Boolean> onChange;
    private SelectHandler<T> onSelect;

    private Dropdown(String text, String icon, boolean custom, boolean grouped, boolean split) {
        this.custom = custom;
        this.grouped = grouped;
        this.split = split;
        this.ceb = new CollapseExpandBlock<>(this);
        this.ivb = new ItemVisualizationBlock<>();

        String buttonId = uniqueId(dropdown, Constants.button);
        HtmlContentBuilder<HTMLButtonElement> buttonBuilder = button()
                .id(buttonId)
                .aria(expanded, false_)
                .aria(hasPopup, true_)
                .on(click, e -> ceb.expand(element(), buttonElement(), menuElement()));

        if (split) {
            HTMLLabelElement le;
            String inputId = uniqueId(dropdown, Constants.input);
            toggle = div().css(component(dropdown, Constants.toggle), modifier(splitButton))
                    .add(le = label().css(component(dropdown, Constants.toggle, check))
                            .add(div().css(component(check))
                                    .add(input = input(checkbox).css(component(check, Constants.input))
                                            .id(inputId)
                                            .aria(invalid, false_)
                                            .aria(Constants.label, "Select All")
                                            .on(change, e -> {
                                                if (onChange != null) {
                                                    onChange.accept(((HTMLInputElement) e.target).checked);
                                                }
                                            })
                                            .get()))
                            .get())
                    .add(button = buttonBuilder.css(component(dropdown, Constants.toggle, Constants.button))
                            .aria(Constants.label, "Select")
                            .get())
                    .get();
            le.htmlFor = inputId;
            if (text != null) {
                le.appendChild(span().css(component(dropdown, Constants.toggle, Constants.text))
                        .aria(hidden, true_)
                        .textContent(text)
                        .get());
            }

        } else {
            input = null;
            if (text != null) {
                button = buttonBuilder.css(component(dropdown, Constants.toggle))
                        .add(span().css(component(dropdown, Constants.toggle, Constants.text)).textContent(text))
                        .add(i().css(fas(caretDown), component(dropdown, Constants.toggle, Constants.icon))
                                .aria(hidden, true_))
                        .get();
            } else { // icon != null
                button = buttonBuilder.css(modifier(plain)).aria(Constants.label, "Actions")
                        .add(i().css(icon).aria(hidden, true_))
                        .get();
            }
            toggle = button;
        }

        HtmlContentBuilder menuBuilder;
        if (custom || grouped) {
            menuBuilder = div();
        } else {
            menuBuilder = ul();
        }
        menu = menuBuilder.css(component(dropdown, Constants.menu))
                .aria(labelledBy, buttonId)
                .attr(hidden, "")
                .attr(role, Constants.menu)
                .get();
        root = div().css(component(dropdown))
                .add(toggle)
                .add(menu)
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

    public Dropdown<T> add(Iterable<T> items) {
        for (T item : items) {
            add(item);
        }
        return this;
    }

    public Dropdown<T> add(T item) {
        return add(item, false);
    }

    public Dropdown<T> add(T item, boolean disabled) {
        menu.appendChild(newItem(item, disabled));
        return this;
    }

    public Dropdown<T> add(String group, T item) {
        return add(group, item, false);
    }

    public Dropdown<T> add(String group, T item, boolean disabled) {
        String groupId = buildId(group);
        Element ul = menu.querySelector("section[data-dropdown-group=" + groupId + "] > ul");
        if (ul == null) {
            menu.appendChild(section().css(component(dropdown, Constants.group))
                    .data(dropdownGroup, groupId)
                    .add(h(1, header).css(component(dropdown, group, title)).aria(hidden, true_))
                    .add(ul = ul().attr(role, none).get()).get());
        }
        ul.appendChild(newItem(item, disabled));
        return this;
    }

    public Dropdown<T> addSeparator() {
        if (!custom && !grouped) {
            menu.appendChild(li().css(component(divider)).attr(role, separator).get());
        }
        return this;
    }

    public Dropdown<T> asString(Function<T, String> asString) {
        ivb.asString = asString;
        return this;
    }

    public Dropdown<T> display(BiConsumer<HtmlContentBuilder<HTMLButtonElement>, T> display) {
        ivb.display = display;
        return this;
    }

    public Dropdown<T> up() {
        root.classList.add(modifier(top));
        return this;
    }

    public Dropdown<T> right() {
        menu.classList.add(modifier(alignRight));
        return this;
    }

    public Dropdown primary() {
        if (!split) {
            button.classList.add(modifier(primary));
        }
        return this;
    }

    @Override
    public Dropdown<T> disable() {
        button.disabled = true;
        if (split) {
            toggle.classList.add(modifier(disabled));
            if (input != null) {
                input.disabled = true;
            }
        }
        return this;
    }

    @Override
    public Dropdown<T> enable() {
        button.disabled = false;
        if (split) {
            toggle.classList.remove(modifier(disabled));
            if (input != null) {
                input.disabled = false;
            }
        }
        return this;
    }

    public void disable(T item) {
        HTMLButtonElement button = itemButton(item);
        if (button != null) {
            button.disabled = true;
        }
    }

    public void enable(T item) {
        HTMLButtonElement button = itemButton(item);
        if (button != null) {
            button.disabled = false;
        }
    }


    // ------------------------------------------------------ events

    public Dropdown<T> onToggle(BiConsumer<Dropdown<T>, Boolean> onToggle) {
        ceb.onToggle = onToggle;
        return this;
    }

    public Dropdown<T> onChange(Consumer<Boolean> onChange) {
        this.onChange = onChange;
        return this;
    }

    public Dropdown<T> onSelect(SelectHandler<T> onSelect) {
        this.onSelect = onSelect;
        return this;
    }


    // ------------------------------------------------------ internals

    private HTMLLIElement newItem(T item, boolean disabled) {
        HtmlContentBuilder<HTMLButtonElement> button = button().css(component(dropdown, Constants.menu, Constants.item))
                .attr(tabindex, _1)
                .data(dropdownItem, ivb.itemId(item))
                .on(click, e -> {
                    ceb.collapse(element(), buttonElement(), menuElement());
                    if (onSelect != null) {
                        onSelect.onSelect(item);
                    }
                });
        ivb.display.accept(button, item);
        HTMLButtonElement buttonElement = button.get();
        buttonElement.disabled = disabled;
        return li().attr(role, menuitem)
                .add(buttonElement)
                .get();
    }

    private HTMLButtonElement itemButton(T item) {
        return cast(menu.querySelector("button[data-dropdown-item=" + ivb.itemId(item) + "]"));
    }
}
