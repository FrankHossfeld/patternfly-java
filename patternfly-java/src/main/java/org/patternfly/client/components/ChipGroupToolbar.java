package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.li;
import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.chipGroup;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.toolbar;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.resources.Constants;

import elemental2.dom.HTMLElement;

/**
 * PatternFly chip group toolbar component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/chipgroup">https://www.patternfly.org/v4/documentation/core/components/chipgroup</a>
 */
public class ChipGroupToolbar extends ElementBuilder<HTMLElement, ChipGroupToolbar>
        implements HtmlContent<HTMLElement, ChipGroupToolbar> {

    public ChipGroupToolbar() {
        super(ul().css(component(chipGroup), modifier(toolbar)).get());
    }

    @Override
    public ChipGroupToolbar that() {
        return this;
    }

    public ChipGroupToolbar add(String category, ChipGroup chipGroup) {
        add(li()
                .add(h(4, category).css(component(Constants.chipGroup, label)))
                .add(chipGroup)
                .get());
        return this;
    }
}
