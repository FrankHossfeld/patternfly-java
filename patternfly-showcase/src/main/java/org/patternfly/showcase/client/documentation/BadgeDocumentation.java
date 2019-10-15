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
                    .add(new Badge(7, true))
                    .add(" ")
                    .add(new Badge(24, true))
                    .add(" ")
                    .add(new Badge(423, true))
                    .add(" ")
                    .add(new Badge("999+", true))
                    .get());

    @DataElement Demo unread = new Demo("Badge (unread)", Code.get().badgeRead().getText(),
            () -> div()
                    .add(new Badge(7))
                    .add(" ")
                    .add(new Badge(24))
                    .add(" ")
                    .add(new Badge(423))
                    .add(" ")
                    .add(new Badge("999+"))
                    .get());
}
