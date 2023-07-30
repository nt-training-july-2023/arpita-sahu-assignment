package JavaBasics;

import java.util.Scanner;
public class ArmstrongNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int num = sc.nextInt();
        int n=num, pow=0;
        while(n>0) {
        	pow++;
        	n/=10;
        }
        int sum=0, originalNum=num;
        while (num > 0) {
            int digit = num%10;
            sum += Math.pow(digit, pow);
            num /= 10;
        }
        if(sum == originalNum)
        	System.out.println(originalNum +" is an Armstrong Number");
        else
        	System.out.println(originalNum +" is not an Armstrong Number");
        sc.close();
    }
    
}

