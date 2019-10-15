package org.patternfly.client.core;

@FunctionalInterface
public interface ClickHandler<T> {

    void onClick(T source);
}
