package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.img;
import static org.jboss.gwt.elemento.core.Elements.input;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.jboss.gwt.elemento.core.InputType.checkbox;
import static org.patternfly.client.components.Card.Head.actions;
import static org.patternfly.client.components.Card.body;
import static org.patternfly.client.components.Card.footer;
import static org.patternfly.client.components.Card.head;
import static org.patternfly.client.components.Card.header;
import static org.patternfly.client.components.Components.card;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.noFill;

import org.patternfly.client.components.Dropdown;
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
                                        .get()),

                        new Demo("Card with image and actions", Code.get().cardImageActions().getText(),
                                () -> div()
                                        .add(card()
                                                .add(head()
                                                        .add(img("./images/pf_logo.svg").style("width: 300px"))
                                                        .add(actions()
                                                                .add(Dropdown.<String> kebab().right()
                                                                        .add("Link")
                                                                        .add("Action")
                                                                        .add("Disabled Link", true)
                                                                        .add("Disabled Action", true)
                                                                        .addSeparator()
                                                                        .add("Separated Link"))
                                                                .add(input(checkbox))))
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card header in card head", Code.get().cardHeaderInHead().getText(),
                                () -> div()
                                        .add(card()
                                                .add(head()
                                                        .add(actions()
                                                                .add(Dropdown.<String> kebab().right()
                                                                        .add("Link")
                                                                        .add("Action")
                                                                        .add("Disabled Link", true)
                                                                        .add("Disabled Action", true)
                                                                        .addSeparator()
                                                                        .add("Separated Link"))
                                                                .add(input(checkbox)))
                                                        .add(header()
                                                                .textContent(
                                                                        "This is a really really really really really really really really really really long header")))
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Only actions in card head (no header/footer)", Code.get().cardActionsInHead().getText(),
                                () -> div()
                                        .add(card()
                                                .add(head()
                                                        .add(actions()
                                                                .add(Dropdown.<String> kebab().right()
                                                                        .add("Link")
                                                                        .add("Action")
                                                                        .add("Disabled Link", true)
                                                                        .add("Disabled Action", true)
                                                                        .addSeparator()
                                                                        .add("Separated Link"))
                                                                .add(input(checkbox))))
                                                .add(body().textContent(
                                                        "This is the card body, there is only actions in the card head.")))
                                        .get()),
                        new Demo("Only image in the card head", Code.get().cardImageInHead().getText(),
                                () -> div()
                                        .add(card()
                                                .add(head()
                                                        .add(img("./images/pf_logo.svg").style("width: 300px")))
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card with no footer", Code.get().cardNoFooter().getText(),
                                () -> div()
                                        .add(card()
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("This card has no footer")))
                                        .get()),
                        new Demo("Card with no header", Code.get().cardNoHeader().getText(),
                                () -> div()
                                        .add(card()
                                                .add(body().textContent("This card has no header"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card with only a content section", Code.get().cardContentOnly().getText(),
                                () -> div()
                                        .add(card()
                                                .add(body().textContent("Body")))
                                        .get()),
                        new Demo("Card with multiple body sections", Code.get().cardMultipleBodies().getText(),
                                () -> div()
                                        .add(card()
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(body().textContent("Body"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card with only one body that fills", Code.get().cardBodyFill().getText(),
                                () -> div()
                                        .add(card().style("min-height: 30em;")
                                                .add(header().textContent("Header"))
                                                .add(body().css(modifier(noFill)).textContent("Body pf-m-no-fill"))
                                                .add(body().css(modifier(noFill)).textContent("Body pf-m-no-fill"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card hover example", Code.get().cardHover().getText(),
                                () -> div()
                                        .add(card().hoverable()
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get()),
                        new Demo("Card compact example", Code.get().cardCompact().getText(),
                                () -> div()
                                        .add(card().compact()
                                                .add(header().textContent("Header"))
                                                .add(body().textContent("Body"))
                                                .add(footer().textContent("Footer")))
                                        .get())));
    }
}
