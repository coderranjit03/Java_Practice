public class practice {

    // public static boolean sumOfNum(int n){
    // if(n==2){
    // return true;
    // }
    // for(int i=2; i<=Math.sqrt(n); i++){
    // if( n%i==0){
    // return false;
    // }

    // }
    // return true;
    // }

    // public static void isPrime(int n){
    // for(int i=2; i<=n; i++){
    // if(sumOfNum(i)){
    // System.out.print(i+" ");
    // }
    // }
    // System.out.println();
    // }

    // public static void binTodec(int binNum){
    // int mynum = binNum;
    // int pow =0;
    // int dec =0;
    // while (binNum>0) {
    // int lastdigit = binNum%10;
    // dec= dec+(lastdigit* (int)Math.pow(2, pow));

    // pow++;
    // binNum = binNum/10;
    // }
    // System.out.println("The decimal of "+ mynum + "=" + dec );
    // }

    // public static void decTobin(int decnum){
    // int mynum = decnum;
    // int pow =0;
    // int binNum =0;

    // while (decnum>0) {
    // int rem = decnum%2;
    // binNum = binNum +(rem*(int)Math.pow(10,pow));
    // pow++;
    // decnum =decnum/2;
    // }
    // System.out.println("The binary of "+ mynum + "=" + binNum );
    // }

    // public static void main(String[] args) {

    // int dec =0;
    // Scanner sc = new Scanner(System.in);
    // System.out.print("Enetr no to convert into decimal : ");
    // //int binNum = sc.nextInt();
    // int decnum = sc.nextInt();
    // decTobin(decnum);

    // }
    // *** Enter 3 numbers from the user & make a function to print their average.
    // ***//
    // public static int myAvg(int a, int b, int c){

    // int avg = (a+b+c)/3;
    // return avg;
    // }

    // void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.print("Enter First no ");
    // int a = sc.nextInt();
    // System.out.print("Enter Second no ");
    // int b = sc.nextInt();
    // System.out.print("Enter Third no ");
    // int c = sc.nextInt();

    // int avg = myAvg(a,b,c);
    // System.out.println("Sum of A and B is : "+avg);
    // }

    // *** Write a function to print the sum of all odd numbers from 1 to n. ***//
    // public static int mySum(int n){
    // int sum = 0;
    // for(int i=1; i<=n; i++){
    // if (i%2!=0){
    // sum=sum+i;
    // i++;

    // }

    // }
    // return sum;
    // }

    // public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Enter No to upto Add : ");
    // int n = sc.nextInt();
    // int sum = mySum(n);
    // System.out.println("The sum of odd no from 1 to " + n + " is : "+ sum);
    // }

    // *** Write a function which takes in 2 numbers and returns the greater of
    // those two. ***//
    // public static int findGreater(int a, int b){
    // if (a>b){
    // return a;
    // }
    // else{
    // return b;
    // }
    // }

    // public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.print("Enter First no ");
    // int a = sc.nextInt();
    // System.out.print("Enter Second no ");
    // int b = sc.nextInt();
    // int grt = findGreater(a, b);
    // System.out.println("The greater number is: " + grt);
    // }

    // *** Write a function that takes in the radius as input and returns the
    // circumference of a circle. ***//
    // public static double circum(int r){
    // double circumference = 2*Math.PI*r;
    // return circumference;
    // }
    // public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println(" Enter Radius of Circle : ");
    // int r = sc.nextInt();
    // double circumference = circum(r);
    // System.out.println("The Circumference of circle is : "+ circumference);
    // }
    // *** Write a function that takes in age as input and returns if that person is
    // eligible to vote or not. A person of age > 18 is eligible to vote. ***//
    // public static boolean votiing(int age){
    // return age>=18;
    // }

    // public static void main(String[] args) {
    // Scanner sc = new Scanner(System.in);
    // System.out.println("Enter ur age : ");
    // int age = sc.nextInt();
    // boolean isEligable = votiing(age);
    // if(isEligable){
    // System.out.println("You are Eligible for Votiing");
    // }
    // else{
    // System.out.println("You Are not eligible For vote");
    // }
    // }

    // *** Write a program to enter the numbers till the user wants and at the end
    // it should display the count of positive, negative and zeros entered ***//

    // public static void main(String[] args) {
    // Scanner scanner = new Scanner(System.in);
    // int positiveCount = 0;
    // int negativeCount = 0;
    // int zeroCount = 0;
    // int number;

    // System.out.println("Enter numbers (enter 0 to stop):");

    // do {
    // number = scanner.nextInt();

    // if (number > 0) {
    // positiveCount++;
    // } else if (number < 0) {
    // negativeCount++;
    // } else {
    // zeroCount++;
    // }
    // } while (number != 0);

    // System.out.println("Positive numbers: " + positiveCount);
    // System.out.println("Negative numbers: " + negativeCount);
    // System.out.println("Zeros: " + zeroCount);
    // }

    // *** Two numbers are entered by the user, x and n. Write a function to find
    // the value of one number raised to the power of another i.e. xn ***//
    // public static double calculatePower(double base, int exponent) {
    // double result = 1.0;
    // for (int i = 0; i < exponent; i++) {
    // result *= base;
    // }
    // return result;
    // }
    // public static void main(String[] args) {
    // double base = 2.0;
    // int exponent = 5;
    // double result = calculatePower(base, exponent);
    // System.out.println(base + " raised to the power of " + exponent + " is: " +
    // result);
    // }

    public static void main(String[] args) {
        int counter = 1;
        while (counter <= 100) {
            System.out.print(counter + " ");
            counter++;
        }
        System.out.println(" ");
    }

}
