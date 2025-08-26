// Payments Methods For GameHub.

import java.util.Scanner;

class PaymentMethods {
    static String upiNumber;
    static String cardNumber;
    static GameZone gz;

    void confirmPaymentByUPI(int tokensPurchased, int totalCost) {
        Scanner sc = new Scanner(System.in);
        System.out.print(gz.ANSI_YELLOW+"\nEnter your UPI number (10 digits): "+gz.ANSI_RESET);
        upiNumber = sc.next();

        if (upiNumber.length() == 10) {
            System.out.println(gz.ANSI_GREEN+"Payment confirmed! " + tokensPurchased + " tokens added to your account." +gz.ANSI_RESET);
            gz.userTokens += tokensPurchased;
            System.out.println(gz.ANSI_YELLOW+ "Total Tokens Available: " + gz.userTokens+gz.ANSI_RESET+"\n");
        }
        else {
            System.out.println(gz.ANSI_RED+"Invalid UPI number. Purchase canceled.\n"+gz.ANSI_RESET);
        }
    }

    void confirmPaymentByCard(int tokensPurchased, int totalCost) {
        GameZone gz = new GameZone();
        Scanner sc = new Scanner(System.in);
        System.out.print(gz.ANSI_YELLOW+"\nEnter your card number (12 digits): "+gz.ANSI_RESET);
        cardNumber = sc.next();
        System.out.print(gz.ANSI_YELLOW+"Enter your CVV code (3 digits): "+gz.ANSI_RESET);
        int cvv = sc.nextInt();

        if (cardNumber.length() == 12 && String.valueOf(cvv).length() == 3) {
            System.out.println(gz.ANSI_GREEN+"Payment confirmed! " + tokensPurchased + " tokens added to your account." +gz.ANSI_RESET);
            gz.userTokens += tokensPurchased;
            System.out.println(gz.ANSI_YELLOW+ "Total Tokens Available: " + gz.userTokens+"\n"+gz.ANSI_RESET);
        }
        else {
            System.out.println(gz.ANSI_RED+"Invalid card information. Purchase canceled.\n"+gz.ANSI_RESET);
        }
    }

    void confirmRedeem(){
        TokenBill bill = new TokenBill();
        Scanner sc = new Scanner(System.in);
        boolean paymentStatus = true;
        while(paymentStatus){
            switch(bill.creditChoice){
                case 1:{
                    System.out.print(gz.ANSI_YELLOW+ "\nEnter UPI Number: " +gz.ANSI_RESET);
                    bill.redeemUpi = sc.next();
                    if(bill.redeemUpi.length()==10){
                        gz.totalTokensPurchased += gz.tokensToPurchase;
                        paymentStatus = false;
                        break;
                    }
                    else{
                        System.out.println(gz.ANSI_RED+ "Invalid UPI Number, please try again." +gz.ANSI_RESET);
                    }
                    break;
                }
                case 2:{
                    boolean cvvStatus = true;    
                    System.out.print(gz.ANSI_YELLOW+ "\nEnter your card number (12 digits): " +gz.ANSI_RESET);
                    bill.redeemCard = sc.next();     
                    if (bill.redeemCard.length() == 12) {
                        while (cvvStatus) {
                            System.out.print(gz.ANSI_YELLOW+ "Enter your CVV code (3 digits): " +gz.ANSI_RESET);
                            int cvv = sc.nextInt();
                            if (String.valueOf(cvv).length() == 3) {
                                paymentStatus = false;
                                cvvStatus = false;
                                gz.totalTokensPurchased += gz.tokensToPurchase;
                            }
                            else {
                                System.out.println(gz.ANSI_RED+ "Invalid CVV Number, please enter again." +gz.ANSI_RESET);
                            }
                        }
                    }
                    else {
                        System.out.println(gz.ANSI_RED+ "Invalid Card Number, please enter again." +gz.ANSI_RESET);
                    }
                    break;
                }
                default:{
                    System.out.println(gz.ANSI_RED+ "Enter Valid Payment Choice." +gz.ANSI_RESET);
                }
            }
        }
    }
}
