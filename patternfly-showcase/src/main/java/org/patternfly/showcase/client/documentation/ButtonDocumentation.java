package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Button;
import org.patternfly.client.components.Button.Type;
import org.patternfly.showcase.client.resources.Code;

import static elemental2.dom.DomGlobal.alert;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.patternfly.client.resources.CSS.fas;

@Templated("button.html#content")
abstract class ButtonDocumentation implements IsElement<HTMLElement> {

    static ButtonDocumentation create() {
        return new Templated_ButtonDocumentation();
    }

    @DataElement Demo variations = new Demo("Button variations", Code.get().buttonVariations().getText(),
            () -> div()
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Primary").primary())
                            .add(" ")
                            .add(Button.button("Secondary").secondary())
                            .add(" ")
                            .add(Button.button("Tertiary").tertiary())
                            .add(" ")
                            .add(Button.button("Danger").danger()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Primary icon").primary().prepend(fas("address-card")))
                            .add(" ")
                            .add(Button.button("Secondary icon").secondary().prepend(fas("address-card")))
                            .add(" ")
                            .add(Button.button("Tertiary icon").tertiary().prepend(fas("address-card")))
                            .add(" ")
                            .add(Button.button("Danger icon").danger().prepend(fas("address-card"))))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.link("Link"))
                            .add(" ")
                            .add(Button.link("Link icon").prepend(fas("calendar-plus")))
                            .add(" ")
                            .add(Button.icon(fas("chart-pie"), "Chart"))
                            .add(" ")
                            .add(Button.inline("Inline Link")))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.control("Control"))
                            .add(" ")
                            .add(Button.control("Control icon").prepend(fas("clock")))
                            .add(" ")
                            .add(Button.control(fas("code"), "Code")))
                    .get());

    @DataElement Demo states = new Demo("Button states", Code.get().buttonStates().getText(),
            () -> div()
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Primary").primary())
                            .add(" ")
                            .add(Button.button("Primary focus").primary().focus())
                            .add(" ")
                            .add(Button.button("Primary active").primary().active())
                            .add(" ")
                            .add(Button.button("Primary disabled").primary().disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Secondary").secondary())
                            .add(" ")
                            .add(Button.button("Secondary focus").secondary().focus())
                            .add(" ")
                            .add(Button.button("Secondary active").secondary().active())
                            .add(" ")
                            .add(Button.button("Secondary disabled").secondary().disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Tertiary").tertiary())
                            .add(" ")
                            .add(Button.button("Tertiary focus").tertiary().focus())
                            .add(" ")
                            .add(Button.button("Tertiary active").tertiary().active())
                            .add(" ")
                            .add(Button.button("Tertiary disabled").tertiary().disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.button("Danger").danger())
                            .add(" ")
                            .add(Button.button("Danger focus").danger().focus())
                            .add(" ")
                            .add(Button.button("Danger active").danger().active())
                            .add(" ")
                            .add(Button.button("Danger disabled").danger().disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.link("Link"))
                            .add(" ")
                            .add(Button.link("Link focus").focus())
                            .add(" ")
                            .add(Button.link("Link active").active())
                            .add(" ")
                            .add(Button.link("Link disabled").disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.link("Link icon").prepend(fas("calendar-plus")))
                            .add(" ")
                            .add(Button.link("Link icon focus").prepend(fas("calendar-plus")).focus())
                            .add(" ")
                            .add(Button.link("Link icon active").prepend(fas("calendar-plus")).active())
                            .add(" ")
                            .add(Button.link("Link icon disabled").prepend(fas("calendar-plus")).disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.icon(fas("chart-pie"), "Chart"))
                            .add(" ")
                            .add(Button.icon(fas("chart-pie"), "Chart focus").focus())
                            .add(" ")
                            .add(Button.icon(fas("chart-pie"), "Chart active").active())
                            .add(" ")
                            .add(Button.icon(fas("chart-pie"), "Chart disabled").disable()))
                    .add(div().css("sc-demo__code-block")
                            .add(Button.control("Control"))
                            .add(" ")
                            .add(Button.control("Control focus").focus())
                            .add(" ")
                            .add(Button.control("Control active").active())
                            .add(" ")
                            .add(Button.control("Control expanded").expanded())
                            .add(" ")
                            .add(Button.control("Control disabled").disable()))
                    .get());

    @DataElement Demo links = new Demo("Links as buttons", Code.get().buttonLinks().getText(),
            () -> div()
                    .add(Button.link("Primary link to W3.org", "https://www.w3.org/").primary())
                    .add(" ")
                    .add(Button.link("Secondary link to W3.org", "https://www.w3.org/").secondary())
                    .add(" ")
                    .add(Button.link("Tertiary link to W3.org", "https://www.w3.org/", "_blank")
                            .prepend(fas("external-link-alt"))
                            .tertiary())
                    .get());

    @DataElement Demo block = new Demo("Button (block level)", Code.get().buttonBlock().getText(),
            () -> div()
                    .add(Button.button("Block level button").block())
                    .get());

    @DataElement Demo types = new Demo("Button types", Code.get().buttonTypes().getText(),
            () -> div()
                    .add(Button.button("Submit").type(Type.SUBMIT))
                    .add(" ")
                    .add(Button.button("Reset").type(Type.RESET))
                    .add(" ")
                    .add(Button.button("Default").type(Type.DEFAULT))
                    .get());

    @DataElement Demo event = new Demo("Button event", Code.get().buttonEvent().getText(),
            () -> div()
                    .add(Button.button("Click me").primary().onClick(b -> alert("Hello!")))
                    .get());
}
