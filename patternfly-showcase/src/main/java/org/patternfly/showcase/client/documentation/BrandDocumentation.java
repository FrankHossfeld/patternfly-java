package org.patternfly.showcase.client.documentation;

import org.patternfly.client.components.Brand;
import org.patternfly.showcase.client.resources.Code;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

class BrandDocumentation extends ComponentDocumentation {

    BrandDocumentation() {
        super("Brand", p().textContent("Brand is used to place a product logotype on a screen.").get(),
                singletonList(new Demo("Simple Brand", Code.get().brand().getText(),
                        () -> div().add(new Brand("https://www.patternfly.org/assets/images/pf_logo.svg")).get())));
    }
}
