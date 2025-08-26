// Redeems available tokens and Generates Bill.

import java.util.Scanner;

class TokenBill {
    static int creditChoice;
    static String redeemCard,redeemUpi;
    void generateBill() {
        Scanner sc = new Scanner(System.in);
        GameZone gz = new GameZone();
        PaymentMethods pm = new PaymentMethods();

        if(gz.userTokens>0){
            System.out.println(gz.ANSI_YELLOW+ "\nEnter Payment Method to credit rupees earned from redeemed tokens: ");
            System.out.println("1. UPI Number");
            System.out.println("2. Credit/Debit Card" +gz.ANSI_RESET);
            System.out.print(gz.ANSI_GREEN+ "Enter Your Choice: " +gz.ANSI_RESET);
            creditChoice = sc.nextInt();
            pm.confirmRedeem();
        }

        System.out.println(gz.ANSI_RED+"\n------------------------------------ BILL ----------------------------------"+gz.ANSI_RESET);
        System.out.println(gz.ANSI_YELLOW+"Tokens Purchased: " + (gz.totalTokensPurchased));
        System.out.println("Tokens Used: "+ (gz.tokensDeducted));
        System.out.println("Tokens Won: "+ (gz.totalTokensWon));
        System.out.println("Tokens Redeemed: "+ (gz.userTokens) +gz.ANSI_RESET);
        if(gz.userTokens>0){
            if(creditChoice==1){
                StringBuffer maskedUpi = new StringBuffer(redeemUpi);
                maskedUpi.delete(0, 7);
                System.out.println(gz.ANSI_GREEN+ gz.updatedRupees+ " Rs. will be credited to your UPI Number: XXXXXXX"+maskedUpi+" in 24 Hours." +gz.ANSI_RESET);
            }
            else if(creditChoice==2){
                StringBuffer maskedCard = new StringBuffer(redeemCard);
                maskedCard.delete(0, 8);
                System.out.println(gz.ANSI_GREEN+ gz.updatedRupees+" Rs. will be credited to your Card Number: XXXX XXXX "+maskedCard+" in 24 Hours." +gz.ANSI_RESET);
            }
        }
        else{
            System.out.println(gz.ANSI_RED+ "Sorry, you did not win any tokens." +gz.ANSI_RESET);
        }
        System.out.println(gz.ANSI_YELLOW+ "Thank you for playing!"+gz.ANSI_RESET);
        System.out.println(gz.ANSI_RED+"----------------------------------------------------------------------------"+gz.ANSI_RESET);

        System.out.println(gz.ANSI_LIGHT_PURPLE+"\n\n\nDEVELOPED BY: MISTRY KAHAN." +gz.ANSI_RESET);
    }    
}
