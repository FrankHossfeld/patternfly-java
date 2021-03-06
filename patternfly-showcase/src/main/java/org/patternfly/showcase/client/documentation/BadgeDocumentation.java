package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

import org.patternfly.client.components.Badge;
import org.patternfly.showcase.client.resources.Code;

class BadgeDocumentation extends ComponentDocumentation {

    BadgeDocumentation() {
        super("Badge",
                p().textContent(
                        "A badge is used to annotate other information like a label or an object name. Badges are typically used to reflect a count, e.g. number of object, number of events, number of unread, etc.")
                        .get(),
                asList(
                        new Demo("Badge (read)", Code.get().badgeUnread().getText(),
                                () -> div()
                                        .add(Badge.read(7))
                                        .add(" ")
                                        .add(Badge.read(24))
                                        .add(" ")
                                        .add(Badge.read(423))
                                        .add(" ")
                                        .add(Badge.read("999+"))
                                        .get()),
                        new Demo("Badge (unread)", Code.get().badgeRead().getText(),
                                () -> div()
                                        .add(Badge.unread(7))
                                        .add(" ")
                                        .add(Badge.unread(24))
                                        .add(" ")
                                        .add(Badge.unread(423))
                                        .add(" ")
                                        .add(Badge.unread("999+"))
                                        .get())));
    }
}
