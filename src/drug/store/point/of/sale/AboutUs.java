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
import java.awt.Image;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class AboutUs extends JFrame {

    JMenuBar menubar = new JMenuBar();

    JMenu menu = new JMenu("Menu");

    ImageIcon img;

    JPanel pnl1;

    JLabel lbl1 = new JLabel("Username : ");
    JLabel lbl2 = new JLabel("Password : ");
    JLabel lbl3 = new JLabel("Division : ");

    JLabel register = new JLabel("Sign up an account");

    JTextField userField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JComboBox division = new JComboBox();

    JButton button = new JButton("About Us");
    JLabel background = new JLabel();
    JTextArea a = new JTextArea();

    AboutUs() {
        add(a);
        add(background);
        setLayout(null);
        setResizable(false);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("AboutUs");
        setResizable(false);
        setLocationRelativeTo(null);
        a.setForeground(Color.yellow);
        a.setFont(new Font("Monospaced", Font.PLAIN, 30));
        a.setEditable(false);
        a.setText("\n\tMembers: "
                + "\n\tAljay Meeko Lasiste"
                + "\n\tKenneth Renz Gonzales"
                + "\n\tAllen Crisostomo");
        a.setOpaque(false);
        a.setBounds(0, 0, 650, 500);

        background.setBounds(0, 0, 650, 500);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\2.jpg").getImage().getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_DEFAULT));
        background.setIcon(imageIcon);

    }

    public static void main(String[] args) {
        AboutUs login = new AboutUs();
        login.setVisible(true);
    }

}
