Alert warning1 = Alert.warning("Warning alert title")
        .description(span()
                .add("Warning alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
Alert warning2 = Alert.warning("Warning alert title")
        .closable();
Alert warning3 = Alert.warning("Warning alert title")
        .action("Action Button", () -> toast().add(Alert.info("Warning action button clicked.")));
Alert warning4 = Alert.warning("Warning alert title");