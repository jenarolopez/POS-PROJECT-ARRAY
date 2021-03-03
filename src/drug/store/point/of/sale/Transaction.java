/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import static java.lang.Double.parseDouble;

public class Transaction {

    public static int[] cartId = new int[900];
    public static int[] productId = new int[900];
    public static String[] productbrand = new String[900];
    public static String[] productname = new String[900];
    public static double[] price = new double[900];

    public static double[] change = new double[900];

    public static double cash = 0;
    public static double total = 0;
    public static int cartCount = 0;

    Transaction() {

    }

    public static void main(String[] args) {

    }

    public static String addCart(int productId) {

        Transaction.cartId[cartCount] = cartCount;
        Transaction.productId[cartCount] = productId;
        Transaction.productname[cartCount] = ProductVariables.productname[productId];
        Transaction.productbrand[cartCount] = ProductVariables.productbrand[productId];
        Transaction.price[cartCount] = ProductVariables.price[productId];
        Transaction.cartCount++;
        ProductVariables.productstock[productId]--;

        MainMenu.updatecart();

        return "true";
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String removeCart(int productId, int stock) {

        for (int x = 0; x < Transaction.productname.length; x++) {
            if (Transaction.productname[x] != null) {
                if (Transaction.productId[x] == productId) {
                    Transaction.productname[x] = null;
                    Transaction.productbrand[x] = null;
                    Transaction.cartCount--;
                }
            }

        }
        ProductVariables.productstock[productId] += stock;

        MainMenu.updateproducts();
        return "true";
    }

    public static String checkout(String cash, double total) {

       

        if (isNumeric(cash) == false) {
            return "invalidamount";
        } else if (parseDouble(cash) <= total) {
            return "invalid";
        } else {
            Transaction.total = total;
            Transaction.cash = parseDouble(cash);
            Receipt a = new Receipt();
            a.setVisible(true);
            return "true";
        }

    }

    public static String[][] viewCart() {

        String[][] cart = new String[Transaction.cartCount][4];
        int newCtr = 0;
        for (int x = 0; x < Transaction.productname.length; x++) {

            if (Transaction.productname[x] != null) {

                cart[newCtr][0] = Transaction.productId[x] + "";
                cart[newCtr][1] = Transaction.productname[x] + "";
                cart[newCtr][2] = Transaction.productbrand[x];
                cart[newCtr][3] = Transaction.price[x] + "";
                newCtr++;
            }
        }
        return cart;

    }

    public static void clearcart() {

        for (int x = 0; x < Transaction.cartId.length; x++) {
            Transaction.cartId[x] = 0;
            Transaction.productId[x] = 0;
            Transaction.productbrand[x] = null;
            Transaction.productname[x] = null;
            Transaction.price[x] = 0;
            Transaction.change[x] = 0;
            Transaction.cash = 0;
            Transaction.total = 0;
            Transaction.cartCount = 0;
        }

    }

    public static String cancel() {

        for (int x = 0; x < Transaction.productname.length; x++) {
            if (Transaction.productname[x] != null) {
                Transaction.productname[x] = null;
                ProductVariables.productstock[Transaction.productId[x]]++;
                MainMenu.updateproducts();

            }

        }

        return "true";
    }

}
