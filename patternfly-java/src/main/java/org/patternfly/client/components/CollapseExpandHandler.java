package org.patternfly.client.components;

import static elemental2.dom.DomGlobal.document;
import static org.jboss.gwt.elemento.core.Elements.setVisible;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.expanded;
import static org.patternfly.client.resources.Constants.false_;
import static org.patternfly.client.resources.Constants.hidden;
import static org.patternfly.client.resources.Constants.true_;

import java.util.function.Consumer;

import org.gwtproject.event.shared.HandlerRegistration;

import elemental2.dom.HTMLElement;
import elemental2.dom.Node;

/** Reusable class for components which have a collapsible / expandable UI element */
class CollapseExpandHandler {

    private HandlerRegistration closeHandler;
    Consumer<Boolean> onToggle;

    void expand(HTMLElement root, HTMLElement button, HTMLElement menu) {
        if (!root.classList.contains(modifier(expanded))) {
            closeHandler = bind(document, click, e -> {
                boolean clickInside = root.contains((Node) e.target);
                if (!clickInside) {
                    collapse(root, button, menu);
                }
            });
            root.classList.add(modifier(expanded));
            button.setAttribute("aria-expanded", true_);
            menu.removeAttribute(hidden);
            setVisible(menu, true);
            if (onToggle != null) {
                onToggle.accept(true);
            }
        } else {
            collapse(root, button, menu);
        }
    }

    void collapse(HTMLElement root, HTMLElement button, HTMLElement menu) {
        if (root.classList.contains(modifier(expanded))) {
            root.classList.remove(modifier(expanded));
            button.setAttribute("aria-expanded", false_);
            menu.setAttribute(hidden, "");
            setVisible(menu, false);
            closeHandler.removeHandler();
            if (onToggle != null) {
                onToggle.accept(false);
            }
        }
    }
}
