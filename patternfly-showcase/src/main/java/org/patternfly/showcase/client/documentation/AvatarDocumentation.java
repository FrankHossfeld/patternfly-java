package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;

import org.patternfly.client.components.Avatar;
import org.patternfly.showcase.client.resources.Code;

class AvatarDocumentation extends ComponentDocumentation {

  AvatarDocumentation() {
    super(
        "Avatar",
        p().textContent(
                "The avatar is used to represent a user. It may contain an image that represents the user or a placeholder graphic in the absence of an image. Typical usage is to represent the current user in the masthead. Related design guidelines: Masthead")
            .get(),
        asList(
            new Demo(
                "Avatar ()",
                Code.get().avatar().getText(),
                () -> div().add(Avatar.create("./images/img_avatar.svg", "avatar")).get())));
  }
}
