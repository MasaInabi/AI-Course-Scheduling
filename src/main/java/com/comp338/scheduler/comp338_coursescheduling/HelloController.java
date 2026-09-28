package com.comp338.scheduler.comp338_coursescheduling;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import java.util.List;

public class HelloController {

    @FXML
    private TableView<Gene> timetableTable;

    @FXML
    private TableColumn<Gene, String> courseColumn;

    @FXML
    private TableColumn<Gene, String> dayColumn;

    @FXML
    private TableColumn<Gene, String> timeColumn;

    @FXML
    private TableColumn<Gene, String> roomColumn;

    @FXML
    private TableColumn<Gene, String> instructorColumn;

    @FXML
    private Label fitnessLabel;

    @FXML
    private Label hardLabel;

    @FXML
    private Label softLabel;

    @FXML
    private Label penaltyLabel;

    @FXML
    private Label coursesLabel;

    @FXML
    private Label generationLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private Label statusLabel;

    @FXML
    private Label algorithmLabel;

    @FXML
    private TextField populationField;

    @FXML
    private TextField generationsField;

    @FXML
    private TextField mutationField;

    @FXML
    private TextField crossoverField;

    @FXML
    private LineChart<Number, Number> fitnessChart;

    @FXML
    private NumberAxis fitnessXAxis;

    @FXML
    private NumberAxis fitnessYAxis;

    @FXML
    private TableView<ComparisonResult> comparisonTable;

    @FXML
    private TableColumn<ComparisonResult, String>
            comparisonAlgorithmColumn;

    @FXML
    private TableColumn<ComparisonResult, Double>
            comparisonFitnessColumn;

    @FXML
    private TableColumn<ComparisonResult, Integer>
            comparisonHardColumn;

    @FXML
    private TableColumn<ComparisonResult, Integer>
            comparisonSoftColumn;

    @FXML
    private TableColumn<ComparisonResult, String>
            comparisonTimeColumn;

    private final ObservableList<ComparisonResult>
            comparisonResults =
            FXCollections.observableArrayList();

    // Experiment Table
    @FXML
    private TableView<ExperimentResult> experimentTable;

    @FXML
    private TableColumn<ExperimentResult, String>
            experimentTypeColumn;

    @FXML
    private TableColumn<ExperimentResult, String>
            experimentValueColumn;

    @FXML
    private TableColumn<ExperimentResult, Double>
            experimentFitnessColumn;

    @FXML
    private TableColumn<ExperimentResult, Integer>
            experimentHardColumn;

    @FXML
    private TableColumn<ExperimentResult, Integer>
            experimentSoftColumn;

    @FXML
    private TableColumn<ExperimentResult, Integer>
            experimentGenerationsColumn;

    @FXML
    private TableColumn<ExperimentResult, String>
            experimentTimeColumn;

