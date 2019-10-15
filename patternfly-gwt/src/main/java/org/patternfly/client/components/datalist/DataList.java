package org.patternfly.client.components.datalist;

import java.util.HashMap;
import java.util.Map;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.dataprovider.DataProvider;
import org.patternfly.client.dataprovider.Display;
import org.patternfly.client.dataprovider.SelectionInfo;
import org.patternfly.client.dataprovider.PageInfo;

import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.dataList;
import static org.patternfly.client.resources.Constants.list;
import static org.patternfly.client.resources.Constants.role;

/**
 * PatternFly data list.
 *
 * <p>The data list does not manage data by itself. Use a {@link
 * DataProvider} and add the data list as a display to the data
 * provider:</p>
 *
 * <pre>
 * DataProvider dataProvider = ...;
 * DataList list = ...;
 *
 * dataProvider.addDisplay(list);
 * dataProvider.setItems(...);
 * </pre>
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/datalist">https://www.patternfly.org/v4/documentation/core/components/datalist</a>
 */
public class DataList<T> implements Display<T>, IsElement<HTMLElement> {

    private final DataProvider<T> dataProvider;
    private final ItemRenderer<T> itemRenderer;
    private final Map<String, Item<T>> currentListItems;
    private final HTMLElement root;

    public DataList(DataProvider<T> dataProvider, ItemRenderer<T> itemRenderer) {
        this.dataProvider = dataProvider;
        this.itemRenderer = itemRenderer;
        this.currentListItems = new HashMap<>();
        this.root = ul().css(component(dataList))
                .attr(role, list)
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    @Override
    public void showItems(Iterable<T> items, PageInfo pageInfo) {
        currentListItems.clear();
        Elements.removeChildrenFrom(root);
        for (T item : items) {
            ItemDisplay<T> display = itemRenderer.render(item);
            Item<T> itm = new Item<>(item, display);
            currentListItems.put(itm.id, itm);
            root.appendChild(itm.element());
        }
    }

    @Override
    public void updateSelection(SelectionInfo<T> selectionInfo) {
    }
}
