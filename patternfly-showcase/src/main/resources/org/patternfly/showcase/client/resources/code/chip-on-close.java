import static elemental2.dom.DomGlobal.alert;

Chip chip = Chip.text("Close Me").onClose(c -> alert("Goodbye"));