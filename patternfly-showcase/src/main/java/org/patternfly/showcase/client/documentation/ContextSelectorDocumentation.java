package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Badge;
import org.patternfly.client.components.ContextSelector;
import org.patternfly.showcase.client.resources.Code;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.resources.CSS.util;

@Templated("context-selector.html#content")
abstract class ContextSelectorDocumentation implements IsElement<HTMLElement> {

    static ContextSelectorDocumentation create() {
        return new Templated_ContextSelectorDocumentation();
    }

    @DataElement Demo simple = new Demo("Simple context selector", Code.get().contextSelectorSimple().getText(),
            () -> div()
                    .add(new ContextSelector<String>("Stage")
                            .onToggle((cs, open) ->
                                    toast().add(info("Context selector " + (open ? "expanded" : "collapsed"))))
                            .onSelect(stage -> toast().add(info("Selected stage: '" + stage + "'")))
                            .add(asList("Development", "Staging", "QA", "Production")))
                    .get());

    @DataElement Demo typed = new Demo("Typed context selector", Code.get().contextSelectorTyped().getText(),
            () -> div()
                    .add(new ContextSelector<Stage>("Stage")
                            .display((html, stage) -> html.css(util("justify-content-space-between"))
                                    .title(stage.url)
                                    .add(stage.name)
                                    .add(Badge.read(stage.nodes)))
                            .onSelect(stage -> toast().add(info(
                                    "Selected stage '" + stage.name + "' at " + stage.url + " with " + stage.nodes + " nodes")))
                            .add(asList(new Stage("Development", "http://localhost:8080", 2),
                                    new Stage("Staging", "https://staging.acme.org", 5),
                                    new Stage("QA", "https://qa.acme.org", 3),
                                    new Stage("Production", "https://acme.org", 12))))
                    .get());


    static class Stage {

        final String name;
        final String url;
        final int nodes;

        Stage(String name, String url, int nodes) {
            this.name = name;
            this.url = url;
            this.nodes = nodes;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
