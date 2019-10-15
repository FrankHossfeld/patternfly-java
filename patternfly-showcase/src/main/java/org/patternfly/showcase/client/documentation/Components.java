package org.patternfly.showcase.client.documentation;

import com.github.nalukit.nalu.client.component.IsComponent;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;

public interface Components {

    interface Controller extends IsComponent.Controller {
    }


    interface Component extends IsComponent<Controller, HTMLElement> {

        void show(IsElement<HTMLElement> component);
    }
}
