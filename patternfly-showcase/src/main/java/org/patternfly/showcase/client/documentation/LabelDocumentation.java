package org.patternfly.showcase.client.documentation;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Components.label;

import org.patternfly.showcase.client.resources.Code;

class LabelDocumentation extends ComponentDocumentation {

    LabelDocumentation() {
        super("Label",
                p().textContent(
                        "Use a label when you want to highlight an element on a page to draw attention to it or make it more searchable.")
                        .get(),
                singletonList(new Demo("Simple label", Code.get().label().getText(),
                        () -> div()
                                .add(label("Default Label"))
                                .add(" ")
                                .add(label("Compact Label", true))
                                .get())));
    }
}
