Alert success1 = Alert.success("Success alert title")
        .description(span()
                .add("Success alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
Alert success2 = Alert.success("Success alert title")
        .closable();
Alert success3 = Alert.success("Success alert title")
        .action("Action Button", () -> toast().add(Alert.info("Success action button clicked.")));
Alert success4 = Alert.success("Success alert title");