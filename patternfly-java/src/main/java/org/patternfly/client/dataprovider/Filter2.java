package org.patternfly.client.dataprovider;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class Filter2<T> {

    private final String label;
    private boolean exclusive;
    private final Map<String, Predicate<T>> conditions;

    public Filter2(String label, boolean exclusive) {
        this.label = label;
        this.exclusive = exclusive;
        this.conditions = new HashMap<>();
    }

    public Filter2<T> condition(String label, Predicate<T> condition) {
        conditions.put(label, condition);
        return this;
    }

    public boolean isExclusive() {
        return exclusive;
    }

    public Map<String, Predicate<T>> getConditions() {
        return conditions;
    }
}
