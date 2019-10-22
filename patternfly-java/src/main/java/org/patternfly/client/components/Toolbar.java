package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.toolbar;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.dataprovider.Display;
import org.patternfly.client.dataprovider.Filter2;
import org.patternfly.client.dataprovider.PageInfo;
import org.patternfly.client.dataprovider.SelectionInfo;

import elemental2.dom.HTMLDivElement;

/**
 * PatternFly toolbar component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/toolbar">https://www.patternfly.org/v4/documentation/core/components/toolbar</a>
 */
public class Toolbar<T> extends ElementBuilder<HTMLDivElement, Toolbar<T>>
        implements HtmlContent<HTMLDivElement, Toolbar<T>>, Display<T> {

    Toolbar() {
        super(div().css(component(toolbar)).get());
    }

    @Override
    public Toolbar<T> that() {
        return this;
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
