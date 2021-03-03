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
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;

/**
 *
 * @author User
 */
public class EditProducts extends JFrame implements ActionListener, MouseListener {

    public static JTable userTbl = new JTable();

    JPanel accountPnl = new JPanel();

    JButton close = new JButton("Close");
    public static JButton delete = new JButton("Update");
    int selectedRow = 0;

    public static DefaultTableModel model = (DefaultTableModel) userTbl.getModel();

    EditProducts() {

        model = new DefaultTableModel();
        updatetable();
        
        JScrollPane pane = new JScrollPane(userTbl);
        pane.setBounds(30, 30, 610, 550);
        close.setBounds(558, 600, 80, 24);
        delete.setBounds(558 - 100, 600, 80, 24);
        userTbl.addMouseListener(this);

        accountPnl.setLayout(null);
        accountPnl.add(close);
        accountPnl.add(delete);

        delete.setEnabled(false);

        close.addActionListener(this);
        delete.addActionListener(this);

        accountPnl.add(pane);

        add(accountPnl);
        accountPnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "All Product", TitledBorder.LEFT, TitledBorder.TOP));
        accountPnl.setBounds(10, 10, 675, 650);

        setLayout(null);
        setResizable(false);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Products(edit)");
        setResizable(false);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args) {
        
        EditProducts a = new EditProducts();
        a.setVisible(true);
    
    }

    public static void updatetable() {

        userTbl.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Product Id", "Product Name", "Product Brand"}
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,};
            boolean[] canEdit = new boolean[]{
                false, false, false, true, true,};

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        model = (DefaultTableModel) userTbl.getModel();
        Object rowData[] = new Object[3];

        String products[][] = ProductVariables.viewProduct();

        for (int x = 0; x < products.length; x++) {

            rowData[0] = products[x][0];
            rowData[1] = products[x][1];
            rowData[2] = products[x][2];

            model.addRow(rowData);

        }
        userTbl.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent me) {
                delete.setEnabled(true);
            }
        });

        userTbl.setModel(model);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == delete) {

            model = (DefaultTableModel) userTbl.getModel();
            userTbl.clearSelection();
            delete.setEnabled(false);
            ProductVariables.currentProduct = parseInt(model.getValueAt(selectedRow, 0).toString());
            EditProduct prod = new EditProduct();
            prod.setVisible(true);
            updatetable();

        }
        if (ae.getSource() == close) {
            MainMenu.updateproducts();
            dispose();
        }
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
        if (me.getSource() == userTbl) {
            delete.setEnabled(true);
            selectedRow = userTbl.getSelectedRow();
        }

    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

}
