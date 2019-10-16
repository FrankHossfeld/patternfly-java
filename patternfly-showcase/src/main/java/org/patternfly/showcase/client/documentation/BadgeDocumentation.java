package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Badge;
import org.patternfly.showcase.client.resources.Code;

import static org.jboss.gwt.elemento.core.Elements.div;

@Templated("badge.html#content")
abstract class BadgeDocumentation implements IsElement<HTMLElement> {

    static BadgeDocumentation create() {
        return new Templated_BadgeDocumentation();
    }

    @DataElement Demo read = new Demo("Badge (read)", Code.get().badgeUnread().getText(),
            () -> div()
                    .add(Badge.read(7))
                    .add(" ")
                    .add(Badge.read(24))
                    .add(" ")
                    .add(Badge.read(423))
                    .add(" ")
                    .add(Badge.read("999+"))
                    .get());

    @DataElement Demo unread = new Demo("Badge (unread)", Code.get().badgeRead().getText(),
            () -> div()
                    .add(Badge.unread(7))
                    .add(" ")
                    .add(Badge.unread(24))
                    .add(" ")
                    .add(Badge.unread(423))
                    .add(" ")
                    .add(Badge.unread("999+"))
                    .get());
}
