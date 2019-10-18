package org.patternfly.showcase.client.documentation;

import org.patternfly.client.components.Alert;
import org.patternfly.client.components.AlertGroup;
import org.patternfly.showcase.client.resources.Code;

import static java.util.Arrays.asList;
import static org.jboss.gwt.elemento.core.Elements.a;
import static org.jboss.gwt.elemento.core.Elements.div;
import static org.jboss.gwt.elemento.core.Elements.p;
import static org.jboss.gwt.elemento.core.Elements.span;
import static org.patternfly.client.components.AlertGroup.toast;

class AlertDocumentation extends ComponentDocumentation {

    AlertDocumentation() {
        super("Alert",
                p().textContent("Alerts are used to notify the user about a change in status or other event").get(),
                asList(
                        new Demo("Default alert", Code.get().alertDefault().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.default_("Default alert title")
                                                .description(span()
                                                        .add("Default alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                // TODO Why doesn't this compile?
                                                // .description(html -> html
                                                //         .add("Info alert description. ")
                                                //         .add(a("#").textContent("This is a link")))
                                                .closable())
                                        .add(Alert.default_("Default alert title")
                                                .closable())
                                        .add(Alert.default_("Default alert title")
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Default action button clicked."))))
                                        .add(Alert.default_("Default alert title"))
                                        .element()).get()),
                        new Demo("Info alert", Code.get().alertInfo().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.info("Info alert title")
                                                .description(span()
                                                        .add("Info alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                .closable())
                                        .add(Alert.info("Info alert title")
                                                .closable())
                                        .add(Alert.info("Info alert title")
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Info action button clicked."))))
                                        .add(Alert.info("Info alert title"))
                                        .element()).get()),
                        new Demo("Success alert", Code.get().alertSuccess().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.success("Success alert title")
                                                .description(span()
                                                        .add("Success alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                .closable())
                                        .add(Alert.success("Success alert title")
                                                .closable())
                                        .add(Alert.success("Success alert title")
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Success action button clicked."))))
                                        .add(Alert.success("Success alert title"))
                                        .element()).get()),
                        new Demo("Warning alert", Code.get().alertWarning().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.warning("Warning alert title")
                                                .description(span()
                                                        .add("Warning alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                .closable())
                                        .add(Alert.warning("Warning alert title")
                                                .closable())
                                        .add(Alert.warning("Warning alert title")
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Warning action button clicked."))))
                                        .add(Alert.warning("Warning alert title"))
                                        .element()).get()),
                        new Demo("Danger alert", Code.get().alertDanger().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.danger("Danger alert title")
                                                .description(span()
                                                        .add("Danger alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                .closable())
                                        .add(Alert.danger("Danger alert title")
                                                .closable())
                                        .add(Alert.danger("Danger alert title")
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Danger action button clicked."))))
                                        .add(Alert.danger("Danger alert title"))
                                        .element()).get()),
                        new Demo("Inline alert types", Code.get().alertInline().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.default_("Default inline alert title").inline())
                                        .add(Alert.info("Info inline alert title").inline())
                                        .add(Alert.success("Success inline alert title").inline())
                                        .add(Alert.warning("Warning inline alert title").inline())
                                        .add(Alert.danger("Danger inline alert title").inline())
                                        .element()).get()),
                        new Demo("Inline alert variations", Code.get().alertInlineVariations().getText(),
                                () -> div().add(AlertGroup.embedded()
                                        .add(Alert.success("Success alert title").inline()
                                                .description(span()
                                                        .add("Danger alert description. ")
                                                        .add(a("#").textContent("This is a link"))
                                                        .get())
                                                .closable())
                                        .add(Alert.success("Success alert title").inline()
                                                .closable())
                                        .add(Alert.success("Success alert title").inline()
                                                .action("Action Button", () -> toast().add(
                                                        Alert.info("Success action button clicked."))))
                                        .add(Alert.success("Success alert title").inline())
                                        .element()).get())
                ));
    }
}
