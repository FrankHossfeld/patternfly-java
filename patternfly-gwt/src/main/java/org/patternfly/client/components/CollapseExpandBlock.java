package org.patternfly.client.components;

import java.util.function.BiConsumer;

import elemental2.dom.HTMLElement;
import elemental2.dom.Node;
import org.gwtproject.event.shared.HandlerRegistration;

import static elemental2.dom.DomGlobal.document;
import static org.jboss.gwt.elemento.core.Elements.setVisible;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.expanded;
import static org.patternfly.client.resources.Constants.false_;
import static org.patternfly.client.resources.Constants.hidden;
import static org.patternfly.client.resources.Constants.true_;

class CollapseExpandBlock<T> {

    private final T source;
    private HandlerRegistration closeHandler;
    BiConsumer<T, Boolean> onToggle;

    CollapseExpandBlock(T source) {
        this.source = source;
    }

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
                onToggle.accept(source, true);
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
                onToggle.accept(source, false);
            }
        }
    }
}
