package com.bank.model;

public class Applicant {
    private int gender;         // 1 = Male, 0 = Female
    private int married;        // 1 = Yes, 0 = No
    private int dependents;     // 0,1,2,3+
    private int education;      // 1 = Graduate, 0 = Not Graduate
    private int selfEmployed;   // 1 = Yes, 0 = No
    private double applicantIncome;
    private double coapplicantIncome;
    private double loanAmount;
    private double loanAmountTerm;
    private int creditHistory;  // 1 = Good, 0 = Bad

    public Applicant(int gender, int married, int dependents,
                     int education, int selfEmployed,
                     double applicantIncome, double coapplicantIncome,
                     double loanAmount, double loanAmountTerm, int creditHistory) {
        this.gender = gender;
        this.married = married;
        this.dependents = dependents;
        this.education = education;
        this.selfEmployed = selfEmployed;
        this.applicantIncome = applicantIncome;
        this.coapplicantIncome = coapplicantIncome;
        this.loanAmount = loanAmount;
        this.loanAmountTerm = loanAmountTerm;
        this.creditHistory = creditHistory;
    }

    // Getters (if needed)
    public double[] toWekaArray() {
        return new double[]{
                gender,
                married,
                dependents,
                education,
                selfEmployed,
                applicantIncome,
                coapplicantIncome,
                loanAmount,
                loanAmountTerm,
                creditHistory
        };
    }
}
