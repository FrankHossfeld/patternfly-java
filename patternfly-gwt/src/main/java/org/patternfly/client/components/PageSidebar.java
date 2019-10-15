package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.core.Theme;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.core.Theme.DARK;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;

/**
 * = PatternFly Sidebar Component
 *
 * The sidebar component holds the vertical navigation. By default the sidebar uses the dark theme and is expanded.
 * The collapsed / expanded state is controlled by the {@link PageHeader}.
 *
 * == Usage
 *
 * [source,java]
 * --
 * Page page = new Page()
 * .header(new PageHeader())
 * .sidebar(new PageSidebar(new Navigation()))
 * .section(new PageSection());
 * --
 *
 * @see https://www.patternfly.org/v4/documentation/core/components/page
 */
public class PageSidebar implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public PageSidebar(Navigation navigation) {
        this(navigation, DARK);
    }

    public PageSidebar(Navigation navigation, Theme theme) {
        root = div().css(component(page, sidebar), modifier(expanded))
                .add(div().css(component(page, sidebar, body))
                        .add(navigation))
                .get();
        if (theme == DARK) {
            root.classList.add(modifier(dark));
            navigation.element().classList.add(modifier(dark));
        }
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
