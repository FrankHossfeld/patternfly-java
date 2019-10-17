package org.patternfly.client.components;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.resources.CSS;
import org.patternfly.client.resources.Constants;

import static java.lang.Math.abs;
import static org.jboss.gwt.elemento.core.Elements.insertBefore;
import static org.jboss.gwt.elemento.core.Elements.setVisible;
import static org.jboss.gwt.elemento.core.Elements.stream;
import static org.jboss.gwt.elemento.core.Elements.ul;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.Constants.chipGroup;

/**
 * PatternFly chip group component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/chipgroup">https://www.patternfly.org/v4/documentation/core/components/chipgroup</a>
 */
public class ChipGroup implements IsElement<HTMLElement> {

    private final HTMLElement root;
    private Chip overflow;

    private final int max;
    private boolean expanded;

    public ChipGroup() {
        this(-1);
    }

    public ChipGroup(int max) {
        this.max = max > 1 ? max : -1; // assert max > 1
        this.root = ul().css(component(chipGroup)).get();
        this.expanded = false;

        if (constrained()) {
            overflow = Chip.overflow("").cloneAsLi().onClose(c -> toggle());
            setVisible(overflow.element(), false);
            root.appendChild(overflow.element());
        }
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    public ChipGroup add(Chip chip) {
        Chip liChip = chip.cloneAsLi();

        if (constrained()) {
            liChip.onClose(c -> redraw()); // redraw after chip has been removed
            setVisible(liChip.element(), false);
            insertBefore(liChip.element(), overflow.element());
            redraw();
        } else {
            root.appendChild(liChip.element());
        }
        return this;
    }

    private void redraw() {
        AtomicInteger index = new AtomicInteger(0);
        chips().forEach(e -> setVisible(e, expanded || index.getAndIncrement() < max));

        int space = max - chipsCount();
        overflow.setText(expanded ? "Show Less" : abs(space) + " more");
        setVisible(overflow.element(), space < 0);
        if (expanded && space >= 0) {
            expanded = false;
        }
    }

    private void toggle() {
        expanded = !expanded;
        redraw();
    }

    private int chipsCount() {
        return (int) chips().count();
    }

    private Stream<HTMLElement> chips() {
        return stream(root).filter(e -> e.classList.contains(component(Constants.chip)) &&
                !e.classList.contains(CSS.modifier(Constants.overflow)));
    }

    private boolean constrained() {
        return max != -1;
    }
}
