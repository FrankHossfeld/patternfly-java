package org.patternfly.showcase.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface Code extends ClientBundle {

    Code INSTANCE = GWT.create(Code.class);

    static Code get() {
        return INSTANCE;
    }

    @Source("code/alert-default.java")
    TextResource alertDefault();

    @Source("code/alert-info.java")
    TextResource alertInfo();

    @Source("code/alert-success.java")
    TextResource alertSuccess();

    @Source("code/alert-warning.java")
    TextResource alertWarning();

    @Source("code/alert-danger.java")
    TextResource alertDanger();

    @Source("code/alert-inline.java")
    TextResource alertInline();

    @Source("code/alert-inline-variations.java")
    TextResource alertInlineVariations();

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

    @Source("code/context-selector-event.java")
    TextResource contextSelectorEvent();

    @Source("code/context-selector-simple.java")
    TextResource contextSelectorSimple();

    @Source("code/context-selector-typed.java")
    TextResource contextSelectorTyped();

    @Source("code/dropdown-event.java")
    TextResource dropdownEvent();

    @Source("code/dropdown-groups.java")
    TextResource dropdownGroups();

    @Source("code/dropdown-icon.java")
    TextResource dropdownIcon();

    @Source("code/dropdown-kebab.java")
    TextResource dropdownKebab();

    @Source("code/dropdown-primary.java")
    TextResource dropdownPrimary();

    @Source("code/dropdown-right.java")
    TextResource dropdownRight();

    @Source("code/dropdown-simple.java")
    TextResource dropdownSimple();

    @Source("code/dropdown-split.java")
    TextResource dropdownSplit();

    @Source("code/dropdown-typed.java")
    TextResource dropdownTyped();

    @Source("code/dropdown-up.java")
    TextResource dropdownUp();

    @Source("code/label.java")
    TextResource label();
}
