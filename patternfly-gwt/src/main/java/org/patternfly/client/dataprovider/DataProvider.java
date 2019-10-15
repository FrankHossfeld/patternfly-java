/*
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.patternfly.client.dataprovider;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import org.patternfly.client.core.SelectHandler;

import static java.lang.Math.min;
import static java.util.Collections.emptyList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Holds items and state for components like data lists and tables.
 * Modifications of items and state are reflected in the bound displays.
 */
public class DataProvider<T> {

    private final Function<T, String> identifier;
    private final PageInfo pageInfo;
    private final SelectionInfo<T> selectionInfo;
    private final Map<String, T> allItems;
    private final Map<String, FilterValue<T>> filterValues;
    private final List<Display<T>> displays;
    private List<SelectHandler<T>> selectHandler;
    private Map<String, T> filteredItems;
    private Map<String, T> visibleItems;
    private Comparator<T> comparator;

    public DataProvider(Function<T, String> identifier, boolean multiSelect) {
        this(identifier, multiSelect, PageInfo.DEFAULT_PAGE_SIZE);
    }

    public DataProvider(Function<T, String> identifier, boolean multiSelect, int pageSize) {
        this.identifier = identifier;
        this.pageInfo = new PageInfo(pageSize);
        this.selectionInfo = new SelectionInfo<>(identifier, multiSelect);
        this.allItems = new LinkedHashMap<>();
        this.filteredItems = new LinkedHashMap<>();
        this.visibleItems = new LinkedHashMap<>();
        this.filterValues = new HashMap<>();
        this.selectHandler = new ArrayList<>();
        this.displays = new ArrayList<>();

        reset();
    }


    // ------------------------------------------------------ items

    /** Replaces the items, resets the paging and selection and applies the current filter and sort order. */
    public void update(T[] items) {
        reset();
        for (T item : items) {
            allItems.put(getId(item), item);
        }
        finishUpdate();
    }

    /** Replaces the items, resets the paging and selection and applies the current filter and sort order. */
    public void update(Iterable<T> items) {
        reset();
        for (T item : items) {
            allItems.put(getId(item), item);
        }
        finishUpdate();
    }

    private void finishUpdate() {
        applyFilterSortAndPaging();
        showItems();
        updateSelection();
    }

    public boolean contains(T item) {
        return allItems.containsKey(identifier.apply(item));
    }

    public boolean isVisible(T item) {
        return visibleItems.containsKey(identifier.apply(item));
    }

    public String getId(T item) {
        return identifier.apply(item);
    }

    public Iterable<T> getAllItems() {
        return allItems.values();
    }

    public Iterable<T> getFilteredItems() {
        return filteredItems.values();
    }

    public Iterable<T> getVisibleItems() {
        return visibleItems.values();
    }

    private void reset() {
        allItems.clear();
        pageInfo.reset();
        selectionInfo.reset();
    }

    private void applyFilterSortAndPaging() {
        Stream<T> stream = allItems.values().stream();
        if (!filterValues.isEmpty()) {
            Predicate<T> predicate = null;
            for (FilterValue<T> filterValue : filterValues.values()) {
                if (predicate == null) {
                    predicate = i -> filterValue.getFilter().test(i, filterValue.getValue());
                } else {
                    predicate = predicate.and(i -> filterValue.getFilter().test(i, filterValue.getValue()));
                }
            }
            stream = stream.filter(predicate);
        }
        if (comparator != null) {
            stream = stream.sorted(comparator);
        }
        List<T> values = stream.collect(toList());
        if (values.size() > pageInfo.getPageSize()) {
            filteredItems = values.stream().collect(toLinkedMap(identifier, identity()));
            values = paged(values);
            visibleItems = values.stream().collect(toLinkedMap(identifier, identity()));
        } else {
            filteredItems = visibleItems = values.stream().collect(toLinkedMap(identifier, identity()));
        }
        pageInfo.setTotal(filteredItems.size()); // total first!
        pageInfo.setVisible(visibleItems.size());
    }

    private Collector<T, ?, Map<String, T>> toLinkedMap(Function<? super T, ? extends String> keyMapper,
            Function<? super T, ? extends T> valueMapper) {
        return toMap(keyMapper, valueMapper,
                (u, v) -> {
                    throw new IllegalStateException("Duplicate key " + u); //NON-NLS
                },
                LinkedHashMap::new);
    }


    // ------------------------------------------------------ selection

    public void onSelect(SelectHandler<T> selectHandler) {
        this.selectHandler.add(selectHandler);
    }

    /** Selects all items if {@code multiSelect == true}. Does not fire selection events */
    public void selectAll() {
        if (selectionInfo.isMultiSelect()) {
            filteredItems.forEach((id, item) -> selectInternal(id, item, true));
            updateSelection();
        }
    }

    /** Selects all visible items if {@code multiSelect == true}. Does not fire selection events */
    public void selectVisible() {
        if (selectionInfo.isMultiSelect()) {
            visibleItems.forEach((id, item) -> selectInternal(id, item, true));
            updateSelection();
        }
    }

