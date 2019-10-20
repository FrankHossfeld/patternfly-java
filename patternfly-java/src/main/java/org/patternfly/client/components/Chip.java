package org.patternfly.client.components;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.core.builder.HtmlContentBuilder;
import org.patternfly.client.core.Callback;
import org.patternfly.client.resources.Constants;

import static org.jboss.gwt.elemento.core.Elements.button;
import static org.jboss.gwt.elemento.core.Elements.*;
import static org.jboss.gwt.elemento.core.EventType.click;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.button;
import static org.patternfly.client.resources.Constants.label;
import static org.patternfly.client.resources.Constants.*;

/**
 * PatternFly chip component.
 *
 * @see <a href="https://www.patternfly.org/v4/documentation/core/components/chip">https://www.patternfly.org/v4/documentation/core/components/chip</a>
 */
public class Chip implements IsElement<HTMLElement> {

    // ------------------------------------------------------ factory methods

    public static Chip text(String text) {
        return new Chip(div().get(), text, -1, false, false);
    }

    public static Chip text(String text, int count) {
        return new Chip(div().get(), text, count, false, false);
    }

    public static Chip readOnly(String text) {
        return new Chip(div().get(), text, -1, false, true);
    }

    public static Chip readOnly(String text, int count) {
        return new Chip(div().get(), text, count, false, true);
    }

    public static Chip overflow(String text) {
        return new Chip(div().get(), text, -1, true, false);
    }


    // ------------------------------------------------------ chip instance

    private final HTMLElement root;
    private final HTMLElement text;
    private Badge badge;

    private final int count;
    private final boolean overflow;
    private final boolean readOnly;
    private Callback callback;


    private Chip(HTMLElement element, String text, int count, boolean overflow, boolean readOnly) {
        this.root = element;
        this.count = count;
        this.overflow = overflow;
        this.readOnly = readOnly;

        root.classList.add(component(chip));
        if (readOnly) {
            root.classList.add(modifier(Constants.readOnly));
        } else if (overflow) {
            root.classList.add(modifier(Constants.overflow));
        }

        if (overflow) {
            root.appendChild(button().css(component(button), modifier(plain))
                    .on(click, e -> {
                        if (callback != null) {
                            callback.call();
                        }
                    })
                    .add(this.text = span().css(component(chip, Constants.text)).textContent(text).get())
                    .get());

        } else {
            String textId = uniqueId(chip, Constants.text);
            String buttonId = uniqueId(chip, Constants.button);

            HtmlContentBuilder<HTMLElement> builder = span().css(component(chip, Constants.text))
                    .id(textId)
                    .title(text);
            builder.add(text);
            if (count > 0) {
                badge = Badge.read(count);
                builder.add(badge);
            }
            root.appendChild(this.text = builder.get());
            if (!readOnly) {
                root.appendChild(button().css(component(Constants.button), modifier(plain))
                        .id(buttonId)
                        .aria(labelledBy, buttonId + " " + textId)
                        .aria(label, "Remove")
                        .on(click, e -> {
                            failSafeRemoveFromParent(element());
                            if (callback != null) {
                                callback.call();
                            }
                        })
                        .add(i().css(fas("times-circle")).aria(hidden, true_))
                        .get());
            }
        }
    }

    @Override
    public HTMLElement element() {
        return root;
    }

    Chip cloneAsLi() {
        return new Chip(li().get(), text.textContent, count, overflow, readOnly).onClose(callback);
    }


    // ------------------------------------------------------ public API

    /** Called after the chip has been removed. */
    public Chip onClose(Callback callback) {
        this.callback = callback;
        return this;
    }

    public Chip setText(String text) {
        this.text.textContent = text;
        return this;
    }

    public Badge badge() {
        return badge;
    }
}
