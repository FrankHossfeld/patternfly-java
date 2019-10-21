package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.EmptyState.emptyState;
import static org.patternfly.client.core.Callback.noop;
import static org.patternfly.client.resources.CSS.fas;

import org.patternfly.showcase.client.resources.Code;

class EmptyStateDocumentation extends ComponentDocumentation {

    EmptyStateDocumentation() {
        super("Empty state",
                p().textContent(
                        "Use an empty state component when there is no data or other information to show. It can also be used to communicate completion or other status at the end of a sequential task. An empty state may include a call to action to guide the user on what to do next.")
                        .get(),
                asList(
                        new Demo("Empty state", Code.get().emptyState().getText(),
                                () -> div()
                                        .add(emptyState(fas("cubes"), "Empty State")
                                                .body("This represents an the empty state pattern in PatternFly 4. Hopefully it's simple enough to use but flexible enough to meet a variety of needs.")
                                                .primary("Primary action", noop())
                                                .secondary("Multiple", noop())
                                                .secondary("Action buttons", noop())
                                                .secondary("Can", noop())
                                                .secondary("Go here", noop())
                                                .secondary("In the secondary", noop())
                                                .secondary("Action area", noop())
                                                .get())
                                        .get()),
                        new Demo("Empty state small", Code.get().emptyStateSmall().getText(),
                                () -> div()
                                        .add("NYI")
                                        .get()),
                        new Demo("Empty state large", Code.get().emptyStateLarge().getText(),
                                () -> div()
                                        .add("NYI")
                                        .get()),
                        new Demo("Empty state primary", Code.get().emptyStatePrimary().getText(),
                                () -> div()
                                        .add("NYI")
                                        .get()),
                        new Demo("Empty state loading", Code.get().emptyStateSpinner().getText(),
                                () -> div()
                                        .add("NYI")
                                        .get()),
                        new Demo("Empty state no match found", Code.get().emptyStateNoMatch().getText(),
                                () -> div()
                                        .add("NYI")
                                        .get())));
    }
}
