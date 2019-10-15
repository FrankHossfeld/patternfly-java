package org.patternfly.showcase.client;

import com.github.nalukit.nalu.client.application.IsApplication;
import com.github.nalukit.nalu.client.application.annotation.Application;
import com.github.nalukit.nalu.client.application.annotation.Debug;
import com.github.nalukit.nalu.plugin.elemento.client.DefaultElemental2Logger;
import org.patternfly.showcase.client.resources.Routes;

import static com.github.nalukit.nalu.client.application.annotation.Debug.LogLevel.DETAILED;

@Application(startRoute = Routes.HOME,
        context = ShowcaseContext.class,
        routeError = Routes.HOME)
@Debug(logLevel = DETAILED, logger = DefaultElemental2Logger.class)
interface ShowcaseApplication extends IsApplication {
}