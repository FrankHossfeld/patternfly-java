package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

import org.patternfly.client.components.Label;
import org.patternfly.client.components.Title;
import org.patternfly.client.components.Title.HeadingLevel;
import org.patternfly.client.components.Title.Size;
import org.patternfly.showcase.client.resources.Code;

class TitleDocumentation extends ComponentDocumentation {

    TitleDocumentation() {
        super("Title",
                p().textContent(
                        "The title component applies top and bottom margins, font-weight, font-size, and line-height to titles.")
                        .get(),
                asList(
                        new Demo("Title ()", Code.get().title().getText(),
                                () -> div()
                                        .add(Title.create(HeadingLevel.H1, Size.SIZE_4XL, "4xl Title"))
                                        .add(Title.create(HeadingLevel.H2, Size.SIZE_3XL, "3xl Title"))
                                        .add(Title.create(HeadingLevel.H3, Size.SIZE_2XL, "2xl Title"))
                                        .add(Title.create(HeadingLevel.H4, Size.SIZE_XL, "xl Title"))
                                        .add(Title.create(HeadingLevel.H5, Size.SIZE_LG, "lg Title"))
                                        .add(Title.create(HeadingLevel.H6, Size.SIZE_MD, "md Title"))
                                        .get()
                )));
    }
}
