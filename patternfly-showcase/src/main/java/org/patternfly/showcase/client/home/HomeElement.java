package org.patternfly.showcase.client.home;

import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.Templated;

import com.github.nalukit.nalu.client.component.AbstractComponent;

import elemental2.dom.HTMLElement;

@Templated("home.html#content")
abstract class HomeElement extends AbstractComponent<Home.Controller, HTMLElement>
        implements Home.Component, IsElement<HTMLElement> {

    static HomeElement create() {
        return new Templated_HomeElement();
    }

    @Override
    public void render() {
        initElement(element());
    }
}
