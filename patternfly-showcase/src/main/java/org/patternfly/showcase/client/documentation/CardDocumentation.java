package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Card.body;
import static org.patternfly.client.components.Card.footer;
import static org.patternfly.client.components.Card.header;
import static org.patternfly.client.components.Components.card;

import org.patternfly.showcase.client.resources.Code;

class CardDocumentation extends ComponentDocumentation {

    CardDocumentation() {
        super("Card", p().textContent(
                "A card is a flexible element for containing any kind of content. Cards are used on dashboards, in data displays (e.g. Card View), or for positioning content on a page.")
                .get(),
                asList(
                        new Demo("Simple card", Code.get().cardSimple().getText(),
                                () -> div()
                                        .add(card()
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get())));
    }
}
