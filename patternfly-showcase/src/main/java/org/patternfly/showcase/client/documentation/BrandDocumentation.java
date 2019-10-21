package org.patternfly.showcase.client.documentation;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Components.brand;

import org.patternfly.showcase.client.resources.Code;

class BrandDocumentation extends ComponentDocumentation {

    BrandDocumentation() {
        super("Brand", p().textContent("Brand is used to place a product logotype on a screen.").get(),
                singletonList(new Demo("Simple brand", Code.get().brand().getText(),
                        () -> div().add(brand("https://www.patternfly.org/assets/images/pf_logo.svg")).get())));
    }
}
