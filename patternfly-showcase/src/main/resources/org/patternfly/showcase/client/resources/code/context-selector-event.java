import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;

ContextSelector<String> contextSelector = new ContextSelector<>("Stage")
        .onToggle((cs, open) -> toast().add(info("Context selector " + (open ? "expanded" : "collapsed"))))
        .onSelect(stage -> toast().add(info("Stage selected").description(stage)))
        .add(asList("Development", "Staging", "QA", "Production"));