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

import static com.google.common.primitives.Ints.toArray;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.function.Function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.common.primitives.Ints;

class SelectionInfoTest {

    private static final Function<Integer, String> IDENTIFIER = String::valueOf;

    private SelectionInfo<Integer> single;
    private SelectionInfo<Integer> multi;

    @BeforeEach
    void setUp() {
        single = new SelectionInfo<>(IDENTIFIER, false);
        multi = new SelectionInfo<>(IDENTIFIER, true);
    }

    @Test
    void addSingle() {
        single.add("0", 0);
        assertSelection(single, 0);
        single.add("1", 1);
        assertSelection(single, 1);
        single.add("2", 2);
        assertSelection(single, 2);
    }

    @Test
    void addMulti() {
        multi.add("0", 0);
        assertSelection(multi, 0);
        multi.add("1", 1);
        assertSelection(multi, 0, 1);
        multi.add("2", 2);
        assertSelection(multi, 0, 1, 2);
    }

    @Test
    void removeSingle() {
        single.add("0", 0);
        assertSelection(single, 0);
        single.remove("0");
        assertNoSelection(single);
    }

    @Test
    void removeMulti() {
        multi.add("0", 0);
        multi.add("1", 1);
        multi.add("2", 2);
        assertSelection(multi, 0, 1, 2);
        multi.remove("2");
        assertSelection(multi, 0, 1);
        multi.remove("1");
        assertSelection(multi, 0);
        multi.remove("0");
        assertNoSelection(multi);
    }

    private void assertNoSelection(SelectionInfo<Integer> selectionInfo) {
        assertFalse(selectionInfo.hasSelection());
        assertTrue(selectionInfo.getSelection().isEmpty());
        assertNull(selectionInfo.getSingleSelection());
    }

    private void assertSelection(SelectionInfo<Integer> selectionInfo, int... expected) {
        assertTrue(selectionInfo.hasSelection());
        assertFalse(selectionInfo.getSelection().isEmpty());
        assertTrue(new HashSet<>(Ints.asList(expected)).contains(selectionInfo.getSingleSelection()));
        int[] actual = toArray(selectionInfo.getSelection());
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }

}
