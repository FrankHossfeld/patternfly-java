import static org.patternfly.client.resources.CSS.fas;

Button b01 = Button.button("Primary").primary());
Button b02 = Button.button("Secondary").secondary());
Button b03 = Button.button("Tertiary").tertiary());
Button b04 = Button.button("Danger").danger()));

Button b05 = Button.button("Primary icon").primary().prepend(fas("address-card")));
Button b06 = Button.button("Secondary icon").secondary().prepend(fas("address-card")));
Button b07 = Button.button("Tertiary icon").tertiary().prepend(fas("address-card")));
Button b08 = Button.button("Danger icon").danger().prepend(fas("address-card"))));

Button b09 = Button.link("Link"));
Button b10 = Button.link("Link icon").prepend(fas("calendar-plus")));
Button b11 = Button.icon(fas("chart-pie"), "Chart"));
Button b12 = Button.inline("Inline Link")));

Button b13 = Button.control("Control"));
Button b14 = Button.control("Control icon").prepend(fas("clock")));
Button b15 = Button.control(fas("code"), "Code")));