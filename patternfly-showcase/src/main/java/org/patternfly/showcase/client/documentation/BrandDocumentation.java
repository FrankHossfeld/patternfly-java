package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Brand;
import org.patternfly.client.components.Chip;
import org.patternfly.showcase.client.resources.Code;

import static elemental2.dom.DomGlobal.alert;
import static org.jboss.gwt.elemento.core.Elements.div;

@Templated("brand.html#content")
abstract class BrandDocumentation implements IsElement<HTMLElement> {

    static BrandDocumentation create() {
        return new Templated_BrandDocumentation();
    }

    @DataElement Demo brand = new Demo("Simple Brand", Code.get().brand().getText(),
            () -> div()
                    .add(new Brand("https://www.patternfly.org/assets/images/pf_logo.svg"))
                    .get());
}
