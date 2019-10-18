Alert inline1 = Alert.success("Success alert title").inline()
        .description(span()
                .add("Success alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
Alert inline2 = Alert.success("Success alert title").inline()
        .closable();
Alert inline3 = Alert.success("Success alert title").inline()
        .action("Action Button", () -> toast().add(Alert.info("Success action button clicked.")));
Alert inline4 = Alert.success("Success alert title").inline();