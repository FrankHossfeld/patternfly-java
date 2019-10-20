package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLImageElement;
import org.jboss.gwt.elemento.core.IsElement;

import static org.jboss.gwt.elemento.core.Elements.img;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.avatar;

/**
 * PatternFly avatar component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/react/components/avatar/">https://www.patternfly.org/v4/documentation/react/components/avatar</a>
 */
public class Avatar implements IsElement<HTMLElement> {

    private final HTMLImageElement root;

    public Avatar(String src, String alt) {
        root = img().css(component(avatar)).get();
        root.alt = alt;
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
