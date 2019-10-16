import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;

Chip chip = Chip.text("Close Me").onClose(c -> toast().add(info("Goodbye")))