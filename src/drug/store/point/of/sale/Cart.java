/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;

/**
 *
 * @author User
 */
public class Cart extends JFrame implements ActionListener, MouseListener {

    JTable userTbl = new JTable();

    JPanel accountPnl = new JPanel();

    JButton close = new JButton("Close");
    JButton deletecart = new JButton("Delete");
    JButton checkout = new JButton("Check out");
    int selectedRow = 0;
    JLabel totalTxt = new JLabel("Total Price: ");
    JLabel cash = new JLabel("Enter Cash: ");
    JTextField amount = new JTextField(18);
    double total = 0;

    DefaultTableModel model = (DefaultTableModel) userTbl.getModel();

    Cart() {

        updatetable();

        JScrollPane pane = new JScrollPane(userTbl);
        pane.setBounds(30, 30, 610, 550);
        close.setBounds(558, 600, 80, 24);
        deletecart.setBounds(478, 610, 80, 24);
        checkout.setBounds(558 - 200, 600, 100, 24);
        userTbl.addMouseListener(this);
        totalTxt.setBounds(30, 610, 300, 24);

        add(amount);
        add(deletecart);
        add(totalTxt);
        accountPnl.setLayout(null);
        accountPnl.add(close);
        accountPnl.add(checkout);
        amount.setBounds(220, 610, 100, 24);
        cash.setBounds(150, 610, 300, 24);
        add(cash);
        deletecart.setEnabled(false);

        deletecart.addActionListener(this);
        close.addActionListener(this);
        checkout.addActionListener(this);

        accountPnl.add(pane);

        add(accountPnl);
        accountPnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Cart", TitledBorder.LEFT, TitledBorder.TOP));
        accountPnl.setBounds(10, 10, 675, 650);

        setLayout(null);
        setResizable(false);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Cart");
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {

        Cart a = new Cart();
        a.setVisible(true);
    }

    void updatetable() {
        total = 0;
        String myproduct[][] = Transaction.viewCart();
        int quantity[] = new int[100];
        double price[] = new double[100];
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

        userTbl.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Product Id", "Product Name", "Product Brand", "Product Price", "Quantity"}
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class};
            boolean[] canEdit = new boolean[]{
                false, false, false, false, true,};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        model = (DefaultTableModel) userTbl.getModel();
        Object rowData[] = new Object[5];

        for (int x = 0; x < newproduct.length; x++) {

            rowData[0] = newproduct[x][0];
            rowData[1] = newproduct[x][1];
            rowData[2] = newproduct[x][2];
            rowData[3] = price[x];
            rowData[4] = quantity[x];
            total += price[x];
            model.addRow(rowData);

        }
        totalTxt.setText("Total Price: " + total + "");
        userTbl.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                deletecart.setEnabled(true);
            }
        });

        userTbl.setModel(model);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == checkout) {

            int optionResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to checkout?", "Alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (optionResult == 0) {

                String result = Transaction.checkout(amount.getText(), total);
                if (result.equals("invalidamount")) {
                    JOptionPane.showMessageDialog(null, "Cash you entered is not digit", "Alert", JOptionPane.ERROR_MESSAGE);

                } else if (result.equals("invalid")) {
                    JOptionPane.showMessageDialog(null, "Invalid Amount", "Alert", JOptionPane.ERROR_MESSAGE);

                } else {
                    dispose();
                }

            }
        }
        if (ae.getSource() == close) {
            MainMenu.updatecart();
            dispose();
        }
        if (ae.getSource() == deletecart) {
            int optionResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (optionResult == 0) {

                model = (DefaultTableModel) userTbl.getModel();
                userTbl.clearSelection();
                deletecart.setEnabled(false);
                Transaction.removeCart(parseInt(model.getValueAt(selectedRow, 0).toString()), parseInt(model.getValueAt(selectedRow, 4).toString()));
                updatetable();
                dispose();
            }

        }
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
        if (me.getSource() == userTbl) {
            checkout.setEnabled(true);
            selectedRow = userTbl.getSelectedRow();
        }

    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

}
