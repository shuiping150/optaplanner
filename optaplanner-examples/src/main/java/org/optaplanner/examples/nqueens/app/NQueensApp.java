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

package org.optaplanner.examples.nqueens.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicPhaseConfig;
import org.optaplanner.core.config.constructionheuristic.ConstructionHeuristicType;
import org.optaplanner.core.config.heuristic.selector.common.SelectionOrder;
import org.optaplanner.core.config.heuristic.selector.move.generic.ChangeMoveSelectorConfig;
import org.optaplanner.core.config.localsearch.LocalSearchPhaseConfig;
import org.optaplanner.core.config.localsearch.decider.acceptor.AcceptorConfig;
import org.optaplanner.core.config.phase.PhaseConfig;
import org.optaplanner.core.config.score.definition.ScoreDefinitionType;
import org.optaplanner.core.config.score.director.ScoreDirectorFactoryConfig;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.persistence.SolutionDao;
import org.optaplanner.examples.common.swingui.SolutionPanel;
import org.optaplanner.examples.nqueens.domain.NQueens;
import org.optaplanner.examples.nqueens.domain.Queen;
import org.optaplanner.examples.nqueens.persistence.NQueensDao;
import org.optaplanner.examples.nqueens.swingui.NQueensPanel;

/**
 * For an easy example, look at {@link NQueensHelloWorld} instead.
 */
public class NQueensApp extends CommonApp<NQueens> {

    public static final String SOLVER_CONFIG
            = "org/optaplanner/examples/nqueens/solver/nqueensSolverConfig.xml";

    public static void main(String[] args) {
        prepareSwingEnvironment();
        new NQueensApp().init();
    }

    public NQueensApp() {
        super("N queens",
                "Place queens on a chessboard.\n\n" +
                        "No 2 queens must be able to attack each other.",
                SOLVER_CONFIG,
                NQueensPanel.LOGO_PATH);
    }

    @Override
    protected Solver<NQueens> createSolver() {
        return createSolverByXml();
    }

    /**
     * Normal way to create a {@link Solver}.
     * @return never null
     */
    protected Solver<NQueens> createSolverByXml() {
        SolverFactory<NQueens> solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        return solverFactory.buildSolver();
    }

    /**
     * Unused alternative. Abnormal way to create a {@link Solver}.
     * <p>
     * Not recommended! It is recommended to use {@link #createSolverByXml()} instead.
     * @return never null
     */
    protected Solver<NQueens> createSolverByApi() {
        SolverFactory<NQueens> solverFactory = SolverFactory.createEmpty();
        SolverConfig solverConfig = solverFactory.getSolverConfig();

        solverConfig.setSolutionClass(NQueens.class);
        solverConfig.setEntityClassList(Collections.<Class<?>>singletonList(Queen.class));

        ScoreDirectorFactoryConfig scoreDirectorFactoryConfig = new ScoreDirectorFactoryConfig();
        scoreDirectorFactoryConfig.setScoreDefinitionType(ScoreDefinitionType.SIMPLE);
        scoreDirectorFactoryConfig.setScoreDrlList(
                Arrays.asList("org/optaplanner/examples/nqueens/solver/nQueensScoreRules.drl"));
        solverConfig.setScoreDirectorFactoryConfig(scoreDirectorFactoryConfig);

        TerminationConfig terminationConfig = new TerminationConfig();
        terminationConfig.setBestScoreLimit("0");
        solverConfig.setTerminationConfig(terminationConfig);
        List<PhaseConfig> phaseConfigList = new ArrayList<>();

        ConstructionHeuristicPhaseConfig constructionHeuristicPhaseConfig = new ConstructionHeuristicPhaseConfig();
        constructionHeuristicPhaseConfig.setConstructionHeuristicType(
                ConstructionHeuristicType.FIRST_FIT_DECREASING);
        phaseConfigList.add(constructionHeuristicPhaseConfig);

        LocalSearchPhaseConfig localSearchPhaseConfig = new LocalSearchPhaseConfig();
        ChangeMoveSelectorConfig changeMoveSelectorConfig = new ChangeMoveSelectorConfig();
        changeMoveSelectorConfig.setSelectionOrder(SelectionOrder.ORIGINAL);
        localSearchPhaseConfig.setMoveSelectorConfig(changeMoveSelectorConfig);
        AcceptorConfig acceptorConfig = new AcceptorConfig();
        acceptorConfig.setEntityTabuSize(5);
        localSearchPhaseConfig.setAcceptorConfig(acceptorConfig);
        phaseConfigList.add(localSearchPhaseConfig);

        solverConfig.setPhaseConfigList(phaseConfigList);
        return solverFactory.buildSolver();
    }

    @Override
    protected SolutionPanel createSolutionPanel() {
        return new NQueensPanel();
    }

    @Override
    protected SolutionDao createSolutionDao() {
        return new NQueensDao();
    }

}
