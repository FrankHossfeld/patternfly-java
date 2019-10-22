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
            .add("Components", new NavigationItem("alert", "Alert",
                    hash(COMPONENT.replace(":component", "alert"))))
            .add("Components", new NavigationItem("avatar", "Avatar",
                    hash(COMPONENT.replace(":component", "avatar"))))
            .add("Components", new NavigationItem("badge", "Badge",
                    hash(COMPONENT.replace(":component", "badge"))))
            .add("Components", new NavigationItem("brand", "Brand",
                    hash(COMPONENT.replace(":component", "brand"))))
            .add("Components", new NavigationItem("button", "Button",
                    hash(COMPONENT.replace(":component", "button"))))
            .add("Components", new NavigationItem("card", "Card",
                    hash(COMPONENT.replace(":component", "card"))))
            .add("Components", new NavigationItem("chip", "Chip",
                    hash(COMPONENT.replace(":component", "chip"))))
            .add("Components", new NavigationItem("chip-group", "Chip Group",
                    hash(COMPONENT.replace(":component", "chip-group"))))
            .add("Components", new NavigationItem("context-selector", "Context selector",
                    hash(COMPONENT.replace(":component", "context-selector"))))
            .add("Components", new NavigationItem("dropdown", "Dropdown",
                    hash(COMPONENT.replace(":component", "dropdown"))))
            .add("Components", new NavigationItem("empty-state", "Empty state",
                    hash(COMPONENT.replace(":component", "empty-state"))))
            .add("Components", new NavigationItem("expandable", "Expandable",
                    hash(COMPONENT.replace(":component", "expandable"))))
            .add("Components", new NavigationItem("label", "Label",
                    hash(COMPONENT.replace(":component", "label"))))
            .add("Components", new NavigationItem("title", "Title",
                    hash(COMPONENT.replace(":component", "title"))));

    static Navigation horizontal() {
        return HORIZONTAL_NAV;
    }

    static Navigation documentation() {
        return DOCUMENTATION_NAV;
    }
}
