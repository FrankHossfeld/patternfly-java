package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.dataprovider.Display;
import org.patternfly.client.dataprovider.Filter2;
import org.patternfly.client.dataprovider.PageInfo;
import org.patternfly.client.dataprovider.SelectionInfo;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.toolbar;

/**
 * PatternFly toolbar component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/toolbar">https://www.patternfly.org/v4/documentation/core/components/toolbar</a>
 */
public class Toolbar<T> implements Display<T>, IsElement<HTMLElement> {

    private final HTMLElement root;

    public Toolbar() {
        root = div().css(component(toolbar))
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    public Toolbar<T> filter(Filter2<T> filter) {
        return this;
    }

    @Override
    public void showItems(Iterable<T> items, PageInfo pageInfo) {
    }

    @Override
    public void updateSelection(SelectionInfo<T> selectionInfo) {
    }
}
