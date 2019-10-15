import static elemental2.dom.DomGlobal.alert;

Button button = Button.button("Click me").primary().onClick(b -> alert("Hello!"));