package org.patternfly.showcase.client;

import com.github.nalukit.nalu.client.component.AbstractShell;
import com.github.nalukit.nalu.client.component.annotation.Shell;
import com.github.nalukit.nalu.client.event.RouterStateEvent;
import elemental2.dom.HTMLDivElement;
import org.patternfly.showcase.client.resources.Nav;
import org.patternfly.showcase.client.resources.Routes;

import static com.github.nalukit.nalu.client.event.RouterStateEvent.RouterState.ROUTING_DONE;
import static elemental2.dom.DomGlobal.document;
import static org.jboss.gwt.elemento.core.Elements.failSafeRemoveFromParent;
import static org.patternfly.client.components.AlertGroup.toast;
import static org.patternfly.client.components.Components.brand;
import static org.patternfly.client.components.Page.page;
import static org.patternfly.client.components.PageHeader.pageHeader;
import static org.patternfly.showcase.client.resources.Ids.ROOT_CONTAINER;
import static org.patternfly.showcase.client.resources.Routes.HOME;
import static org.patternfly.showcase.client.resources.Routes.hash;
import static org.patternfly.showcase.client.resources.Routes.split;

@Shell(Routes.SHELL)
public class ShowcaseShell extends AbstractShell<ShowcaseContext> {

    private HTMLDivElement pageElement;

    public void bind(ShellLoader shellLoader) {
        eventBus.addHandler(RouterStateEvent.TYPE, e -> {
            if (e.getState() == ROUTING_DONE) {
                String[] segments = split(e.getRoute());
                if (segments.length != 0) {
                    Nav.horizontal().setCurrent(segments[0]);
                }
            }
        });
        shellLoader.continueLoading();
    }

    @Override
    public void attachShell() {
        document.body.appendChild(pageElement = page(pageHeader(brand("./images/pf_logo_color.svg"), hash(HOME))
                .navigation(Nav.horizontal()), ROOT_CONTAINER).get());
        document.body.appendChild(toast().get());
    }

    @Override
    public void detachShell() {
        failSafeRemoveFromParent(pageElement);
        failSafeRemoveFromParent(toast().get());
    }
}