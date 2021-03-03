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
public class Accounts extends JFrame implements ActionListener, MouseListener {

    JTable userTbl = new JTable();

    JPanel accountPnl = new JPanel();

    JButton save = new JButton("Save Changes");
    JButton delete = new JButton("Delete");

    DefaultTableModel model;

    Accounts() {

        updatetable();

        JScrollPane pane = new JScrollPane(userTbl);
        pane.setBounds(30, 30, 610, 550);
        save.setBounds(505, 600, 130, 24);
        delete.setBounds(405, 600, 80, 24);

        accountPnl.setLayout(null);
        accountPnl.add(save);
        accountPnl.add(delete);

        delete.setEnabled(false);

        save.addActionListener(this);
        delete.addActionListener(this);

        accountPnl.add(pane);

        add(accountPnl);
        accountPnl.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "All Users", TitledBorder.LEFT, TitledBorder.TOP));
        accountPnl.setBounds(10, 10, 675, 650);

        setLayout(null);
        setResizable(false);
        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Accounts");
        setResizable(false);
        setLocationRelativeTo(null);
       

    }

    public static void main(String[] args) {

        Accounts a = new Accounts();
        a.setVisible(true);
    }

    void updatetable() {

        userTbl.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "UserId", "Username", "Password", "IsAdmin", "IsBlocked/Tries",}
        ) {
            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
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
        Object rowData[] = new Object[5];

        String users[][] = UserVariables.getusers();

        for (int x = 0; x < users.length; x++) {
            if (!users[x][1].equals(UserVariables.username[UserVariables.currentUser])) {
                rowData[0] = users[x][0];
                rowData[1] = users[x][1];
                rowData[2] = users[x][2];

                if (users[x][3].equals("true")) {
                    rowData[3] = true;
                } else {
                    rowData[3] = false;
                }
                rowData[4] = users[x][4];

                model.addRow(rowData);
            }
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
            int optionResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete?", "Alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (optionResult == 0) {

                userTbl.clearSelection();
                delete.setEnabled(false);
                UserVariables.delete(parseInt(model.getValueAt(userTbl.getSelectedRow() + 1, 0).toString()));
                updatetable();
            }
        }
        if (ae.getSource() == save) {
            userTbl.clearSelection();
            int optionResult = JOptionPane.showConfirmDialog(null, "Save changes?", "Alert", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (optionResult == 0) {
                
                int[][] setaccount = new int[model.getRowCount()][3];
                String[] usernames = new String[model.getRowCount()];

                for (int x = 0; x < model.getRowCount(); x++) {

                    setaccount[x][0] = parseInt(model.getValueAt(x, 0).toString());
                    if (model.getValueAt(x, 3).toString().equals("true")) {
                        setaccount[x][1] = 1;
                    } else {
                        setaccount[x][1] = 0;
                    }

                    setaccount[x][2] = parseInt(model.getValueAt(x, 4).toString());

                    usernames[x] = model.getValueAt(x, 1).toString();
                   
                }

               
                for (int x = 0; x < usernames.length; x++) {
                    

                    UserVariables.saveusers(setaccount[x][2], setaccount[x][1], setaccount[x][0]);
                }
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
            delete.setEnabled(true);
        }
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

}
