/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.aps.domain.resource;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamInclude;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.aps.domain.resource.GlobalResource;
import org.optaplanner.examples.aps.domain.resource.LocalResource;

/**
 * 资源类
 */
@XStreamAlias("PjsResource")
@XStreamInclude({
        GlobalResource.class,
        LocalResource.class
})
public abstract class Resource extends AbstractPersistable {

    /**
     * 资源承受能力
     */
    private int capacity;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    /**
     * @return 是否可再生(重复利用)
     */
    public abstract boolean isRenewable();

}
