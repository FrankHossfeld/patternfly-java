package org.patternfly.showcase.client.documentation;

import org.patternfly.client.components.Title;
import org.patternfly.client.components.Title.Size;
import org.patternfly.showcase.client.resources.Code;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

class TitleDocumentation extends ComponentDocumentation {

    TitleDocumentation() {
        super("Title",
                p().textContent(
                        "The title component applies top and bottom margins, font-weight, font-size, and line-height to titles.")
                        .get(),
                singletonList(
                        new Demo("Title ()", Code.get().title().getText(),
                                () -> div()
                                        .add(new Title(1, "4xl Title", Size.SIZE_4XL))
                                        .add(new Title(2, "3xl Title", Size.SIZE_3XL))
                                        .add(new Title(3, "2xl Title", Size.SIZE_2XL))
                                        .add(new Title(4, "xl Title", Size.SIZE_XL))
                                        .add(new Title(5, "lg Title", Size.SIZE_LG))
                                        .add(new Title(6, "md Title", Size.SIZE_MD))
                                        .get()
                        )));
    }
}
