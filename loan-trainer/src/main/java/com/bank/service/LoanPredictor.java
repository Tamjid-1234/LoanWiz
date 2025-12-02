package com.bank.service;

import com.bank.model.Applicant;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.Evaluation;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.Filter;

import java.io.File;
import java.util.Random;

public class LoanPredictor {

    private RandomForest model;
    private Instances datasetStructure; // empty structure for creating new instances
    private Instances fullDataset;      // full dataset for evaluation

    // ====== Train the model ======
    public void trainModel(String csvFilePath) throws Exception {
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(csvFilePath));
        Instances data = loader.getDataSet();

        // ===== Convert last column (Loan_Status) to nominal =====
        NumericToNominal convert = new NumericToNominal();
        convert.setAttributeIndices("" + data.numAttributes()); // last column
        convert.setInputFormat(data);
        data = Filter.useFilter(data, convert);

        // Set last column as class
        data.setClassIndex(data.numAttributes() - 1);

        // Save full dataset for evaluation
        fullDataset = new Instances(data);

        // Save empty structure for creating new instances
        datasetStructure = new Instances(data, 0);

        // Train Random Forest
        model = new RandomForest();
        model.setNumIterations(100);
        model.setSeed(42);
        model.buildClassifier(data);

        System.out.println("Model trained successfully!");
    }

    // ====== Predict eligibility ======
    public boolean predict(Applicant applicant) throws Exception {
        if (model == null || datasetStructure == null) {
            throw new IllegalStateException("Model not trained. Call trainModel() first.");
        }

        double[] vals = applicant.toWekaArray();
        DenseInstance instance = new DenseInstance(1.0, vals);
        instance.setDataset(datasetStructure);

        double prediction = model.classifyInstance(instance);
        return prediction == 1.0; // 1 = eligible, 0 = not eligible
    }

    // ====== Save model ======
    public void saveModel(String filePath) throws Exception {
        weka.core.SerializationHelper.write(filePath, model);
        weka.core.SerializationHelper.write(filePath + ".structure", datasetStructure);
        weka.core.SerializationHelper.write(filePath + ".full", fullDataset);
    }

    // ====== Load model ======
    public void loadModel(String filePath) throws Exception {
        model = (RandomForest) weka.core.SerializationHelper.read(filePath);
        datasetStructure = (Instances) weka.core.SerializationHelper.read(filePath + ".structure");

        File fullFile = new File(filePath + ".full");
        if (fullFile.exists()) {
            fullDataset = (Instances) weka.core.SerializationHelper.read(filePath + ".full");
        } else {
            fullDataset = datasetStructure; // fallback for old models
        }
    }

    // ====== Evaluate model accuracy using cross-validation ======
    public double evaluateModel() throws Exception {
        if (model == null || fullDataset == null) {
            throw new IllegalStateException("Model not trained. Call trainModel() or loadModel() first.");
        }

        int folds = 10;
        if (fullDataset.numInstances() < folds) {
            folds = fullDataset.numInstances(); // avoid more folds than instances
        }

        Evaluation eval = new Evaluation(fullDataset);
        eval.crossValidateModel(model, fullDataset, folds, new Random(1));
        System.out.println(eval.toSummaryString("\n=== Evaluation Results ===\n", false));
        return eval.pctCorrect(); // returns accuracy in %
    }
}
