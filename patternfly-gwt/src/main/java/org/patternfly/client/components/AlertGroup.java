package org.patternfly.client.components;

import java.util.HashMap;
import java.util.Map;

import elemental2.dom.Element;
import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.Constants;

import static elemental2.dom.DomGlobal.clearTimeout;
import static elemental2.dom.DomGlobal.document;
import static elemental2.dom.DomGlobal.setTimeout;
import static org.jboss.gwt.elemento.core.Elements.failSafeRemoveFromParent;
import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.jboss.gwt.elemento.core.Elements.uniqueId;
import static org.jboss.gwt.elemento.core.EventType.bind;
import static org.jboss.gwt.elemento.core.EventType.mouseout;
import static org.jboss.gwt.elemento.core.EventType.mouseover;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.alertGroup;

/**
 * PatternFly alert group component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/alertgroup">https://www.patternfly.org/v4/documentation/core/components/alertgroup</a>
 */
public class AlertGroup implements IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    private static AlertGroup toast;

    public static AlertGroup toast() {
        if (toast == null) {
            toast = new AlertGroup(DEFAULT_TIMEOUT);
            toast.element().classList.add(modifier(Constants.toast));
        }
        return toast;
    }

    public static AlertGroup embedded() {
        return new AlertGroup(0);
    }


    // ------------------------------------------------------ alert group instance

    private static final double DEFAULT_TIMEOUT = 8000; // ms

    private final double timeout;
    private final Map<String, Double> messageIds;
    private final HTMLElement root;

    private AlertGroup(double timeout) {
        this.timeout = timeout;
        this.messageIds = new HashMap<>();
        this.root = ul().css(component(alertGroup))
                .get();
    }

    @Override
    public HTMLElement element() {
        return root;
    }


    // ------------------------------------------------------ public API

    public AlertGroup add(Alert alert) {
        if (timeout > 100) {
            String id = uniqueId(Constants.alert);
            alert.element().id = id;
            startMessageTimeout(id);
            bind(alert.element(), mouseover, e1 -> stopMessageTimeout(id));
            bind(alert.element(), mouseout, e2 -> startMessageTimeout(id));


        }
        root.insertBefore(alert.element(), root.firstChild);
        return this;
    }


    // ------------------------------------------------------ internals

    private void startMessageTimeout(String id) {
        double timeoutHandle = setTimeout((o) -> remove(id), timeout);
        messageIds.put(id, timeoutHandle);
    }

    private void stopMessageTimeout(String id) {
        if (messageIds.containsKey(id)) {
            clearTimeout(messageIds.get(id));
            messageIds.remove(id);
        }
    }

    private void remove(String id) {
        Element element = document.getElementById(id);
        failSafeRemoveFromParent(element);
        messageIds.remove(id);
    }
}
