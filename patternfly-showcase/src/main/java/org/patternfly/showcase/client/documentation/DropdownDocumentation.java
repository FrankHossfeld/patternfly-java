package org.patternfly.showcase.client.documentation;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.IsElement;
import org.jboss.gwt.elemento.template.DataElement;
import org.jboss.gwt.elemento.template.Templated;
import org.patternfly.client.components.Dropdown;
import org.patternfly.showcase.client.resources.Code;

import static org.jboss.gwt.elemento.core.Elements.div;

@Templated("dropdown.html#content")
abstract class DropdownDocumentation implements IsElement<HTMLElement> {

    static DropdownDocumentation create() {
        return new Templated_DropdownDocumentation();
    }

    @DataElement Demo simple = new Demo("Simple dropdown", Code.get().dropdownSimple().getText(),
            () -> div()
                    .add(Dropdown.<String>text("Dropdown")
                            .add("Link")
                            .add("Action")
                            .add("Disabled Link", true)
                            .add("Disabled Action", true)
                            .addSeparator()
                            .add("Separated Link")
                    )
                    .get());


    @DataElement Demo selection = new Demo("Dropdown with initial selection", Code.get().dropdownSelection().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo groups = new Demo("Dropdown with groups", Code.get().dropdownGroups().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo disabled = new Demo("Dropdown (disabled)", Code.get().dropdownDisabled().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo primary = new Demo("Dropdown (primary toggle)", Code.get().dropdownPrimary().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo right = new Demo("Dropdown (position right)", Code.get().dropdownRight().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo up = new Demo("Dropdown (direction up)", Code.get().dropdownUp().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo kebab = new Demo("Dropdown with kebab", Code.get().dropdownKebab().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo icon = new Demo("Dropdown (icon only)", Code.get().dropdownIcon().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo split = new Demo("Split button", Code.get().dropdownSplit().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo splitText = new Demo("Split button (with text)", Code.get().dropdownSplitText().getText(),
            () -> div().textContent("NYI")
                    .get());

    @DataElement Demo splitDisabled = new Demo("Split button (disabled)", Code.get().dropdownSplitDisabled().getText(),
            () -> div().textContent("NYI")
                    .get());
}
