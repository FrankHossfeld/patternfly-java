Alert danger1 = Alert.danger("Danger alert title")
        .description(span()
                .add("Danger alert description. ")
                .add(a("#").textContent("This is a link"))
                .get())
        .closable();
Alert danger2 = Alert.danger("Danger alert title")
        .closable();
Alert danger3 = Alert.danger("Danger alert title")
        .action("Action Button", () -> toast().add(Alert.info("Danger action button clicked.")));
Alert danger4 = Alert.danger("Danger alert title");