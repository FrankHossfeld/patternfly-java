package org.patternfly.showcase.client.documentation;

import java.util.HashMap;
import java.util.Map;

import com.github.nalukit.nalu.client.component.AbstractComponentController;
import com.github.nalukit.nalu.client.component.annotation.AcceptParameter;
import com.github.nalukit.nalu.client.component.annotation.Controller;
import elemental2.dom.HTMLElement;
import org.patternfly.client.components.Page;
import org.patternfly.client.core.Theme;
import org.patternfly.showcase.client.ShowcaseContext;
import org.patternfly.showcase.client.resources.Ids;
import org.patternfly.showcase.client.resources.Nav;
import org.patternfly.showcase.client.resources.Routes;

@Controller(route = Routes.COMPONENT,
        selector = Ids.ROOT_CONTAINER,
        component = ComponentsElement.class,
        componentInterface = Components.Component.class)
public class ComponentsController
        extends AbstractComponentController<ShowcaseContext, Components.Component, HTMLElement>
        implements Components.Controller {

    private static final Map<String, ComponentDocumentation> components = new HashMap<>();
    static {
        components.put("alert", new AlertDocumentation());
        components.put("badge", new BadgeDocumentation());
        components.put("brand", new BrandDocumentation());
        components.put("button", new ButtonDocumentation());
        components.put("chip", new ChipDocumentation());
        components.put("chip-group", new ChipGroupDocumentation());
        components.put("context-selector", new ContextSelectorDocumentation());
        components.put("dropdown", new DropdownDocumentation());
    }

    @Override
    public void start() {
        Page.get().verticalNavigation(Nav.documentation(), Theme.LIGHT);
    }

    @AcceptParameter("component")
    public void setComponent(String component) {
        Nav.documentation().setCurrent(component);
        getComponent().show(components.getOrDefault(component, new NotFound(component)));
    }
}
