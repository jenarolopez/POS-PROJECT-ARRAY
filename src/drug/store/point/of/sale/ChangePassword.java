/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimerTask;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class ChangePassword extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();

    JMenu menu = new JMenu("Menu");

    ImageIcon img;

    JPanel pnl1;

    JLabel lbl1 = new JLabel("Enter New Password : ");
    JLabel lbl2 = new JLabel("Re-enter New Password : ");
    JTextField adminTxt = new JTextField(20);
    JPasswordField adminpasswordTxt = new JPasswordField(20);

    JLabel register = new JLabel("Sign up an account");

    JPasswordField pass1 = new JPasswordField();
    JPasswordField pass2 = new JPasswordField();
    JComboBox division = new JComboBox();

    JButton button = new JButton("Confirm");
    JLabel background;

    ChangePassword() {

        setLayout(null);
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Change Password");
        setResizable(false);
        setLocationRelativeTo(null);

        pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnl1.add(lbl1);
        pass1.setPreferredSize(new Dimension(200, 24));
        pnl1.add(pass1);
        pnl1.add(lbl2);
        pass2.setPreferredSize(new Dimension(200, 24));
        pnl1.add(pass2);

        pnl1.setBounds(10, 80, 400, 120);

        button.setBounds(pnl1.getX() + 180, pnl1.getY() + 80, 100, 30);
        pnl1.setOpaque(false);

        add(pnl1);
        add(button);

        button.addActionListener(this);

    }

    public static void main(String[] args) {
        
        ChangePassword pass = new ChangePassword();
        pass.setVisible(true);
    }

    public void actionPerformed(ActionEvent r) {
        if (r.getSource() == button) {
            JPanel pnl2 = new JPanel();
            pnl2.setLayout(new GridLayout(2, 0, 20, 10));
            pnl2.setSize(400, 400);
            pnl2.add(new JLabel("Admin Username: "));
            pnl2.add(adminTxt);
            pnl2.add(new JLabel("Admin Password: "));
            pnl2.add(adminpasswordTxt);
            if (pass1.getText().toString().replaceAll(" ", "").equals("")
                    || pass2.getText().toString().replaceAll(" ", "").equals("")) {
                JOptionPane.showMessageDialog(null, "Please complete the form...", "Alert", 1);
            } else if (!pass1.getText().toString().equals(pass2.getText().toString())) {
                JOptionPane.showMessageDialog(null, "Password not matched...", "Alert", 1);
            } else {
                int optionResult = JOptionPane.showConfirmDialog(null, pnl2, "Login Admin", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (optionResult == 0) {
                    if (adminTxt.getText().toString().replaceAll(" ", "").equals("")
                            || adminpasswordTxt.getText().toString().replaceAll(" ", "").equals("")) {
                        JOptionPane.showMessageDialog(null, "Please complete the form...", "Alert", 1);
                    } else {
                        String result = UserVariables.changePasswordLogin(adminTxt.getText().toString(), adminpasswordTxt.getText().toString(), pass1.getText().toString(), pass2.getText().toString());
                        if (result == "nomatch") {
                            JOptionPane.showMessageDialog(null, "Invalid admin account...", "Alert", 1);
                        }
                        if (result == "change") {
                            JOptionPane.showMessageDialog(null, "Password changed...", "Alert", 1);
                            dispose();
                        }

                    }
                }
            }
        }
    }

    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == register) {
            RegisterUser a = new RegisterUser();
            a.setVisible(true);
            dispose();
        }

    }

}
