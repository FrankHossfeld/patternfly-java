package org.patternfly.showcase.client.documentation;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.resources.CSS.fas;

import org.patternfly.client.components.Dropdown;
import org.patternfly.showcase.client.resources.Code;

class DropdownDocumentation extends ComponentDocumentation {

    DropdownDocumentation() {
        super("Dropdown",
                p().textContent("Use a dropdown when you want to present a list of actions in a limited space.").get(),
                asList(
                        new Demo("Simple dropdown", Code.get().dropdownSimple().getText(),
                                () -> div()
                                        .add(Dropdown.<String> text("Dropdown")
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .add(" ")
                                        .add(Dropdown.<String> text("Dropdown").disable())
                                        .get()),
                        new Demo("Dropdown with groups", Code.get().dropdownGroups().getText(),
                                () -> div()
                                        .add(Dropdown.<String> text("Dropdown", true)
                                                .add("Link")
                                                .add("Action")
                                                .add("Group 2", "Group 2 link")
                                                .add("Group 2", "Group 2 action")
                                                .add("Group 3", "Group 3 link")
                                                .add("Group 3", "Group 3 action"))
                                        .get()),
                        new Demo("Split button", Code.get().dropdownSplit().getText(),
                                () -> div()
                                        .add(Dropdown.<String> split()
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .add(" ")
                                        .add(Dropdown.<String> split().disable())
                                        .add(" ")
                                        .add(Dropdown.<String> split("Dropdown")
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .add(" ")
                                        .add(Dropdown.<String> split("Dropdown").disable())
                                        .get()),
                        new Demo("Dropdown typed", Code.get().dropdownTyped().getText(),
                                () -> div()
                                        .add(Dropdown.<Color> text("Dropdown")
                                                .display((html, color) -> html
                                                        .style("background-color:" + color.bg + ";color:" + color.fg)
                                                        .textContent(color.name()))
                                                .add(Color.values()))
                                        .get()),
                        new Demo("Dropdown with kebab", Code.get().dropdownKebab().getText(),
                                () -> div()
                                        .add(Dropdown.<String> kebab()
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .add(" ")
                                        .add(Dropdown.<String> kebab().disable())
                                        .get()),
                        new Demo("Dropdown (icon only)", Code.get().dropdownIcon().getText(),
                                () -> div()
                                        .add(Dropdown.<String> icon(fas("th"))
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .add(" ")
                                        .add(Dropdown.<String> icon(fas("th")).disable())
                                        .get()),
                        new Demo("Dropdown (primary toggle)", Code.get().dropdownPrimary().getText(),
                                () -> div()
                                        .add(Dropdown.<String> text("Dropdown").primary()
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .get()),
                        new Demo("Dropdown (position right)", Code.get().dropdownRight().getText(),
                                () -> div()
                                        .add(Dropdown.<String> text("Dropdown").right()
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .get()),
                        new Demo("Dropdown (direction up)", Code.get().dropdownUp().getText(),
                                () -> div()
                                        .add(Dropdown.<String> text("Dropdown").up()
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link"))
                                        .get()),
                        new Demo("Dropdown events", Code.get().dropdownEvent().getText(),
                                () -> div()
                                        .add(Dropdown.<String> split("Dropdown")
                                                .add("Link")
                                                .add("Action")
                                                .add("Disabled Link", true)
                                                .add("Disabled Action", true)
                                                .addSeparator()
                                                .add("Separated Link")
                                                .onToggle(open -> toast().add(
                                                        info("Dropdown " + (open ? "expanded" : "collapsed"))))
                                                .onChange(value -> toast().add(
                                                        info("Dropdown " + (value ? "checked" : "not checked"))))
                                                .onSelect(item -> toast().add(info("Selected " + item))))
                                        .get())));
    }

    enum Color {

        Success("#92D400", "#151515"),
        Information("#73BCF7", "#151515"),
        Warning("#f0AB00", "#151515"),
        Danger("#C9190B", "#eeeeee");
        final String bg;

        final String fg;

        Color(String bg, String fg) {
            this.bg = bg;
            this.fg = fg;
        }

    }
}
