package org.patternfly.showcase.client.documentation;

import static java.util.Collections.emptyList;
import static org.jboss.gwt.elemento.core.Elements.code;
import static org.jboss.gwt.elemento.core.Elements.p;

class NotFound extends ComponentDocumentation {

    NotFound(String component) {
        super("Not found",
                p().add("Unable to find documentation for component ")
                        .add(code().textContent(component))
                        .add(".")
                        .get(),
                emptyList());
    }
}
