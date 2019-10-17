package org.patternfly.client.components.datalist;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.cell;
import static org.patternfly.client.resources.Constants.dataList;
import static org.patternfly.client.resources.Constants.itemContent;
import static org.patternfly.client.resources.Constants.itemRow;

public final class ItemBuilder {

    public static ItemRow row() {
        return new ItemRow();
    }

    public static ItemContent content() {
        return new ItemContent();
    }

    public static ItemCell cell() {
        return new ItemCell();
    }


    public static class ItemRow extends ElementBuilder<HTMLElement, ItemRow>
            implements HtmlContent<HTMLElement, ItemRow> {

        public ItemRow() {
            super(div().css(component(dataList, itemRow)).get());
        }

        public ItemRow add(ItemContent content) {
            add(content.get());
            return that();
        }

        @Override
        public ItemRow that() {
            return this;
        }
    }


    public static class ItemContent extends ElementBuilder<HTMLElement, ItemContent>
            implements HtmlContent<HTMLElement, ItemContent> {

        public ItemContent() {
            super(div().css(component(dataList, itemContent)).get());
        }

        public ItemContent add(ItemCell cell) {
            add(cell.get());
            return that();
        }

        @Override
        public ItemContent that() {
            return this;
        }
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


    private ItemBuilder() {
    }
}
