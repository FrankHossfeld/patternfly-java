import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.components.Button.button;

Button button = button("Click me").primary()
        .onClick(() -> toast().add(info("Hello")));