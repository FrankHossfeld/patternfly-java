package org.patternfly.showcase.client.documentation;

import java.util.Random;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Button;
import org.patternfly.client.components.Chip;
import org.patternfly.client.components.ChipGroup;
import org.patternfly.client.components.ChipGroupToolbar;
import org.patternfly.showcase.client.resources.Code;

import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.uniqueId;
import static org.patternfly.client.resources.CSS.fas;

@Templated("chip-group.html#content")
abstract class ChipGroupDocumentation implements IsElement<HTMLElement> {

    static ChipGroupDocumentation create() {
        return new Templated_ChipGroupDocumentation();
    }

    @DataElement Demo group = new Demo("Chip group", Code.get().chipGroup().getText(),
            () -> {
                ChipGroup group = new ChipGroup(5)
                        .add(Chip.text(uniqueId()))
                        .add(Chip.text(uniqueId()))
                        .add(Chip.text(uniqueId()));
                return div()
                        .add(div().css("sc-demo__code-block")
                                .add(group))
                        .add(div().css("sc-demo__code-block")
                                .add(Button.link("Add").prepend(fas("plus-circle"))
                                        .onClick(c -> group.add(Chip.text(uniqueId())))))
                        .get();
            });

    @DataElement Demo toolbar = new Demo("Chip toolbar", Code.get().chipToolbar().getText(),
            () -> {
                ChipGroup[] groups = new ChipGroup[]{
                        new ChipGroup(3),
                        new ChipGroup(4),
                        new ChipGroup()
                };

                ChipGroupToolbar toolbar = new ChipGroupToolbar()
                        .add("Max 3", groups[0])
                        .add("Max 4", groups[1])
                        .add("Unlimited", groups[2]);

                return div()
                        .add(div().css("sc-demo__code-block")
                                .add(toolbar))
                        .add(div().css("sc-demo__code-block")
                                .add(Button.link("Add").prepend(fas("plus-circle"))
                                        .onClick(c -> {
                                            int i = new Random().nextInt(3);
                                            groups[i].add(Chip.text(uniqueId()));
                                        })))
                        .get();
            });
}
