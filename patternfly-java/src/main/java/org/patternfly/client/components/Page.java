package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.page;
import static org.patternfly.client.resources.Constants.role;
import static org.patternfly.client.resources.Constants.tabindex;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.Constants;
import org.patternfly.client.resources.Theme;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;

/**
 * = PatternFly page Component
 *
 * The page component is used to create the basic structure of a page with either vertical or horizontal navigation.
 *
 * == Usage
 *
 * [source,java]
 * --
 * Page page = new Page()
 * .header()
 * .sidebar();
 * --
 *
 * @see https://www.patternfly.org/v4/documentation/core/components/page
 */
public class Page extends ElementBuilder<HTMLDivElement, Page>
        implements HtmlContent<HTMLDivElement, Page> {

    // ------------------------------------------------------ factory methods

    private static Page instance;

    public static Page instance() {
        return instance;
    }

    public static Page page(PageHeader header, String mainContainerId) {
        instance = new Page(header, mainContainerId);
        return instance;
    }

    // ------------------------------------------------------ page instance

    private final HTMLElement main;
    private HTMLElement sidebar;

    private Page(PageHeader header, String mainContainerId) {
        super(div().css(component(page)).get());

        add(header);
        add(main = main().id(mainContainerId).css(component(page, Constants.main))
                .attr(role, Constants.main)
                .attr(tabindex, "-1")
                .get());
    }

    @Override
    public Page that() {
        return this;
    }

    // ------------------------------------------------------ public API

    /** Shortcut for `sidebar(new PageSidebar(navigation))` */
    public Page verticalNavigation(Navigation navigation) {
        return sidebar(new PageSidebar(navigation));
    }

    /** Shortcut for `sidebar(new PageSidebar(navigation, theme))` */
    public Page verticalNavigation(Navigation navigation, Theme theme) {
        return sidebar(new PageSidebar(navigation, theme));
    }

    public Page sidebar(PageSidebar sidebar) {
        removeSidebar();

        insertBefore(sidebar.get(), main);
        this.sidebar = sidebar.get();
        return this;
    }

    public void removeSidebar() {
        failSafeRemoveFromParent(this.sidebar);
    }

    public Page section(PageSection firstSection, PageSection... moreSections) {
        removeChildrenFrom(main);
        main.appendChild(firstSection.get());
        if (moreSections != null) {
            for (PageSection section : moreSections) {
                main.appendChild(section.get());
            }
        }
        return this;
    }
}
