package org.patternfly.showcase.client.documentation;

import javax.annotation.PostConstruct;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;

@Templated("not-found.html#content")
abstract class NotFound implements IsElement<HTMLElement> {

    static NotFound create(String name) {
        return new Templated_NotFound(name);
    }

    abstract String name();

    @DataElement HTMLElement component;

    @PostConstruct
    void init() {
        component.textContent = name();
    }
}
