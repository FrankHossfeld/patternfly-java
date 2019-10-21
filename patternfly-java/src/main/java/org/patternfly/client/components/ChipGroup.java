package org.patternfly.client.components;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.builder.ElementBuilder;
import org.jboss.gwt.elemento.core.builder.HtmlContent;
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
public class ChipGroup extends ElementBuilder<HTMLElement, ChipGroup>
        implements HtmlContent<HTMLElement, ChipGroup> {

    private final int max;
    private boolean expanded;
    private Chip overflow;

    public ChipGroup() {
        this(-1);
    }

    public ChipGroup(int max) {
        super(ul().css(component(chipGroup)).get());

        this.max = max > 1 ? max : -1; // assert max > 1
        this.expanded = false;

        if (constrained()) {
            overflow = Chip.overflow("").cloneAsLi().onClose(this::toggle);
            element.appendChild(overflow.get()); // do not use add(overflow)!
            setVisible(overflow.get(), false);
        }
    }

    @Override
    public ChipGroup that() {
        return this;
    }

    public ChipGroup add(Chip chip) {
        Chip liChip = chip.cloneAsLi();

        if (constrained()) {
            liChip.onClose(this::redraw); // redraw after chip has been removed
            setVisible(liChip.get(), false);
            insertBefore(liChip.get(), overflow.get());
            redraw();
        } else {
            element.appendChild(liChip.get());
        }
        return this;
    }

    private void redraw() {
        AtomicInteger index = new AtomicInteger(0);
        chips().forEach(e -> setVisible(e, expanded || index.getAndIncrement() < max));

        int space = max - chipsCount();
        overflow.text(expanded ? "Show Less" : abs(space) + " more");
        setVisible(overflow.get(), space < 0);
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
        return stream(element).filter(e -> e.classList.contains(component(Constants.chip)) &&
                !e.classList.contains(CSS.modifier(Constants.overflow)));
    }

    private boolean constrained() {
        return max != -1;
    }
}
