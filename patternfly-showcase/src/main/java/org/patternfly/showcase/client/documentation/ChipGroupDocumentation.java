package org.patternfly.showcase.client.documentation;

import java.util.Random;

import org.patternfly.client.components.Button;
import org.patternfly.client.components.Chip;
import org.patternfly.client.components.ChipGroup;
import org.patternfly.client.components.ChipGroupToolbar;
import org.patternfly.showcase.client.resources.Code;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.jboss.gwt.elemento.core.Elements.uniqueId;
import static org.patternfly.client.resources.CSS.fas;

class ChipGroupDocumentation extends ComponentDocumentation {

    ChipGroupDocumentation() {
        super("Chip group",
                p().textContent(
                        "<p>A chip group is used to represent an attribute that has been assigned one or more values. An OR relationship is implied between values in the group. Chip groups are useful to express complex filters to a data set, for example.</p>")
                        .get(),
                asList(
                        new Demo("Chip group", Code.get().chipGroup().getText(),
                                () -> {
                                    ChipGroup group = new ChipGroup(5)
                                            .add(Chip.text(uniqueId()))
                                            .add(Chip.text(uniqueId()))
                                            .add(Chip.text(uniqueId()));
                                    return div()
                                            .add(div().css("sc-demo__code-block")
                                                    .add(group))
                                            .add(div().css("sc-demo__code-block")
                                                    .add(Button.link("Add cip").prepend(fas("plus-circle"))
                                                            .onClick(() -> group.add(Chip.text(uniqueId())))))
                                            .get();
                                }),
                        new Demo("Chip toolbar", Code.get().chipToolbar().getText(),
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
                                                    .add(Button.link("Add chip").prepend(fas("plus-circle"))
                                                            .onClick(() -> {
                                                                int i = new Random().nextInt(3);
                                                                groups[i].add(Chip.text(uniqueId()));
                                                            })))
                                            .get();
                                })
                ));
    }
}
