package org.patternfly.showcase.client;

import static com.github.nalukit.nalu.client.application.annotation.Debug.LogLevel.DETAILED;

import org.patternfly.showcase.client.resources.Routes;

import com.github.nalukit.nalu.client.application.IsApplication;
import com.github.nalukit.nalu.client.application.annotation.Application;
import com.github.nalukit.nalu.client.application.annotation.Debug;
import com.github.nalukit.nalu.plugin.elemento.client.DefaultElementoLogger;

@Application(startRoute = Routes.HOME, context = ShowcaseContext.class)
@Debug(logLevel = DETAILED, logger = DefaultElementoLogger.class)
interface ShowcaseApplication extends IsApplication {

}
