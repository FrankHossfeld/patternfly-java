import static org.jboss.gwt.elemento.core.Elements.uniqueId;
import static org.patternfly.client.resources.CSS.fas;

ChipGroup group = new ChipGroup(5)
        .add(Chip.text(uniqueId()))
        .add(Chip.text(uniqueId()))
        .add(Chip.text(uniqueId()));

Button add = Button.link("Add").prepend(fas("plus-circle"))
        .onClick(c -> group.add(Chip.text(uniqueId())));