    private final ObservableList<ExperimentResult>
            experimentResults =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        courseColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue()
                                .getCourse()
                                .getName()
                )
        );

        dayColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue()
                                .getTimeSlot()
                                .getDay()
                )
        );

        timeColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue()
                                .getTimeSlot()
                                .getTime()
                )
        );

        roomColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue()
                                .getRoom()
                                .getId()
                )
        );

        instructorColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue()
                                .getCourse()
                                .getInstructorId()
                )
        );

        comparisonAlgorithmColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getAlgorithm()
                )
        );

        comparisonFitnessColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getFitness()
                )
        );

        comparisonHardColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getHardViolations()
                )
        );

        comparisonSoftColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getSoftViolations()
                )
        );

        comparisonTimeColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        String.format(
                                "%.3f",
                                data.getValue().getExecutionTime()
                        )
                )
        );

        comparisonTable.setItems(comparisonResults);


        experimentTypeColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getExperiment()
                )
        );

        experimentValueColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        data.getValue().getParameterValue()
                )
        );

        experimentFitnessColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getFitness()
                )
        );

        experimentHardColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getHardViolations()
                )
        );

        experimentSoftColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(
                        data.getValue().getSoftViolations()
                )
        );

        experimentGenerationsColumn.setCellValueFactory(data ->
                new ReadOnlyObjectWrapper<>(data.getValue().getGenerationsUsed()));

        experimentTimeColumn.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(
                        String.format("%.3f", data.getValue().getExecutionTime())));

        experimentTable.setItems(experimentResults);

        fitnessChart.setAnimated(false);    }

    @FXML
    private void runGeneticAlgorithm() {

        try {

            System.out.println("=== GENETIC ALGORITHM STARTED ===");

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate =
                    Double.parseDouble(mutationField.getText());

            double crossoverRate = Double.parseDouble(crossoverField.getText());

            if (populationSize <= 0 || generations <= 0) {

                statusLabel.setText("Population and generations must be greater than 0.");

                return;
            }

            if (mutationRate < 0 || mutationRate > 1 || crossoverRate < 0 || crossoverRate > 1) {

                statusLabel.setText("Mutation and crossover rates must be between 0 and 1.");

                return;
            }

            DataManager dataManager = new DataManager();

            GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager);

            long startTime = System.nanoTime();

            Chromosome solution = ga.run();

            long endTime = System.nanoTime();

            double executionTime = (endTime - startTime) / 1_000_000.0;

            showSolution(solution, executionTime);

            generationLabel.setText("Generations Used: " + ga.getGenerationsUsed());

            updateComparison("Genetic Algorithm", solution, executionTime);

            fitnessChart.setTitle("GA Convergence Chart");

            fitnessXAxis.setLabel("Generation");

            fitnessYAxis.setAutoRanging(true);

            updateFitnessChart(ga.getFitnessHistory());

            fitnessChart.setVisible(true);

            algorithmLabel.setText("Algorithm: Genetic Algorithm");

            statusLabel.setText("Genetic Algorithm completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runHillClimbing() {

        try {

            int iterations =
                    Integer.parseInt(generationsField.getText());

            if (iterations <= 0) {

                statusLabel.setText("Iterations must be greater than 0.");

                return;
            }

            DataManager dataManager = new DataManager();

            HillClimbing hillClimbing = new HillClimbing(dataManager, iterations);

            long startTime = System.nanoTime();

            Chromosome solution = hillClimbing.run();

            long endTime = System.nanoTime();

            double executionTime = (endTime - startTime) / 1_000_000.0;

            showSolution(solution, executionTime);

            generationLabel.setText("Iterations Used: " + hillClimbing.getIterationsUsed());

            updateComparison("Hill Climbing", solution, executionTime);

            fitnessChart.setTitle("Hill Climbing Convergence Chart");

            fitnessXAxis.setLabel("Iteration");
            fitnessYAxis.setAutoRanging(false);
            fitnessYAxis.setLowerBound(0.0);
            fitnessYAxis.setUpperBound(0.01);
            fitnessYAxis.setTickUnit(0.001);

            updateFitnessChart(hillClimbing.getFitnessHistory());

            fitnessChart.setVisible(true);

            algorithmLabel.setText("Algorithm: Hill Climbing");

            statusLabel.setText("Hill Climbing completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter a valid number of iterations.");
        }
    }

    @FXML
    private void runPopulationExperiment() {

        try {

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate = Double.parseDouble(mutationField.getText());

            double crossoverRate = Double.parseDouble(crossoverField.getText());

            experimentResults.clear();

            int[] populationSizes = {20, 50, 100, 200};

            statusLabel.setText("Running population size experiment...");

            for (int populationSize : populationSizes) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(new ExperimentResult("Population Size", String.valueOf(populationSize), solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Population size experiment completed successfully.");

        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runMutationExperiment() {

        try {

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double crossoverRate =
                    Double.parseDouble(crossoverField.getText());

            experimentResults.clear();

            double[] mutationRates = {0.01, 0.05, 0.10, 0.20};

            statusLabel.setText("Running mutation rate experiment...");

            for (double mutationRate : mutationRates) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(new ExperimentResult("Mutation Rate", String.format("%.2f", mutationRate), solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Mutation rate experiment completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runCrossoverExperiment() {

        try {

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate = Double.parseDouble(mutationField.getText());

            experimentResults.clear();

            double[] crossoverRates = {0.60, 0.80, 0.90};

            statusLabel.setText("Running crossover rate experiment...");

            for (double crossoverRate : crossoverRates) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(new ExperimentResult("Crossover Rate", String.format("%.2f", crossoverRate), solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Crossover rate experiment completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runDatasetExperiment() {

        try {

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate = Double.parseDouble(mutationField.getText());

            double crossoverRate = Double.parseDouble(crossoverField.getText());

            experimentResults.clear();

            int[] datasetSizes = {10, 20, 30, 40, 50};

            statusLabel.setText("Running dataset size experiment...");

            for (int datasetSize : datasetSizes) {

                DataManager dataManager = new DataManager(datasetSize);

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(
                        new ExperimentResult("Dataset Size", datasetSize + " Courses", solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Dataset size experiment completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runSelectionExperiment() {

        try {

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate = Double.parseDouble(mutationField.getText());

            double crossoverRate = Double.parseDouble(crossoverField.getText());

            experimentResults.clear();

            String[] selectionMethods = {"TOURNAMENT", "ROULETTE"};

            statusLabel.setText("Running selection method experiment...");

            for (String selectionMethod : selectionMethods) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager, selectionMethod);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();
                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(new ExperimentResult("Selection Method", selectionMethod.equals("TOURNAMENT") ? "Tournament" : "Roulette Wheel", solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Tournament VS Roulette Wheel experiment completed successfully.");

        } catch (NumberFormatException e) {
            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    @FXML
    private void runCrossoverMethodExperiment() {

        try {

            int populationSize = Integer.parseInt(populationField.getText());

            int generations = Integer.parseInt(generationsField.getText());

            double mutationRate = Double.parseDouble(mutationField.getText());

            double crossoverRate = Double.parseDouble(crossoverField.getText());

            experimentResults.clear();

            String[] crossoverMethods = {"ONE_POINT", "TWO_POINT"};

            statusLabel.setText("Running crossover method experiment...");

            for (String crossoverMethod : crossoverMethods) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga = new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager, "TOURNAMENT", crossoverMethod);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(
                        new ExperimentResult("Crossover Method", crossoverMethod.equals("ONE_POINT") ? "One-Point" : "Two-Point", solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("One-Point VS Two-Point experiment completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }


    @FXML
    private void runMutationMethodExperiment() {

        try {

            int populationSize =
                    Integer.parseInt(populationField.getText());

            int generations =
                    Integer.parseInt(generationsField.getText());

            double mutationRate =
                    Double.parseDouble(mutationField.getText());

            double crossoverRate =
                    Double.parseDouble(crossoverField.getText()
                    );

            experimentResults.clear();

            String[] mutationMethods = {"RANDOM", "SWAP"};

            statusLabel.setText("Running mutation method experiment...");

            for (String mutationMethod : mutationMethods) {

                DataManager dataManager = new DataManager();

                GeneticAlgorithm ga =
                        new GeneticAlgorithm(populationSize, generations, mutationRate, crossoverRate, 3, dataManager, "TOURNAMENT", "ONE_POINT", mutationMethod);

                long startTime = System.nanoTime();

                Chromosome solution = ga.run();

                long endTime = System.nanoTime();

                double executionTime = (endTime - startTime) / 1_000_000.0;

                experimentResults.add(new ExperimentResult("Mutation Method", mutationMethod.equals("RANDOM") ? "Random" : "Swap", solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), ga.getGenerationsUsed(), executionTime));
            }

            statusLabel.setText("Random VS Swap mutation experiment completed successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid numeric values.");
        }
    }

    private void showSolution(Chromosome solution, double executionTime) {

        timetableTable.getItems().clear();

        timetableTable.getItems().addAll(solution.getGenes());

        fitnessLabel.setText("Fitness: " + solution.getFitness());

        hardLabel.setText("Hard Violations: " + solution.getHardViolations());

        softLabel.setText("Soft Violations: " + solution.getSoftViolations());

        penaltyLabel.setText("Final Penalty: " + solution.getPenalty());

        coursesLabel.setText("Courses Scheduled: " + solution.getGenes().size() + "/" + solution.getGenes().size());

        timeLabel.setText(String.format("Execution Time: %.3f ms", executionTime));
    }

    private void updateComparison(String algorithm, Chromosome solution, double executionTime) {

        comparisonResults.removeIf(
                result ->
                        result.getAlgorithm().equals(algorithm)
        );

        ComparisonResult result =
                new ComparisonResult(algorithm, solution.getFitness(), solution.getHardViolations(), solution.getSoftViolations(), executionTime);

        if (algorithm.equals("Genetic Algorithm")) {

            comparisonResults.add(0, result);

        } else {

            comparisonResults.add(result);
        }
    }

    private void updateFitnessChart(List<Double> fitnessHistory) {

        fitnessChart.getData().clear();

        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        series.setName("Best Fitness");

        for (int i = 0; i < fitnessHistory.size(); i++) {

            series.getData().add(new XYChart.Data<>(i, fitnessHistory.get(i)));
        }

        fitnessChart.getData().add(series);
        fitnessChart.applyCss();
        fitnessChart.layout();
    }
}