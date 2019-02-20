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

package org.optaplanner.examples.aps.app;

import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.persistence.AbstractSolutionImporter;
import org.optaplanner.examples.aps.domain.Schedule;
import org.optaplanner.examples.aps.persistence.APSImporter;
import org.optaplanner.examples.aps.swingui.APSPanel;
import org.optaplanner.persistence.common.api.domain.solution.SolutionFileIO;
import org.optaplanner.persistence.xstream.impl.domain.solution.XStreamSolutionFileIO;

public class APSApp extends CommonApp<Schedule> {

    public static final String SOLVER_CONFIG
            = "org/optaplanner/examples/aps/solver/APSSolverConfig.xml";

    public static final String DATA_DIR_NAME = "projectjobscheduling";

    public static void main(String[] args) {
        prepareSwingEnvironment();
        new APSApp().init();
    }

    public APSApp() {
        super("Project job scheduling",
                "Official competition name:" +
                        " multi-mode resource-constrained multi-project scheduling problem (MRCMPSP)\n\n" +
                        "Schedule all jobs in time and execution mode.\n\n" +
                        "Minimize project delays.",
                SOLVER_CONFIG, DATA_DIR_NAME,
                APSPanel.LOGO_PATH);
    }

    @Override
    protected APSPanel createSolutionPanel() {
        return new APSPanel();
    }

    @Override
    public SolutionFileIO<Schedule> createSolutionFileIO() {
        return new XStreamSolutionFileIO<>(Schedule.class);
    }

    @Override
    protected AbstractSolutionImporter[] createSolutionImporters() {
        return new AbstractSolutionImporter[]{
                new APSImporter()
        };
    }

}
