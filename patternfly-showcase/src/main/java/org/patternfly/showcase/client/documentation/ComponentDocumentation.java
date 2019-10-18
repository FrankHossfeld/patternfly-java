package org.patternfly.showcase.client.documentation;

import java.util.List;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.components.PageSection;
import org.patternfly.client.resources.Constants;

import static java.util.stream.Collectors.toList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.h;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.content;
import static org.patternfly.client.resources.Constants.light;

class ComponentDocumentation implements IsElement<HTMLElement> {

    private final HTMLElement root;

    ComponentDocumentation(String title, HTMLElement description, List<Demo> demos) {
        PageSection section = new PageSection().css(modifier(light))
                .add(div().css(component(content))
                        .add(h(1, title).css(component(Constants.title), modifier("4-xl")))
                        .add(description))
                .addAll(demos.stream().map(Demo::element).collect(toList()));
        this.root = section.get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
