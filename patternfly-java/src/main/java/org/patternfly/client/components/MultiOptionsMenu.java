package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.toggle;
import static org.patternfly.client.resources.Dataset.multiOptionsMenuItem;
import static org.patternfly.client.resources.Dataset.singleOptionsMenuCheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Disable;
import org.patternfly.client.core.HasValue;
import org.patternfly.client.core.SelectHandler;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;

/**
 * PatternFly options menu component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/optionsmenu">https://www.patternfly.org/v4/documentation/core/components/optionsmenu</a>
 */
public class MultiOptionsMenu extends ElementBuilder<HTMLDivElement, MultiOptionsMenu>
        implements HtmlContent<HTMLDivElement, MultiOptionsMenu>, Disable<MultiOptionsMenu> {

    // ------------------------------------------------------ factory methods

    public static MultiOptionsMenu text(String text) {
        return new MultiOptionsMenu(text, null, false);
    }

    public static MultiOptionsMenu icon(String icon) {
        return new MultiOptionsMenu(null, icon, false);
    }

    public static MultiOptionsMenu plain(String text) {
        return new MultiOptionsMenu(text, null, true);
    }

    // ------------------------------------------------------ options menu instance

    private final CollapseExpandHandler ceh;
    private final Map<String, Group<?>> groups;

    private final HTMLButtonElement button;
    private final HTMLElement plain;
    private final HTMLElement menu;

    private MultiOptionsMenu(String text, String icon, boolean plain) {
        super(div().css(component(optionsMenu)).get());
        this.ceh = new CollapseExpandHandler();
        this.groups = new HashMap<>();

        String buttonId = uniqueId(optionsMenu, Constants.button);
        HtmlContentBuilder<HTMLButtonElement> buttonBuilder = button()
                .id(buttonId)
                .aria(expanded, false_)
                .aria(hasPopup, listbox)
                .on(click, e -> ceh.expand(get(), buttonElement(), menuElement()));

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

        add(trigger);
        add(menu = ul().css(component(optionsMenu, Constants.menu))
                .aria(labelledBy, buttonId)
                .attr(hidden, "")
                .attr(role, Constants.menu)
                .get());
    }

    @Override
    public MultiOptionsMenu that() {
        return this;
    }

    private HTMLElement buttonElement() {
        return button;
    }

    private HTMLElement menuElement() {
        return menu;
    }

    // ------------------------------------------------------ public API

    public <T> MultiOptionsMenu add(Group<T> group) {
        if (menu.childNodes.length != 0) {
            menu.appendChild(li().css(component(optionsMenu, separator)).attr(role, separator).get());
        }
        HTMLUListElement ul;
        menu.appendChild(li().css(component(divider))
                .aria(label, group.text)
                .add(ul = ul().get())
                .get());

        for (T item : group.items) {
            HtmlContentBuilder<HTMLButtonElement> button = button()
                    .css(component(optionsMenu, Constants.menu, Constants.item))
                    .attr(Constants.tabindex, _1)
                    .data(multiOptionsMenuItem, group.itemId(item))
                    .on(click, e -> {
                        ceh.collapse(get(), buttonElement(), menuElement());
                        if (group.onSelect != null) {
                            group.onSelect.onSelect(item);
                        }
                    });
            group.display.accept(button, item);
            HTMLElement icon;
            button.add(icon = i().css(
                    fas(check), component(optionsMenu, Constants.menu, Constants.item, Constants.icon))
                    .aria(hidden, true_)
                    .data(singleOptionsMenuCheck, group.itemId(item))
                    .get());
            setVisible(icon, false);

            ul.appendChild(li().attr(role, menuitem)
                    .add(button)
                    .get());
        }
        return this;
    }

    public MultiOptionsMenu up() {
        element.classList.add(modifier(top));
        return this;
    }

    public MultiOptionsMenu right() {
        menu.classList.add(modifier(alignRight));
        return this;
    }

    @Override
    public MultiOptionsMenu disable() {
        button.disabled = true;
        if (plain != null) {
            plain.classList.add(modifier(disabled));
        }
        return this;
    }

    @Override
    public MultiOptionsMenu enable() {
        button.disabled = false;
        if (plain != null) {
            plain.classList.remove(modifier(disabled));
        }
        return this;
    }

    // ------------------------------------------------------ inner classes

    public static class Group<T> implements HasValue<T> {

        private final String id;
        private final String text;
        private final List<T> items;
        private final HTMLElement groupElement;

        private T value;
        private SelectHandler<T> onSelect;
        private Function<T, String> asString;
        private BiConsumer<HtmlContentBuilder<HTMLButtonElement>, T> display;

        public Group(String id, String text) {
            this.id = id;
            this.text = text;
            this.items = new ArrayList<>();
            this.groupElement = ul().get();
        }

        public Group<T> add(Iterable<T> items) {
            for (T item : items) {
                add(item);
            }
            return this;
        }

        public Group<T> add(T item) {
            items.add(item);
            return this;
        }

        @Override
        public T value() {
            return value;
        }

        public Group<T> asString(Function<T, String> asString) {
            this.asString = asString;
            return this;
        }

        public Group<T> display(BiConsumer<HtmlContentBuilder<HTMLButtonElement>, T> display) {
            this.display = display;
            return this;
        }

        public Group<T> onSelect(SelectHandler<T> onSelect) {
            this.onSelect = onSelect;
            return this;
        }

        public Group<T> select(T item) {
            value = item;
            String itemId = itemId(item);
            stream(groupElement.querySelectorAll(
                    "." + component(optionsMenu, Constants.menu, Constants.item, icon)))
                            .filter(htmlElements())
                            .map(asHtmlElement())
                            .forEach(e -> setVisible(e, itemId.equals(e.dataset.get(singleOptionsMenuCheck))));

            if (onSelect != null) {
                onSelect.onSelect(value);
            }
            return this;
        }

        private String itemId(T item) {
            return Elements.buildId(asString.apply(item));
        }
    }
}
