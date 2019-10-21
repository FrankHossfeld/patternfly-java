package org.patternfly.showcase.client.getstarted;

import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.Templated;

import com.github.nalukit.nalu.client.component.AbstractComponent;

import elemental2.dom.HTMLElement;

@Templated("get-started.html#content")
abstract class GetStartedElement extends AbstractComponent<GetStarted.Controller, HTMLElement>
        implements GetStarted.Component, IsElement<HTMLElement> {

    static GetStartedElement create() {
        return new Templated_GetStartedElement();
    }

    @Override
    public void render() {
        initElement(element());
    }
}
