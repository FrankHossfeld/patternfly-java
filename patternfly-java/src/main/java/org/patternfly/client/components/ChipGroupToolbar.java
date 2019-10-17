package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.h;
import static org.jboss.gwt.elemento.core.Elements.li;
import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.chipGroup;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.toolbar;

/**
 * PatternFly chip group toolbar component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/chipgroup">https://www.patternfly.org/v4/documentation/core/components/chipgroup</a>
 */
public class ChipGroupToolbar implements IsElement<HTMLElement> {

    private final HTMLElement root;

    public ChipGroupToolbar() {
        root = ul().css(component(chipGroup), modifier(toolbar)).get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    public ChipGroupToolbar add(String category, ChipGroup chipGroup) {
        root.appendChild(li()
                .add(h(4, category).css(component(Constants.chipGroup, label)))
                .add(chipGroup)
                .get());
        return this;
    }
}
