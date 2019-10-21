package org.patternfly.showcase.client.documentation;

import static java.util.stream.Collectors.toList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.components.Components.pageSection;
import static org.patternfly.client.components.Components.title;
import static org.patternfly.client.components.Title.Size.SIZE_4XL;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.content;
import static org.patternfly.client.resources.Constants.light;

import java.util.List;

import org.jboss.gwt.elemento.core.IsElement;

import elemental2.dom.HTMLElement;

class ComponentDocumentation implements IsElement<HTMLElement> {

    private final HTMLElement root;

    ComponentDocumentation(String title, HTMLElement description, List<Demo> demos) {
        root = pageSection().css(modifier(light))
                .add(div().css(component(content))
                        .add(title(1, title, SIZE_4XL))
                        .add(description))
                .addAll(demos.stream().map(Demo::element).collect(toList()))
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
