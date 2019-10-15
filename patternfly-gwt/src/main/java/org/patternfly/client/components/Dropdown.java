package org.patternfly.client.components;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLLIElement;
import elemental2.dom.HTMLLabelElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.jboss.gwt.elemento.core.builder.TextContent;
import org.patternfly.client.core.Callback;
import org.patternfly.client.core.Disable;
import org.patternfly.client.resources.Constants;

import static jsinterop.base.Js.cast;
import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.input;
import static org.jboss.gwt.elemento.core.Elements.label;
import static org.jboss.gwt.elemento.core.Elements.section;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.change;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.jboss.gwt.elemento.core.InputType.checkbox;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Dataset.dropdownGroup;
import static org.patternfly.client.resources.Dataset.dropdownItem;

/**
 * PatternFly dropdown component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/dropdown">https://www.patternfly.org/v4/documentation/core/components/dropdown</a>
 */
public class Dropdown implements Disable<Dropdown>, IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    public static Dropdown text(String label) {
        return new Dropdown(label, null, false, false, false);
    }

    public static Dropdown text(String label, boolean grouped) {
        return new Dropdown(label, null, false, grouped, false);
    }

    public static Dropdown kebab() {
        return new Dropdown(null, fas("ellipsis-v"), false, false, false);
    }

    public static Dropdown kebab(boolean grouped) {
        return new Dropdown(null, fas("ellipsis-v"), false, grouped, false);
    }

    public static Dropdown icon(String icon) {
        return new Dropdown(null, icon, false, false, false);
    }

    public static Dropdown icon(String icon, boolean grouped) {
        return new Dropdown(null, icon, false, grouped, false);
    }

    public static Dropdown split() {
        return new Dropdown(null, null, false, false, true);
    }

    public static Dropdown split(boolean grouped) {
        return new Dropdown(null, null, false, grouped, true);
    }

    public static Dropdown split(String label) {
        return new Dropdown(label, null, false, false, true);
    }

    public static Dropdown split(String label, boolean grouped) {
        return new Dropdown(label, null, false, grouped, true);
    }

    public static Dropdown custom() {
        return new Dropdown(null, null, true, false, true);
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
    private final CollapseExpandBlock<Dropdown> ceb;
    private Consumer<Boolean> onChange;
    private BiConsumer<HtmlContentBuilder<HTMLButtonElement>, String> display;

    private Dropdown(String text, String icon, boolean custom, boolean grouped, boolean split) {
        this.custom = custom;
        this.grouped = grouped;
        this.split = split;
        this.ceb = new CollapseExpandBlock<>(this);
        this.display = TextContent::textContent; // button.textContent(item);

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
                        .add(i().css(fas(caretDown), component(dropdown, Constants.toggle, icon)).aria(hidden, true_))
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

    public Dropdown add(String item, Callback callback) {
        menu.appendChild(newItem(item, callback));
        return this;
    }

    public Dropdown add(String groupId, String item, Callback callback) {
        return add(groupId, null, item, callback);
    }

    public Dropdown add(String groupId, String header, String item, Callback callback) {
        Element ul = menu.querySelector("section[data-dropdown-group=" + groupId + "] > ul");
        if (ul == null) {
            HtmlContentBuilder<HTMLElement> builder = section().css(component(dropdown, Constants.group))
                    .data(dropdownGroup, groupId);
            if (header != null) {
                builder.add(h(1, header).css(component(dropdown, group, title)).aria(hidden, true_));
            }
            builder.add(ul = ul().attr(role, none).get());
            menu.appendChild(builder.get());
        }
        ul.appendChild(newItem(item, callback));
        return this;
    }

    public Dropdown add(HTMLElement element) {
        return add(element, null);
    }

    public Dropdown add(HTMLElement element, Callback callback) {
        if (custom) {
            menu.appendChild(element);
            if (callback != null) {
                bind(element, click, e -> callback.call());
            }
        }
        return this;
    }

    public Dropdown addSeparator() {
        if (!custom && !grouped) {
            menu.appendChild(li().css(component(divider)).attr(role, separator).get());
        }
        return this;
    }

    public Dropdown up() {
        root.classList.add(modifier(top));
        return this;
    }

    public Dropdown right() {
        menu.classList.add(modifier(alignRight));
        return this;
    }

    public Dropdown primary() {
        if (!split) {
            button.classList.add(modifier(primary));
        }
        return this;
    }

    public Dropdown display(BiConsumer<HtmlContentBuilder<HTMLButtonElement>, String> display) {
        this.display = display;
        return this;
    }

    @Override
    public Dropdown disable() {
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
    public Dropdown enable() {
        button.disabled = false;
        if (split) {
            toggle.classList.remove(modifier(disabled));
            if (input != null) {
                input.disabled = false;
            }
        }
        return this;
    }

    public void disable(String item) {
        HTMLButtonElement button = itemButton(item);
        if (button != null) {
            button.disabled = true;
        }
    }

    public void enable(String item) {
        HTMLButtonElement button = itemButton(item);
        if (button != null) {
            button.disabled = false;
        }
    }


    // ------------------------------------------------------ events

    public Dropdown onToggle(BiConsumer<Dropdown, Boolean> onToggle) {
        ceb.onToggle = onToggle;
        return this;
    }

    public Dropdown onChange(Consumer<Boolean> onChange) {
        this.onChange = onChange;
        return this;
    }


    // ------------------------------------------------------ internals

    private HTMLLIElement newItem(String item, Callback callback) {
        HtmlContentBuilder<HTMLButtonElement> button = button().css(component(dropdown, Constants.menu, Constants.item))
                .attr(tabindex, _1)
                .data(dropdownItem, itemId(item))
                .on(click, e -> {
                    ceb.collapse(element(), buttonElement(), menuElement());
                    callback.call();
                });
        display.accept(button, item);
        return li().attr(role, menuitem)
                .add(button)
                .get();
    }

    private HTMLButtonElement itemButton(String item) {
        return cast(menu.querySelector("button[data-dropdown-item=" + itemId(item) + "]"));
    }

    private String itemId(String item) {
        return Elements.buildId(item);
    }
}
