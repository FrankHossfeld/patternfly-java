import static elemental2.dom.DomGlobal.alert;
import static elemental2.dom.DomGlobal.console;

ContextSelector<String> contextSelector = new ContextSelector<>("Stage")
        .onToggle((cs, open) -> console.info("context selector " + (open ? "expanded" : "collapsed")))
        .onSelect(stage -> alert("Selected stage: '" + stage + "'"))
        .add(asList("Development", "Staging", "QA", "Production"));