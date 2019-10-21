package org.patternfly.client.components.datalist;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.removeChildrenFrom;
import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.*;

import java.util.HashMap;
import java.util.Map;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
import org.patternfly.client.dataprovider.DataProvider;
import org.patternfly.client.dataprovider.Display;
import org.patternfly.client.dataprovider.PageInfo;
import org.patternfly.client.dataprovider.SelectionInfo;

import elemental2.dom.HTMLElement;

/**
 * PatternFly data list.
 *
 * <p>
 * The data list does not manage data by itself. Use a {@link
 * DataProvider} and add the data list as a display to the data
 * provider:
 * </p>
 *
 * <pre>
 * DataProvider dataProvider = ...;
 * DataList list = ...;
 *
 * dataProvider.addDisplay(list);
 * dataProvider.setItems(...);
 * </pre>
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/core/components/datalist">https://www.patternfly.org/v4/documentation/core/components/datalist</a>
 */
public class DataList<T> extends ElementBuilder<HTMLElement, DataList<T>>
        implements HtmlContent<HTMLElement, DataList<T>>, Display<T> {

    private final DataProvider<T> dataProvider;
    private final ItemRenderer<T> itemRenderer;
    private final Map<String, VisualItem<T>> currentListItems;

    public DataList(DataProvider<T> dataProvider, ItemRenderer<T> itemRenderer) {
        super(ul().css(component(dataList))
                .attr(role, list)
                .get());
        this.dataProvider = dataProvider;
        this.itemRenderer = itemRenderer;
        this.currentListItems = new HashMap<>();
    }

    @Override
    public DataList<T> that() {
        return this;
    }

    @Override
    public void showItems(Iterable<T> items, PageInfo pageInfo) {
        currentListItems.clear();
        removeChildrenFrom(element);
        for (T item : items) {
            ItemDisplay<T> display = itemRenderer.render(item);
            VisualItem<T> vi = new VisualItem<>(item, display);
            currentListItems.put(vi.id, vi);
            add(vi);
        }
    }

    @Override
    public void updateSelection(SelectionInfo<T> selectionInfo) {
    }

    // ------------------------------------------------------ inner classes

    public static ItemRow row() {
        return new ItemRow();
    }

    public static class ItemRow extends ElementBuilder<HTMLElement, ItemRow>
            implements HtmlContent<HTMLElement, ItemRow> {

        public ItemRow() {
            super(div().css(component(dataList, itemRow)).get());
        }

        public ItemRow content(ItemContent content) {
            return add(content);
        }

        @Override
        public ItemRow that() {
            return this;
        }
    }

    public static ItemContent content() {
        return new ItemContent();
    }

    public static class ItemContent extends ElementBuilder<HTMLElement, ItemContent>
            implements HtmlContent<HTMLElement, ItemContent> {

        public ItemContent() {
            super(div().css(component(dataList, itemContent)).get());
        }

        public ItemContent cell(ItemCell cell) {
            return add(cell);
        }

        @Override
        public ItemContent that() {
            return this;
        }
    }

    public static ItemCell cell() {
        return new ItemCell();
    }

    public static class ItemCell extends ElementBuilder<HTMLElement, ItemCell>
            implements HtmlContent<HTMLElement, ItemCell> {

        public ItemCell() {
            super(div().css(component(dataList, cell)).get());
        }

        @Override
        public ItemCell that() {
            return this;
        }
    }
}
