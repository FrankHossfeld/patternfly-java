Alert info1 = Alert.info("Info alert title")
        .description(span()
                .add("Info alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
    Alert info2 = Alert.info("Info alert title")
        .closable();
    Alert info3 = Alert.info("Info alert title")
        .action("Action Button", () -> toast().add(Alert.info("Info action button clicked.")));
    Alert info4 = Alert.info("Info alert title");