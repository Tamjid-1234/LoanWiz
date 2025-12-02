package com.bank;

import com.bank.model.Applicant;
import com.bank.service.LoanPredictor;

import java.io.File;
import java.util.Scanner;

public class App {
    static int gender;
    static int married;
    static int education;
    static int selfEmployed;
    static int creditHistory;
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("=== LOAN ELIGIBILITY PREDICTOR ===");

            System.out.print("Gender (Male/Female)");
            String genderInput=sc.nextLine();
            
            if(genderInput.equals("Male")){
                 gender = 1;
            }
            else{
                 gender=0;
            }
            

            System.out.print("Married ? (Yes/No): ");
            String marriedInput = sc.nextLine();

            if(marriedInput.equals("Yes")){
                 married = 1;
            }
            else{
                 married=0;
            }

            System.out.print("Number of Dependents (0,1,2,3): ");
            int dependents = sc.nextInt();
            sc.nextLine();

            System.out.print("Education (Graduate / Not Graduate): ");
            String educationInput = sc.nextLine();

            if(educationInput.equals("Graduate")){
                education = 1;
            }
            else{
                education=0;
            }


            System.out.print("Self Employed (Yes / No): ");
            String selfEmployedInput = sc.nextLine();
            
            if(selfEmployedInput.equals("Yes")){
                selfEmployed = 1;
            }
            else{
                selfEmployed=0;
            }

            System.out.print("Applicant Income: ");
            double applicantIncome = sc.nextDouble();
            sc.nextLine();

            System.out.print("Coapplicant Income: ");
            double coapplicantIncome = sc.nextDouble();
            sc.nextLine();

            System.out.print("Desired Loan Amount: ");
            double loanAmount = sc.nextDouble();
            sc.nextLine();

            System.out.print("Loan Amount Term (months): ");
            double loanAmountTerm = sc.nextDouble();
            sc.nextLine();

            System.out.print("Credit History (Good / Bad): ");
            String creditHistoryInput = sc.nextLine();

            if(creditHistoryInput.equals("Good")){
                creditHistory = 1;
            }
            else{
                creditHistory=0;
            }

            Applicant applicant = new Applicant(
                    gender, married, dependents,
                    education, selfEmployed,
                    applicantIncome, coapplicantIncome,
                    loanAmount, loanAmountTerm, creditHistory
            );

            LoanPredictor predictor = new LoanPredictor();

            File modelFile = new File("loan_model.model");
            if (modelFile.exists()) {
                predictor.loadModel("loan_model.model");
                System.out.println("Loaded pre-trained model.");
            } else {
                predictor.trainModel("data.csv");
                predictor.saveModel("loan_model.model");
                System.out.println("Model trained and saved.");
            }

            // ===== NEW: Evaluate and print model accuracy =====
            double accuracy = predictor.evaluateModel();
            System.out.println("Estimated model accuracy: " + accuracy + "%");

            boolean eligible = predictor.predict(applicant);
            System.out.println(eligible ? "✅ Eligible for loan" : "❌ Not eligible for loan");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
