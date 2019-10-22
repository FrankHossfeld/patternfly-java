package org.patternfly.showcase.client.documentation;

import org.patternfly.showcase.client.resources.Code;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Components.expandable;

class ExpandableDocumentation extends ComponentDocumentation {

    ExpandableDocumentation() {
        super("Expandable",
                p().textContent(
                        "The expandable component is used to embed an expandable section within a form when you don’t want controls or information to be shown by default. This may be used to hide advanced features, for example.")
                        .get(),
                singletonList(new Demo("Simple expandable", Code.get().expandable().getText(),
                        () -> div()
                                .add(expandable().textContent("This content is visible only when the component is expanded."))
                                .get())));
    }
}
