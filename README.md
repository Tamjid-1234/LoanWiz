# LoanWiz – Your friendly, loan-predicting wizard!

LoanWiz is a Java-based machine learning project that predicts loan eligibility based on applicant financial and personal data. Using the Random Forest algorithm from Weka, it trains on historical loan application data and allows users to check eligibility for new applicants. The project also evaluates model performance using cross-validation metrics.

# Features

Train a Random Forest model on historical loan data.

Load a pre-trained model for quick predictions.

Input applicant details via a user-friendly command-line interface.

Evaluate model accuracy, correlation, and error metrics.

Modular structure allows applying the same workflow to other classification datasets.

Handles real-world financial attributes like income, loan amount, credit history, etc.

# Technologies Used

Java 21 – Core application logic

Weka 3.8.6 – Machine learning algorithms and dataset handling

Maven – Dependency management and project build

# Installation

# Clone the repository:

git clone https://github.com/Tamjid-1234/LoanWiz.git
cd LoanWiz


# Build the project using Maven:

mvn clean compile


# Run the application:

mvn exec:java -Dexec.mainClass="com.bank.App"


Make sure you have a valid data.csv file in the project root for training the model.

#Usage

When you run the program, it will ask for applicant details:

Gender: (Male/Female)

Married?: (Yes/No)

Number of Dependents: (0/1/2/3+)

Education: (Graduate / Not Graduate):

Self Employed? (Yes/No):

Applicant and Coapplicant Income:

Desired Loan Amount:

Loan Term in months:

Credit History (Good / Bad):

# The program will:

Load a pre-trained model (or train a new one if none exists)

Evaluate model performance

Predict whether the applicant is eligible for a loan

The output shows:

✅ Eligible or ❌ Not eligible

Model evaluation metrics and estimated accuracy

# Model Accuracy

The Random Forest model currently achieves an accuracy of ~78% based on cross-validation with the provided dataset. Accuracy may improve with larger datasets, feature engineering, or algorithm tuning.

License

This project is licensed under the MIT License.
