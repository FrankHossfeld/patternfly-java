package org.patternfly.client.components;

import elemental2.dom.HTMLDivElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.Theme;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.*;
import static org.patternfly.client.resources.Theme.DARK;

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
public class PageSidebar extends ElementBuilder<HTMLDivElement, PageSidebar>
        implements HtmlContent<HTMLDivElement, PageSidebar> {

    public PageSidebar(Navigation navigation) {
        this(navigation, DARK);
    }

    public PageSidebar(Navigation navigation, Theme theme) {
        super(div().css(component(page, sidebar), modifier(expanded)).get());
        add(div().css(component(page, sidebar, body))
                .add(navigation))
                .get();
        if (theme == DARK) {
            element.classList.add(modifier(dark));
            navigation.css(modifier(dark));
        }
    }

    @Override
    public PageSidebar that() {
        return this;
    }
}
