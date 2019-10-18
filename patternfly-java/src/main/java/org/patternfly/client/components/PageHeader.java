package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.CSS;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.header;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.Constants.button;
import static org.patternfly.client.resources.Constants.header;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.nav;
import static org.patternfly.client.resources.Constants.*;

public class PageHeader implements IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    private static PageHeader instance;

    public static PageHeader get() {
        return instance;
    }

    public static PageHeader create(String brand, String homeLink) {
        instance = new PageHeader(span().textContent(brand).get(), homeLink);
        return instance;
    }

    public static PageHeader create(Brand brand, String homeLink) {
        instance = new PageHeader(brand.element(), homeLink);
        return instance;
    }

    public static PageHeader create(HTMLElement brand, String homeLink) {
        instance = new PageHeader(brand, homeLink);
        return instance;
    }


    // ------------------------------------------------------ page instance

    private final HTMLElement toggle;
    private final HTMLElement root;
    private final HTMLElement brandLink;
    private final HTMLElement csContainer;
    private final HTMLElement navContainer;

    private PageHeader(HTMLElement brand, String homeLink) {
        toggle = div().css(component(page, header, Constants.brand, Constants.toggle))
                .add(button().css(component(button), CSS.modifier(plain))
                        .aria(Constants.expanded, true_).aria(label, "Global Navigation")
                        .add(i().css(fas("bars")).aria(hidden, true_)))
                .get();

        root = header().css(component(page, header))
                .attr(Constants.role, Constants.banner)
                .add(div().css(component(page, header, Constants.brand))
                        .add(brandLink = a(homeLink).css(
                                component(page, header, Constants.brand, link))
                                .add(brand)
                                .get()))
                .add(navContainer = div().css(component(page, header, nav))
                        .add(csContainer = div().css(component(page, header, Constants.selector))
                                // TODO remove styles once https://github.com/patternfly/patternfly-next/issues/1900 is fixed
                                .style("margin-right: var(--pf-c-page__header-nav--lg--MarginRight); " +
                                        "margin-top: -10px")
                                .get())
                        .get())
                .add(div().css(component(page, header, Constants.tools)))
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    // ------------------------------------------------------ public API

    public PageHeader contextSelector(ContextSelector contextSelector) {
        csContainer.appendChild(contextSelector.element());
        return this;
    }

    public PageHeader navigation(Navigation navigation) {
        navContainer.appendChild(navigation.element());
        return this;
    }
}