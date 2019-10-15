package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.form;
import static org.patternfly.client.resources.Constants.group;

/**
 * PatternFly form group component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/form">https://www.patternfly.org/v4/documentation/core/components/form</a>
 */
public class FormGroup extends ElementBuilder<HTMLElement, FormGroup>
        implements HtmlContent<HTMLElement, FormGroup> {

    public FormGroup() {
        super(div().css(component(form, group))
                .get());
    }

    @Override
    public FormGroup that() {
        return this;
    }
}
