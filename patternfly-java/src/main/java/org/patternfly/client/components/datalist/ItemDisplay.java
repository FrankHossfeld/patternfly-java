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
package org.patternfly.client.components.datalist;

import elemental2.dom.HTMLElement;
import org.jboss.gwt.elemento.core.Elements;
import org.jboss.gwt.elemento.core.IsElement;
import org.patternfly.client.dataprovider.DataProvider;

/** Controls the layout of a data list item. */
public interface ItemDisplay<T> extends IsElement<HTMLElement> {

    /**
     * An unique id for this item. If you use a {@link DataProvider}
     * make sure to use the same IDs.
     *
     * @return n unique id from {@link Elements#uniqueId()}
     */
    default String id() {
        return Elements.uniqueId();
    }
}
