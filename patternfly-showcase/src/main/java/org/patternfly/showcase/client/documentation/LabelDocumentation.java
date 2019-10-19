package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

import org.patternfly.client.components.Badge;
import org.patternfly.client.components.Label;
import org.patternfly.showcase.client.resources.Code;

class LabelDocumentation extends ComponentDocumentation {

    LabelDocumentation() {
        super("Label",
                p().textContent(
                        "Use a label when you want to highlight an element on a page to draw attention to it or make it more searchable.")
                        .get(),
                asList(
                        new Demo("Badge ()", Code.get().label().getText(),
                                () -> div()
                                        .add(Label.text("Default Label"))
                                        .add(" ")
                                        .add(Label.text("Compact Label", true))
                                        .get()
                )));
    }
}
