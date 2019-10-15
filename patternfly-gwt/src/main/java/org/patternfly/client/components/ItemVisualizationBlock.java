package org.patternfly.client.components;

import java.util.function.BiConsumer;
import java.util.function.Function;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;

import static org.jboss.gwt.elemento.core.Elements.buildId;

class ItemVisualizationBlock<E extends HTMLElement, T> {

    Function<T, String> asString;
    BiConsumer<HtmlContentBuilder<E>, T> display;

    ItemVisualizationBlock() {
        this.asString = String::valueOf;
        this.display = (element, item) -> element.textContent(asString.apply(item));
    }

    String itemId(T item) {
        return buildId(asString.apply(item));
    }
}
