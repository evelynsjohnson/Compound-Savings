/*
I suggest that you comment out the test cases for methods you have not implemented
yet. For each method you write, you should run Maven clean, to clean your project, and
then run Maven test. If the method is correct, you will pass the test/tests for that method
(run with the values from the text files if applicable). If not, you will see the value your
method returned and the value expected by the test case

SavingsMethodTest.java is the testing file
*/


public class SavingsFormulas{


    /*
     * takes a lump sum of money and figures out how much it will
     * be worth in a certain number of years at a constant interest rate
     * The formula is:          FV = PV(1 + i)^N

     * So if I had $10,000 and I wanted to know what it would be worth in 5 years,
     * compounded yearly at 5% interest (corresponding method variable in parentheses):
                                          PV = 10,000 (double cash)
                                          i = .05 (double interest)
                                          N = 5 ( int years)
     * The result would be $12762.81
     */
    public static double futureValueLumpSum(double cash, double interest, int years){
        return cash * Math.pow((1 + interest), years);
    }

    /*
     * takes a lump sum of money and figures out how much it will be worth in a
     * certain number of years at a varying interest rate per year. The values Array is loaded
     * with interest rates from values2.txt. Each index in the array corresponds to the rate for a
     * specific year. For each year you have a unique interest rate i:
                    year1 = PV * (1+i)
                    year2 = year1 * (1+i)
                    year3 = year2 * (1+i)…..you get the idea.

     * So in the method you would be doing:
                    cash = cash(1 + values[0]);
                    cash = cash(1 + values[1]);
     * And so on for all the rates in values[]

     * You must implement this one recursively (you will need to write a private helper method)
     */
    private static double fvLS_VarInt_Helper(double cash, double values[], int year){
        if (year == values.length) {
            return cash;
        }
        double updatedCash = cash * (1 + values[year]);
        return fvLS_VarInt_Helper(updatedCash, values, year + 1);
    }

    public static double futureValueLS_VariableInterest(double cash, double values[]){
        return fvLS_VarInt_Helper(cash, values, 0);
    }

    /*
     * This method calculates the future value of saving the same amount of money each year
     * for a certain number of years at a constant interest rate.
     * FV(subscript A) = Pmt [ {(1 + i)^N - 1}   /   i ]

     * So if I invested $1000 at the end of each year for 5 years at 5% interest:
                                          Pmt = 10,000 (double cash)
                                          i = .05 (double interest)
                                          N = 5 (double years)
     * The result would be $5525.63
     */
    public static double compoundSavingsConstant(double cash, double interest, int years){
        return cash * ((Math.pow((1 + interest), years) - 1)/interest);
    }

    /*
     * This method calculates the future value of investing a different amount of money at the
     * end of each year for a certain number of years at a constant interest rate.
     * The values Array is loaded with the dollar amounts for each year from values.txt. Each
     * index in the array corresponds to a dollar amount for a specific year.
                    year1 = values[0];
                    year2 = (year1 * interest) + values[1]……you get the idea

     * You must implement this one recursively (you will need to write a private helper method)
     */
    private static double compSavVar_Helper(double cash, double values[], double interest, int year) {
        if (year == values.length) {
            return cash;
        }
        double updatedCash = (cash * interest) + values[year];
        return compSavVar_Helper(updatedCash, values, interest, year + 1);
    }

    public static double compoundSavingsVariable(double values[], double interest){
        // Have to change interest to 1.XX to be able to multiply. E.g., 7% interest = 1.07 increase from prev year
        // Similar to prev. function of "(1 + i)"
        return compSavVar_Helper(0, values, interest + 1, 0);
    }
}