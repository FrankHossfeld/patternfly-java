package org.patternfly.showcase.client.documentation;

import org.patternfly.showcase.client.resources.Code;

import static java.util.Collections.singletonList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Components.avatar;

class AvatarDocumentation extends ComponentDocumentation {

    AvatarDocumentation() {
        super(
                "Avatar",
                p().textContent(
                        "The avatar is used to represent a user. It may contain an image that represents the user or a placeholder graphic in the absence of an image. Typical usage is to represent the current user in the masthead. Related design guidelines: Masthead")
                        .get(),
                singletonList(
                        new Demo("Simple avatar", Code.get().avatar().getText(),
                                () -> div().add(avatar("./images/img_avatar.svg", "avatar")).get())));
    }
}
