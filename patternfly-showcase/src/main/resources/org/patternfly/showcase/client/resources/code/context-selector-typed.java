import static java.util.Arrays.asList;
import static org.patternfly.client.components.Alert.info;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.resources.CSS.util;

static class Stage {

    final String name;
    final String url;
    final int nodes;

    Stage(String name, String url, int nodes) {
        this.name = name;
        this.url = url;
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return name;
    }
}

List<Stage> stages = asList(
        new Stage("Development", "http://localhost:8080", 2),
        new Stage("Staging", "https://staging.acme.org", 5),
        new Stage("QA", "https://qa.acme.org", 3),
        new Stage("Production", "https://acme.org", 12));

ContextSelector<Stage> contextSelector = new ContextSelector<>("Stage")
        .display((html, stage) -> html.css(util("justify-content-space-between"))
                .title(stage.url)
                .add(stage.name)
                .add(Badge.read(stage.nodes)))
        .onSelect(stage -> alert(
                "Selected stage '" + stage.name + "' at " + stage.url + " with " + stage.nodes + " nodes"))
        .add(stages);