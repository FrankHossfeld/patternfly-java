import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;

Button button = Button.button("Click me").primary()
        .onClick(() -> toast().add(info("Hello")));