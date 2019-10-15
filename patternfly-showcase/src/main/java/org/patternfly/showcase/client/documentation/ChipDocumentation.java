package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Chip;
import org.patternfly.showcase.client.resources.Code;

import static elemental2.dom.DomGlobal.alert;
import static org.jboss.gwt.elemento.core.Elements.div;

@Templated("chip.html#content")
abstract class ChipDocumentation implements IsElement<HTMLElement> {

    static ChipDocumentation create() {
        return new Templated_ChipDocumentation();
    }

    @DataElement Demo variants = new Demo("Chip", Code.get().chip().getText(),
            () -> div()
                    .add(div().css("sc-demo__code-block")
                            .add(Chip.text("Just Text")))
                    .add(div().css("sc-demo__code-block")
                            .add(Chip.text("Count", 23)))
                    .add(div().css("sc-demo__code-block")
                            .add(Chip.readOnly("Readonly")))
                    .add(div().css("sc-demo__code-block")
                            .add(Chip.readOnly("RO Count", 42)))
                    .add(div()
                            .add(Chip.overflow("Overflow")))
                    .get());

    @DataElement Demo onClose = new Demo("Chip onClose", Code.get().chipOnClose().getText(),
            () -> div()
                    .add(Chip.text("Close Me").onClose(c -> alert("Goodbye")))
                    .get());
}
