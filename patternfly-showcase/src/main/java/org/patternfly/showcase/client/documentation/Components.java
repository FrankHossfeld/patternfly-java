package org.patternfly.showcase.client.documentation;

import com.github.nalukit.nalu.client.component.IsComponent;
import elemental2.dom.HTMLElement;

public interface Components {

    interface Controller extends IsComponent.Controller {
    }


    interface Component extends IsComponent<Controller, HTMLElement> {

        void show(ComponentDocumentation cd);
    }
}
