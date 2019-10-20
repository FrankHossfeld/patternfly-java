package org.patternfly.showcase.client.resources;

import org.patternfly.client.components.Navigation;
import org.patternfly.client.components.NavigationItem;

import static org.patternfly.showcase.client.resources.Routes.*;

public interface Nav {

    Navigation HORIZONTAL_NAV = Navigation.horizontal()
            .add(new NavigationItem("get-started", "Get Started", hash(GET_STARTED)))
            .add(new NavigationItem("documentation", "Documentation",
                    hash(COMPONENT.replace(":component", "alert"))))
            .add(new NavigationItem("contribute", "Contribute", hash(CONTRIBUTE)))
            .add(new NavigationItem("get-in-touch", "Get in Touch", hash(GET_IN_TOUCH)));

    Navigation DOCUMENTATION_NAV = Navigation.expandable()
            .add("Component", new NavigationItem("alert", "Alert",
                    hash(COMPONENT.replace(":component", "alert"))))
            .add("Component", new NavigationItem("avatar", "Avatar",
                    hash(COMPONENT.replace(":component", "avatar"))))
            .add("Component", new NavigationItem("badge", "Badge",
                    hash(COMPONENT.replace(":component", "badge"))))
            .add("Component", new NavigationItem("brand", "Brand",
                    hash(COMPONENT.replace(":component", "brand"))))
            .add("Component", new NavigationItem("button", "Button",
                    hash(COMPONENT.replace(":component", "button"))))
            .add("Component", new NavigationItem("chip", "Chip",
                    hash(COMPONENT.replace(":component", "chip"))))
            .add("Component", new NavigationItem("chip-group", "Chip Group",
                    hash(COMPONENT.replace(":component", "chip-group"))))
            .add("Component", new NavigationItem("context-selector", "Context selector",
                    hash(COMPONENT.replace(":component", "context-selector"))))
            .add("Component", new NavigationItem("dropdown", "Dropdown",
                    hash(COMPONENT.replace(":component", "dropdown"))))
            .add("Component", new NavigationItem("label", "Label",
                    hash(COMPONENT.replace(":component", "label"))))
            .add("Component", new NavigationItem("title", "Title",
                    hash(COMPONENT.replace(":component", "title"))));

    static Navigation horizontal() {
        return HORIZONTAL_NAV;
    }

    static Navigation documentation() {
        return DOCUMENTATION_NAV;
    }
}
