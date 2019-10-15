package org.patternfly.showcase.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface Code extends ClientBundle {

    Code INSTANCE = GWT.create(Code.class);

    static Code get() {
        return INSTANCE;
    }

    @Source("code/badge-unread.java")
    TextResource badgeUnread();

    @Source("code/badge-read.java")
    TextResource badgeRead();

    @Source("code/brand.java")
    TextResource brand();

    @Source("code/button-block.java")
    TextResource buttonBlock();

    @Source("code/button-event.java")
    TextResource buttonEvent();

    @Source("code/button-links.java")
    TextResource buttonLinks();

    @Source("code/button-states.java")
    TextResource buttonStates();

    @Source("code/button-types.java")
    TextResource buttonTypes();

    @Source("code/button-variations.java")
    TextResource buttonVariations();

    @Source("code/chip.java")
    TextResource chip();

    @Source("code/chip-group.java")
    TextResource chipGroup();

    @Source("code/chip-on-close.java")
    TextResource chipOnClose();

    @Source("code/chip-toolbar.java")
    TextResource chipToolbar();

    @Source("code/context-selector-simple.java")
    TextResource contextSelectorSimple();

    @Source("code/context-selector-typed.java")
    TextResource contextSelectorTyped();
}
