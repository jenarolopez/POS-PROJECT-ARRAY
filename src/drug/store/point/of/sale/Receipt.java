/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import static java.lang.Double.parseDouble;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author User
 */
public class Receipt extends JFrame {

    JTextArea details = new JTextArea();
    JScrollPane scroll = new JScrollPane(details);
    String Receipt = "";
    double totalprice = 0;
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    Receipt() {

        details = new JTextArea();
        details.setEditable(false);
        scroll = new JScrollPane(details);
//this.add(textArea); // get rid of this
        add(scroll);
        String pattern = "MM-dd-yyyy : HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

       
        Receipt += "\n\n\t                   THANK YOU FOR PURCHASING"
                + "\n"
                + "\n"
                + "\n"
                + "\tStaff: " + UserVariables.firstname[UserVariables.currentUser] + " " + UserVariables.lastname[UserVariables.currentUser]
                + "\n"
                + "\tDate:  " + date
                + "\n"
                + "\n"
                + "\n"
                + "\tYour Order:"
                + "\n\n\n";

        String myproduct[][] = Transaction.viewCart();
        int quantity[] = new int[100];
        int price[] = new int[100];
        int newCtr = 0;
        for (int x = 0; x < myproduct.length; x++) {
            if (myproduct[x][1] != null) {
                for (int y = 0; y < myproduct.length; y++) {
                    if (y != x) {
                       
                        if (myproduct[x][1].equals(myproduct[y][1])) {
                            myproduct[y][1] = null;
                            quantity[newCtr]++;
                            price[newCtr] += parseDouble(myproduct[y][3]);
                        }
                    }
                    if (y == x) {
                        quantity[newCtr]++;
                        price[newCtr] += parseDouble(myproduct[y][3]);
                    }

                }

                newCtr++;
            }
        }
        String newproduct[][] = new String[newCtr][5];
        newCtr = 0;
        for (int x = 0; x < myproduct.length; x++) {
            if (myproduct[x][1] != null) {
                newproduct[newCtr][0] = myproduct[x][0];
                newproduct[newCtr][1] = myproduct[x][1];
                newproduct[newCtr][2] = myproduct[x][2];
                newproduct[newCtr][3] = myproduct[x][3];

                newCtr++;
            }
        }
        for (int x = 0; x < newproduct.length; x++) {

            Receipt += "\t" + newproduct[x][1] + "\t\tPrice: " + (price[x] / quantity[x]) + "\n"
                    + "\tX" + quantity[x] + "\t\tTotal Price: " + price[x] + "\n\n";
            totalprice += price[x];
        }

        Receipt += "\tYour Cash:          " + df2.format(Transaction.cash) + ""
                + "\n"
                + "\tYour Total Price :" + df2.format(Transaction.total) + ""
                + "\n"
                + "\tYour Change:     " + df2.format(Transaction.cash - Transaction.total) + ""
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\n"
                + "\t\t   HAVE A NICE DAY";
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                Transaction.clearcart();
                MainMenu.closeTransaction();
                MainMenu.updateproducts();
                MainMenu.deleteproduct.setEnabled(true);
            }
        });
        details.setText(Receipt);
        setLayout(new GridLayout(0, 1));
        details.setFont(new Font("Adobe Arabic", Font.BOLD, 16));
        setResizable(false);
        setSize(600, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Receipt");
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        Receipt a = new Receipt();
    }

}
