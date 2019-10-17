import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;

ContextSelector<String> contextSelector = new ContextSelector<>("Stage")
        .add(asList("Development", "Staging", "QA", "Production"));