    /** Clears the selection for all items */
    public void clearAllSelection() {
        if (selectionInfo.hasSelection()) {
            filteredItems.forEach((id, item) -> selectInternal(id, item, false));
            updateSelection();
        }
    }

    /** Clears the selection for all visible items */
    public void clearVisibleSelection() {
        if (selectionInfo.hasSelection()) {
            visibleItems.forEach((id, item) -> selectInternal(id, item, false));
            updateSelection();
        }
    }

    /** (De)selects the specified item and fires a selection event if {@code select == true} */
    public void select(T item, boolean select) {
        selectInternal(getId(item), item, select);
        if (select) {
            fireSelection(item);
        }
        updateSelection();
    }

    public SelectionInfo<T> getSelectionInfo() {
        return selectionInfo;
    }

    private void selectInternal(String id, T item, boolean select) {
        if (select) {
            selectionInfo.add(id, item);
        } else {
            selectionInfo.remove(id);
        }
    }

    private void fireSelection(T item) {
        for (SelectHandler<T> handler : selectHandler) {
            handler.onSelect(item);
        }
    }


    // ------------------------------------------------------ filter

    public void addFilter(String name, FilterValue<T> filter) {
        filterValues.put(name, filter);
        applyFilterSortAndPaging();
        showItems();
        updateSelection();
    }

    public void removeFilter(String name) {
        if (filterValues.containsKey(name)) {
            filterValues.remove(name);
            applyFilterSortAndPaging();
            showItems();
            updateSelection();
        }
    }

    public void clearFilters() {
        if (!filterValues.isEmpty()) {
            filterValues.clear();
            applyFilterSortAndPaging();
            showItems();
            updateSelection();
        }
    }

    @SuppressWarnings("unchecked")
    public FilterValue<T> getFilter(String name) {
        return filterValues.getOrDefault(name, FilterValue.EMPTY);
    }

    public boolean hasFilters() {
        return !filterValues.isEmpty();
    }


    // ------------------------------------------------------ sort

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        applyFilterSortAndPaging();
        showItems();
        updateSelection();
    }

    public Comparator<T> getComparator() {
        return comparator;
    }


    // ------------------------------------------------------ paging

    public void setPageSize(int pageSize) {
        int oldPageSize = pageInfo.getPageSize();
        pageInfo.setPageSize(pageSize);
        if (oldPageSize != pageInfo.getPageSize()) {
            applyFilterSortAndPaging();
            showItems();
            updateSelection();
        }
    }

    public void gotoFirstPage() {
        gotoPage(0);
    }

    public void gotoPreviousPage() {
        gotoPage(pageInfo.getPage() - 1);
    }

    public void gotoNextPage() {
        gotoPage(pageInfo.getPage() + 1);
    }

    public void gotoLastPage() {
        gotoPage(pageInfo.getPages() - 1);
    }

    public void gotoPage(int page) {
        int oldPage = pageInfo.getPage();
        pageInfo.setPage(page);
        if (oldPage != pageInfo.getPage()) {
            applyFilterSortAndPaging();
            showItems();
            updateSelection();
        }
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    private List<T> paged(List<T> values) {
        List<List<T>> pages = partition(values, pageInfo.getPageSize());
        return pages.get(min(pageInfo.getPage(), pages.size() - 1));
    }

    private List<List<T>> partition(List<T> list, int size) {
        if (list != null) {
            if (size > 0) {
                return new Partition<>(list, size);
            }
        }
        return emptyList();
    }


    // ------------------------------------------------------ displays

    public void addDisplay(Display<T> display) {
        displays.add(display);
    }

    private void showItems() {
        for (Display<T> display : displays) {
            display.showItems(visibleItems.values(), pageInfo);
        }
    }

    private void updateSelection() {
        for (Display<T> display : displays) {
            display.updateSelection(selectionInfo);
        }
    }


    // ------------------------------------------------------ inner classes


    private static class Partition<T> extends AbstractList<List<T>> {

        final List<T> list;
        final int size;

        Partition(List<T> list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public List<T> get(int index) {
            checkElementIndex(index, size());
            int start = index * size;
            int end = Math.min(start + size, list.size());
            return list.subList(start, end);
        }

        @Override
        public int size() {
            return divide(list.size(), size);
        }

        @Override
        public boolean isEmpty() {
            return list.isEmpty();
        }

        private void checkElementIndex(int index, int size) {
            if (index < 0) {
                throw new IndexOutOfBoundsException("Index " + index + " must not be negative");
            } else if (index >= size) {
                throw new IndexOutOfBoundsException("Index (" + index + ") must be less than size (" + size + ")");
            }
        }

        private static int divide(int p, int q) {
            if (q == 0) {
                throw new ArithmeticException("/ by zero");
            }

            int div = p / q;
            int rem = p - q * div; // equal to p % q
            if (rem == 0) {
                return div;
            }

            int signum = 1 | ((p ^ q) >> (Integer.SIZE - 1));
            boolean increment = signum > 0;
            return increment ? div + signum : div;
        }
    }
}
