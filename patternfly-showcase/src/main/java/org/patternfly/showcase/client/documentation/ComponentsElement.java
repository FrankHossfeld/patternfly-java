package org.patternfly.showcase.client.documentation;

import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.div;

public class ComponentsElement extends AbstractComponent<Components.Controller, HTMLElement>
        implements Components.Component {

    @Override
    public void render() {
        initElement(div().get());
    }

    @Override
    public void show(IsElement<HTMLElement> component) {
        if (component != null) {
            initElement(component.element());
        }
    }

    @Override
    public void onAttach() {
        PR.prettyPrint();
    }
}
