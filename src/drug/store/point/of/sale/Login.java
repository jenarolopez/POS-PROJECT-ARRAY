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
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author User
 */
public class Login extends JFrame implements ActionListener, MouseListener {
    
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
    
    JButton button = new JButton("Login");
    JLabel background = new JLabel();
    
    Login() {
        add(register);
        setLayout(null);
        setResizable(false);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Login");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        background.setBounds(135, 30, 380, 170);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(System.getProperty("user.dir") + "\\1.png").getImage().getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_DEFAULT));
        background.setIcon(imageIcon);
        add(background);
        menu.setText("About Us");
        menu.addMouseListener(this);
        menubar.add(menu);
        
        division.addItem("Admin");
        
        pnl1 = new JPanel();
        pnl1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pnl1.add(lbl1);
        userField.setPreferredSize(new Dimension(200, 24));
        pnl1.add(userField);
        pnl1.add(lbl2);
        passwordField.setPreferredSize(new Dimension(200, 24));
        pnl1.add(passwordField);
        
        pnl1.setBounds(160, 220, 300, 120);
        
        button.setBounds(pnl1.getX() + 110, pnl1.getY() + 120, 100, 30);
        pnl1.setOpaque(false);
        
        register.setBounds(button.getX() - 5, button.getY() + button.getHeight(), 200, 24);
        register.setCursor(new Cursor(Cursor.HAND_CURSOR));
        register.setForeground(Color.blue);
        setJMenuBar(menubar);
        add(pnl1);
        add(button);
        
        button.addActionListener(this);
        register.addMouseListener(this);
    }
    
    public static void main(String[] args) {
        
        Login login = new Login();
        login.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent r) {
        
        if (r.getSource() == button) {
            
            String result = UserVariables.Login(userField.getText().toString(), passwordField.getText().toString());
            if (result.equals("nomatch")) {
                JOptionPane.showMessageDialog(null, "Username and password not matched.", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            if (result.equals("error")) {
                JOptionPane.showMessageDialog(null, "Invalid Username", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            if (result.equals("log")) {
                JOptionPane.showMessageDialog(null, "Logged in", "Alert", 1);
                MainMenu main = new MainMenu();
                main.setVisible(true);
                dispose();
            }
            if (result.equals("blocked")) {
                JOptionPane.showMessageDialog(null, "User has been BLOCKED. Please contact administrator", "Alert", JOptionPane.ERROR_MESSAGE);
            }
            if (result.equals("complete")) {
                JOptionPane.showMessageDialog(null, "Please complete the form...", "Alert", 1);
            }
        }
    }
    
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == register) {
            RegisterUser a = new RegisterUser();
            a.setVisible(true);
            dispose();
        }
        if (me.getSource() == menu) {
            AboutUs a = new AboutUs();
            a.setVisible(true);
        }
        
    }
    
    public void mousePressed(MouseEvent me) {
    }
    
    public void mouseReleased(MouseEvent me) {
    }
    
    public void mouseEntered(MouseEvent me) {
    }
    
    public void mouseExited(MouseEvent me) {
    }
    
}
