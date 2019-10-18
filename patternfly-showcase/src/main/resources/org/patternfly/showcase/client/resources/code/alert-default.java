Alert default1 = Alert.default_("Default alert title")
        .description(span()
                .add("Default alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
Alert default2 = Alert.default_("Default alert title")
        .closable();
Alert default3 = Alert.default_("Default alert title")
        .action("Action Button", () -> toast().add(Alert.info("Default action button clicked.")));
Alert default4 = Alert.default_("Default alert title");