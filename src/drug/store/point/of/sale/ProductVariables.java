/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 *
 * @author User
 */
public class ProductVariables {

    public static int[] productId = new int[100];
    public static double[] price = new double[100];
    public static String[] productname = new String[100];
    public static String[] productbrand = new String[100];
    public static String[] productdescription = new String[100];
    public static String[] image = new String[100];
    public static int[] productstock = new int[100];
    public static int productCount = 0;
    public static int currentProduct = 0;

    ProductVariables() {


    }

    public static void main(String[] args) {

    }

    public static String getProduct(int ctr) {
        int newCtr = 0;
        for (int x = 0; x < 100; x++) {
            if (ProductVariables.productname[x] != null) {

                if (newCtr == ctr) {
                    ProductVariables.currentProduct = x;
                    ProductDetails a = new ProductDetails();
                    a.setVisible(true);

                }
                newCtr++;
            }
        }

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

    public static String addProduct(String name, String brand, String description, String stock, String image, String price) {

        if (isNumeric(price) == false) {
            return "invalid";
        }
        if (isNumeric(stock) == false) {
            return "invalid";
        }

        if (name.replaceAll(" ", "").equals("")
                || brand.replaceAll(" ", "").equals("")
                || description.replaceAll(" ", "").equals("")
                || stock.replaceAll(" ", "").equals("")
                || image.replaceAll(" ", "").equals("")) {
            return "complete";
        } else {

            for (int x = 0; x < ProductVariables.productname.length; x++) {
                if (ProductVariables.productname[x] != null) {
                    if (name.equals(ProductVariables.productname[x])) {
                        return "used";
                    }
                }
            }

            ProductVariables.price[productCount] = parseDouble(price);
            ProductVariables.productId[productCount] = productCount;
            ProductVariables.productname[productCount] = name;
            ProductVariables.productbrand[productCount] = brand;
            ProductVariables.productdescription[productCount] = description;
            ProductVariables.productstock[productCount] = parseInt(stock);
            ProductVariables.image[productCount] = image;
            productCount++;
            return "true";

        }
    }

    public static String deleteProduct(int productId) {

        ProductVariables.productId[productId] = productId;
        ProductVariables.productname[productId] = null;

        return "true";

    }

    public static String updateProduct(String name, String brand, String description, String stock, String image, String price) {

        if (isNumeric(price) == false) {
            return "invalid";
        }
        if (isNumeric(stock) == false) {
            return "invalid";
        }

        if (name.replaceAll(" ", "").equals("")
                || brand.replaceAll(" ", "").equals("")
                || description.replaceAll(" ", "").equals("")
                || stock.replaceAll(" ", "").equals("")
                || image.replaceAll(" ", "").equals("")) {
            return "complete";
        } else {

            for (int x = 0; x < ProductVariables.productname.length; x++) {
                if (ProductVariables.productname[x] != null) {
                    if (name.equals(ProductVariables.productname[x]) && x!=ProductVariables.currentProduct) {
                        return "used";
                    }
                }
            }

            ProductVariables.productname[ProductVariables.currentProduct] = name;
            ProductVariables.productbrand[ProductVariables.currentProduct] = brand;
            ProductVariables.productdescription[ProductVariables.currentProduct] = description;
            ProductVariables.productstock[ProductVariables.currentProduct] = parseInt(stock);
            ProductVariables.image[ProductVariables.currentProduct] = image;

            return "true";

        }

    }

    public static String[][] viewProduct() {
        int allProduct = 0;
        for (int x = 0; x < ProductVariables.productname.length; x++) {
            if (ProductVariables.productname[x] != null) {
                allProduct++;
            }
        }

        String products[][] = new String[allProduct][6];

        int newCtr = 0;
        for (int x = 0; x < ProductVariables.productname.length; x++) {
            if (ProductVariables.productname[x] != null) {

                products[newCtr][0] = ProductVariables.productId[x] + "";
                products[newCtr][1] = ProductVariables.productname[x] + "";
                products[newCtr][2] = ProductVariables.productbrand[x] + "";
                products[newCtr][3] = ProductVariables.productdescription[x] + "";
                products[newCtr][4] = ProductVariables.productstock[x] + "";
                products[newCtr][5] = ProductVariables.image[x] + "";
                newCtr++;

            }

        }

        return products;
    }

}
