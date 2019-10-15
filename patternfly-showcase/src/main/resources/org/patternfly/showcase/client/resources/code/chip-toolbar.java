import static org.jboss.gwt.elemento.core.Elements.uniqueId;
import static org.patternfly.client.resources.CSS.fas;

ChipGroup[] groups = new ChipGroup[]{
        new ChipGroup(3),
        new ChipGroup(4),
        new ChipGroup()
};

ChipGroupToolbar toolbar = new ChipGroupToolbar()
        .add("Max 3", groups[0])
        .add("Max 4", groups[1])
        .add("Unlimited", groups[2]);

Button add = Button.link("Add").prepend(fas("plus-circle"))
        .onClick(c -> {
            int i = new Random().nextInt(3);
            groups[i].add(Chip.text(uniqueId()));
        });