package org.patternfly.showcase.client.resources;

public interface Routes {

    String SHELL = "showcase";
    String SHELL_PATH = "/" + SHELL;
    String DOCUMENTATION = SHELL_PATH + "/documentation";

    String COMPONENT = DOCUMENTATION + "/component/:component";
    String CONTRIBUTE = SHELL_PATH + "/contribute";
    String GET_IN_TOUCH = SHELL_PATH + "/get-in-touch";
    String GET_STARTED = SHELL_PATH + "/get-started";
    String HOME = SHELL_PATH + "/home";

    static String hash(String route) {
        return "#" + route;
    }

    static String[] split(String route) {
        return route.replace(SHELL_PATH + "/", "").split("/");
    }
}
