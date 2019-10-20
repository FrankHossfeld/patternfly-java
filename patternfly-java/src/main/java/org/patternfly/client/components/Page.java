package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Theme;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.page;
import static org.patternfly.client.resources.Constants.role;
import static org.patternfly.client.resources.Constants.tabindex;

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
public class Page implements IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    private static Page instance;

    public static Page get() {
        return instance;
    }

    public static Page create(PageHeader header, String mainContainerId) {
        instance = new Page(header, mainContainerId);
        return instance;
    }


    // ------------------------------------------------------ page instance

    private final HTMLElement main;
    private final HTMLElement root;
    private HTMLElement sidebar;

    private Page(PageHeader header, String mainContainerId) {
        root = div().css(component(page))
                .add(header)
                .add(main = main().id(mainContainerId).css(component(page, Constants.main))
                        .attr(role, Constants.main)
                        .attr(tabindex, "-1")
                        .get())
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
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
        failSafeRemoveFromParent(this.sidebar);
        insertBefore(sidebar.element(), main);
        this.sidebar = sidebar.element();
        return this;
    }

    public void removeSidebar() {
        failSafeRemoveFromParent(this.sidebar);
        this.sidebar = null;
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
