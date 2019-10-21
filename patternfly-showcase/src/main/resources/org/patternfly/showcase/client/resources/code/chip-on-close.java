import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.components.Chip.chip;

Chip chip = chip("Close Me").onClose(() -> toast().add(info("Goodbye")))