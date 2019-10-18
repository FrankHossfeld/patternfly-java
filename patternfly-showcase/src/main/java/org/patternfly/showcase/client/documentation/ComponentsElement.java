package org.patternfly.showcase.client.documentation;

import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class ComponentsElement extends AbstractComponent<Components.Controller, HTMLElement>
        implements Components.Component {

    @Override
    public void render() {
        initElement(div().get());
    }

    @Override
    public void show(ComponentDocumentation cd) {
        if (cd != null) {
            initElement(cd.element());
        }
    }

    @Override
    public void onAttach() {
        PR.prettyPrint();
    }
}
