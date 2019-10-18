package org.patternfly.showcase.client.documentation;

import java.util.function.Supplier;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.components.Button;

import static org.jboss.gwt.elemento.core.Elements.*;
import static org.patternfly.client.resources.CSS.component;
import static org.patternfly.client.resources.CSS.fas;
import static org.patternfly.client.resources.CSS.modifier;
import static org.patternfly.client.resources.Constants.lg;
import static org.patternfly.client.resources.Constants.title;

class Demo implements IsElement<HTMLElement> {

    private final Supplier<HTMLElement> demoSupplier;
    private final HTMLElement code;
    private final HTMLElement root;
    private HTMLElement demo;

    Demo(String header, String code, Supplier<HTMLElement> demo) {
        demoSupplier = demo;
        root = section().css("sc-demo")
                .add(h(3, header).css("sc-demo__heading", component(title), modifier(lg)))
                .add(this.demo = demo.get())
                .add(div().css("sc-demo__toolbar")
                        .add(Button.icon(fas("code"), "Toggle code").onClick(b -> toggleCode()))
                        .add(Button.icon(fas("copy"), "Copy code").onClick(b -> copyCode()))
                        .add(Button.icon(fas("undo"), "Undo changes").onClick(b -> undo())))
                .add(this.code = div().css("sc-demo__code")
                        .add(pre().css("prettyprint").textContent(code))
                        .get())
                .get();
        this.demo.classList.add("sc-demo__example");
        setVisible(this.code, false);
    }

    private void toggleCode() {
        setVisible(code, !isVisible(code));
    }

    private void copyCode() {
    }

    private void undo() {
        failSafeRemoveFromParent(demo);
        demo = demoSupplier.get();
        demo.classList.add("sc-demo__example");
        insertBefore(demo, root.querySelector(".sc-demo__toolbar"));
    }

    @Override
    public HTMLElement element() {
        return root;
    }
}
