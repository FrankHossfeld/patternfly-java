package org.patternfly.client.components;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.content;

import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;

import elemental2.dom.HTMLDivElement;

/**
 * PatternFly avatar component.
 *
 * @see <a href=
 *      "https://www.patternfly.org/v4/documentation/react/components/avatar/">https://www.patternfly.org/v4/documentation/react/components/avatar</a>
 */
public class TextContent extends ElementBuilder<HTMLDivElement, TextContent>
        implements HtmlContent<HTMLDivElement, TextContent> {

    // ------------------------------------------------------ factory methods

    public static TextContent textContent() {
        return new TextContent();
    }

    // ------------------------------------------------------ textContent instance

    private TextContent() {
        super(div().css(component(content)).get());
    }

    @Override
    public TextContent that() {
        return this;
    }
}
