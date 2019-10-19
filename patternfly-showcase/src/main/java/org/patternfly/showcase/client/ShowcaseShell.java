package org.patternfly.showcase.client;

import com.github.nalukit.nalu.client.component.AbstractShell;
import com.github.nalukit.nalu.client.component.annotation.Shell;
import com.github.nalukit.nalu.client.event.RouterStateEvent;
import org.patternfly.client.components.AlertGroup;
import org.patternfly.client.components.Brand;
import org.patternfly.client.components.Page;
import org.patternfly.client.components.PageHeader;
import org.patternfly.showcase.client.resources.Nav;
import org.patternfly.showcase.client.resources.Routes;

import static com.github.nalukit.nalu.client.event.RouterStateEvent.RouterState.ROUTING_DONE;
import static elemental2.dom.DomGlobal.document;
import static org.patternfly.showcase.client.resources.Ids.ROOT_CONTAINER;
import static org.patternfly.showcase.client.resources.Routes.HOME;
import static org.patternfly.showcase.client.resources.Routes.hash;
import static org.patternfly.showcase.client.resources.Routes.split;

@Shell(Routes.SHELL)
public class ShowcaseShell extends AbstractShell<ShowcaseContext> {

    private PageHeader header;

    @Override
    public void attachShell() {
        eventBus.addHandler(RouterStateEvent.TYPE, e -> {
            if (e.getState() == ROUTING_DONE) {
                String[] segments = split(e.getRoute());
                if (segments.length != 0) {
                    Nav.horizontal().setCurrent(segments[0]);
                }
            }
        });

        this.header = PageHeader.create(new Brand("./images/pf_logo_color.svg"), hash(HOME))
                .navigation(Nav.horizontal());
        document.body.appendChild(Page.create(header, ROOT_CONTAINER).element());
        document.body.appendChild(AlertGroup.toast().element());
    }

    @Override
    public void detachShell() {
        this.header.element().remove();
    }
}