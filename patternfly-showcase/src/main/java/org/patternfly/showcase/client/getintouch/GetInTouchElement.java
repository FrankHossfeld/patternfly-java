package org.patternfly.showcase.client.getintouch;

import com.github.nalukit.nalu.client.component.AbstractComponent;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.Templated;

@Templated("get-in-touch.html#content")
abstract class GetInTouchElement extends AbstractComponent<GetInTouch.Controller, HTMLElement>
        implements GetInTouch.Component, IsElement<HTMLElement> {

    static GetInTouchElement create() {
        return new Templated_GetInTouchElement();
    }

    @Override
    public void render() {
        initElement(element());
    }
}
