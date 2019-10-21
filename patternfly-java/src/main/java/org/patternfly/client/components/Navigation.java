package org.patternfly.client.components;

import static jsinterop.base.Js.cast;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.nav;
import static org.jboss.gwt.elemento.core.Elements.section;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Constants.button;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.nav;
import static org.patternfly.client.resources.Constants.section;
import static org.patternfly.client.resources.Constants.toggle;
import static org.patternfly.client.resources.Dataset.*;

import java.util.function.BiConsumer;
import java.util.function.Function;

import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.SelectHandler;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLElement;

/**
 * PatternFly nav component.
 *
 * Identifiers for items need to be unique across groups!
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/nav">https://www.patternfly.org/v4/documentation/core/components/nav</a>
 */
public class Navigation extends ElementBuilder<HTMLElement, Navigation>
        implements HtmlContent<HTMLElement, Navigation> {

    // ------------------------------------------------------ factory methods

    public static Navigation simple() {
        return new Navigation(true, false, false, false, false);
    }

    public static Navigation grouped() {
        return new Navigation(true, false, false, true, false);
    }

    public static Navigation expandable() {
        return new Navigation(true, false, false, true, true);
    }

    public static Navigation horizontal() {
        return new Navigation(true, true, false, false, false);
    }

    public static Navigation tertiary() {
        return new Navigation(false, true, true, false, false);
    }

    // ------------------------------------------------------ navigation instance

    private final boolean horizontal;
    private final boolean tertiary;
    private final boolean grouped;
    private final boolean expandable;
    private final ItemDisplay<HTMLAnchorElement, NavigationItem> itemDisplay;
    private SelectHandler<NavigationItem> onSelect;

    private HTMLElement ul;
    private HTMLElement lastGroup;

    private Navigation(boolean global, boolean horizontal, boolean tertiary, boolean grouped, boolean expandable) {
        super(nav().css(component(nav)).aria(label, global ? "Global" : "Local").get());
        this.horizontal = horizontal;
        this.tertiary = tertiary;
        this.grouped = grouped;
        this.expandable = expandable;
        this.itemDisplay = new ItemDisplay<>();

        if (horizontal || tertiary) {
            add(button().css(component(nav, scroll, button))
                    .aria(label, "Scroll left")
                    .on(click, e -> scrollLeft())
                    .add(i().css(fas("angle-left")).aria(hidden, true_)));

            if (tertiary) { // tertiary (and horizontal)
                add(ul = ul().css(component(nav, Constants.tertiary, list)).get());

            } else { // horizontal (and not tertiary)
                add(ul = ul().css(component(nav, Constants.horizontal, list)).get());
            }
            add(button().css(component(nav, scroll, button))
                    .aria(label, "Scroll right")
                    .on(click, e -> scrollRight())
                    .add(i().css(fas("angle-right")).aria(hidden, true_)));

        } else {
            if (grouped) {
                if (expandable) { // expandable (and grouped)
                    add(ul = ul().css(component(nav, list)).get());
                } // nothing to do for grouped

            } else { // simple
                add(ul = ul().css(component(nav, simple, list)).get());
            }
        }
    }

    @Override
    public Navigation that() {
        return this;
    }

    // ------------------------------------------------------ public API

    public Navigation add(Iterable<NavigationItem> items) {
        for (NavigationItem item : items) {
            add(item);
        }
        return this;
    }

    public Navigation add(NavigationItem[] items) {
        for (NavigationItem item : items) {
            add(item);
        }
        return this;
    }

    public Navigation add(NavigationItem item) {
        addInternal(ul, null, item);
        return this;
    }

    public Navigation add(String group, NavigationItem item) {
        String groupId = groupId(group);
        lastGroup = cast(element.querySelector("ul[data-nav-group=" + groupId + "]"));
        if (lastGroup == null) {
            lastGroup = ul().css(component(nav, simple, list))
                    .data(navGroup, groupId)
                    .get();
            String labelId = uniqueId(nav, Constants.group, label);
            if (expandable) {
                ul.appendChild(li().css(component(nav, Constants.item), modifier(Constants.expandable))
                        .data(navGroupExpandable, groupId)
                        .add(a("#").css(component(nav, link))
                                .id(labelId)
                                .data(navGroupLink, groupId)
                                .textContent(group)
                                .on(click, e -> toggleGroup(groupId))
                                .add(span().css(component(nav, toggle))
                                        .add(i().css(fas(angleRight))
                                                .aria(hidden, true_))))
                        .add(section().css(component(nav, subnav))
                                .aria(labelledBy, labelId)
                                .attr(hidden, "")
                                .data(navGroupSection, groupId)
                                .add(lastGroup))
                        .get());

            } else { // grouped
                add(section().css(component(nav, section))
                        .aria(labelledBy, labelId)
                        .add(h(2, group).css(component(nav, section, title))
                                .id(labelId)
                                .textContent(group))
                        .add(lastGroup)
                        .get());
            }
        }
        addInternal(lastGroup, group, item);
        return this;
    }

    public void setCurrent(NavigationItem item) {
        setCurrent(item.getId());
    }

    public void setCurrent(String itemId) {
        Elements.stream(element.querySelectorAll("a[data-nav-item]"))
                .filter(htmlElements())
                .map(asHtmlElement())
                .forEach(e -> {
                    String value = e.dataset.get(navItem);
                    if (itemId.equals(value)) {
                        e.classList.add(modifier(current));
                        e.setAttribute("aria-current", page);
                        e.scrollIntoView();
                    } else {
                        e.classList.remove(modifier(current));
                        e.removeAttribute("aria-current");
                    }
                });

        if (expandable) {
            HTMLElement a = cast(element.querySelector("a[data-nav-item=" + itemId + "]"));
            if (a != null) {
                String groupId = a.dataset.get(navGroup);
                if (groupId != null) {
                    Elements.stream(element.querySelectorAll("li[data-nav-group-expandable]"))
                            .filter(htmlElements())
                            .map(asHtmlElement())
                            .forEach(e -> {
                                if (groupId.equals(e.dataset.get(navGroupExpandable))) {
                                    e.classList.add(modifier(current));
                                    expand(groupId);
                                } else {
                                    e.classList.remove(modifier(current));
                                }
                            });
                }
            }

        }
    }

    public Navigation asString(Function<NavigationItem, String> asString) {
        itemDisplay.asString = asString;
        return this;
    }

    public Navigation display(BiConsumer<HtmlContentBuilder<HTMLAnchorElement>, NavigationItem> display) {
        itemDisplay.display = display;
        return this;
    }

    // ------------------------------------------------------ events

    public Navigation onSelect(SelectHandler<NavigationItem> onSelect) {
        this.onSelect = onSelect;
        return this;
    }

    // ------------------------------------------------------ internals

    private void addInternal(HTMLElement ul, String group, NavigationItem item) {
        if (NavigationItem.SEPARATOR.equals(item) && (!horizontal && !tertiary)) {
            HTMLElement element = (grouped || expandable) ? lastGroup : ul;
            if (element != null) {
                element.appendChild(li().css(divider).attr(role, separator).get());
            }
        } else {
            HtmlContentBuilder<HTMLAnchorElement> a = a().css(component(nav, link))
                    .on(click, e -> {
                        if (onSelect != null) {
                            onSelect.onSelect(item);
                        }
                    })
                    .data(navItem, item.getId());
            if (item.getHref() != null) {
                a.attr("href", item.getHref());
            }
            itemDisplay.display.accept(a, item);
            if (group != null) {
                a.data(navGroup, groupId(group));
            }
            ul.appendChild(li().css(component(nav, Constants.item)).add(a).get());
            if (horizontal || tertiary) {
                // TODO add / remove "pf-m-start pf-m-end" to root element
            }
        }
    }

    private void toggleGroup(String groupId) {
        HTMLElement li = cast(element.querySelector("li[data-nav-group-expandable=" + groupId + "]"));
        HTMLElement a = cast(element.querySelector("a[data-nav-group-link=" + groupId + "]"));
        HTMLElement section = cast(element.querySelector("section[data-nav-group-section=" + groupId + "]"));
        if (li != null && a != null && section != null) {
            if (li.classList.contains(modifier(expanded))) {
                // collapse
                li.classList.remove(modifier(expanded));
                a.setAttribute("aria-expanded", false_);
                section.setAttribute(hidden, "");

            } else {
                // expand
                li.classList.add(modifier(expanded));
                a.setAttribute("aria-expanded", true_);
                section.removeAttribute(hidden);
            }
        }
    }

    private void expand(String groupId) {
        HTMLElement li = cast(element.querySelector("li[data-nav-group-expandable=" + groupId + "]"));
        HTMLElement a = cast(element.querySelector("a[data-nav-group-link=" + groupId + "]"));
        HTMLElement section = cast(element.querySelector("section[data-nav-group-section=" + groupId + "]"));
        if (li != null && a != null && section != null) {
            li.classList.add(modifier(expanded));
            a.setAttribute("aria-expanded", true_);
            section.removeAttribute(hidden);
        }
    }

    private String groupId(String group) {
        return buildId(group);
    }

    private void scrollLeft() {
        // TODO add / remove "pf-m-start pf-m-end" to root element
    }

    private void scrollRight() {
        // TODO add / remove "pf-m-start pf-m-end" to root element
    }
}
