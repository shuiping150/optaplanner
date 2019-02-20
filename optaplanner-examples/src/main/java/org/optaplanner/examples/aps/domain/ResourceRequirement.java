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

package org.optaplanner.examples.aps.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.projectjobscheduling.domain.ExecutionMode;
import org.optaplanner.examples.projectjobscheduling.domain.resource.Resource;

/**
 * 资源需求
 */
@XStreamAlias("PjsResourceRequirement")
public class ResourceRequirement extends AbstractPersistable {

    /**
     * 所属资源组合解决方案
     */
    private org.optaplanner.examples.projectjobscheduling.domain.ExecutionMode executionMode;
    /**
     * 所需资源
     */
    private Resource resource;
    /**
     * 所需要能力
     */
    private int requirement;

    public org.optaplanner.examples.projectjobscheduling.domain.ExecutionMode getExecutionMode() {
        return executionMode;
    }

    public void setExecutionMode(ExecutionMode executionMode) {
        this.executionMode = executionMode;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getRequirement() {
        return requirement;
    }

    public void setRequirement(int requirement) {
        this.requirement = requirement;
    }

    // ************************************************************************
    // Complex methods
    // ************************************************************************

    public boolean isResourceRenewable() {
        return resource.isRenewable();
    }

}
