/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;


import static drug.store.point.of.sale.MainMenu.menu;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class UserDetails extends JFrame implements ActionListener {

    JPanel pnl1 = new JPanel();

    JPanel infoPanel = new JPanel();

    JLabel userLbl = new JLabel("Username: ");
    JLabel useridLbl = new JLabel("User ID: ");
    JLabel firstnameLbl = new JLabel("First Name: ");
    JLabel middlenameLbl = new JLabel("Middle Name: ");
    JLabel lastnameLbl = new JLabel("Last Name: ");
    JLabel contactLbl = new JLabel("Contact number: ");
    JLabel addressLbl = new JLabel("Address: ");
    JLabel genderLbl = new JLabel("Gender: ");

    JLabel image = new JLabel();

    JTextField userTxt = new JTextField(20);
    JTextField useridTxt = new JTextField(20);
    JTextField firstnameTxt = new JTextField(20);
    JTextField middlenameTxt = new JTextField(20);
    JTextField lastnameTxt = new JTextField(20);
    JTextField contactTxt = new JTextField(20);
    JTextField addressTxt = new JTextField(20);

    JComboBox genderCombo = new JComboBox();

    JButton save = new JButton("Save");
    JButton cancel = new JButton("Cancel");
    JButton addimage = new JButton("Change Profile");

    public static JFileChooser fc = new JFileChooser();
    public static File file;

    GridLayout grid = new GridLayout(0, 2, 10, 10);

    public static String path;

    UserDetails() {

        userTxt.setText(UserVariables.username[UserVariables.currentUser]);
        userTxt.setEditable(false);
        useridTxt.setText(UserVariables.userId[UserVariables.currentUser] + "");
        useridTxt.setEditable(false);
        firstnameTxt.setText(UserVariables.firstname[UserVariables.currentUser] + "");
        lastnameTxt.setText(UserVariables.lastname[UserVariables.currentUser] + "");
        middlenameTxt.setText(UserVariables.middlename[UserVariables.currentUser] + "");
        contactTxt.setText(UserVariables.contact[UserVariables.currentUser] + "");
        addressTxt.setText(UserVariables.address[UserVariables.currentUser] + "");

        add(save);
        add(cancel);
        add(addimage);
        add(image);
        infoPanel.add(pnl1);
        add(infoPanel);

        addimage.addActionListener(this);
        save.addActionListener(this);
        cancel.addActionListener(this);

        setLayout(null);
        setResizable(false);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Register");
        setResizable(false);
        setLocationRelativeTo(null);

        addimage.setBounds(344, 130, 120, 24);
        save.setBounds(150, 400, 100, 24);
        cancel.setBounds(150 + 120, 400, 100, 24);

        path = UserVariables.image[UserVariables.currentUser];

        image.setBounds(350, 20, 100, 100);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
        image.setIcon(imageIcon);

        genderCombo.addItem("");
        genderCombo.addItem("Male");
        genderCombo.addItem("Female");

        if (UserVariables.gender[UserVariables.currentUser] == "Male") {
            genderCombo.setSelectedIndex(1);
        } else {
            genderCombo.setSelectedIndex(2);
        }

        pnl1.setLayout(grid);
        pnl1.add(useridLbl);
        pnl1.add(useridTxt);

        pnl1.add(userLbl);
        pnl1.add(userTxt);

        pnl1.add(firstnameLbl);
        pnl1.add(firstnameTxt);
        pnl1.add(middlenameLbl);
        pnl1.add(middlenameTxt);
        pnl1.add(lastnameLbl);
        pnl1.add(lastnameTxt);
        pnl1.add(genderLbl);
        pnl1.add(genderCombo);
        pnl1.add(contactLbl);
        pnl1.add(contactTxt);
        pnl1.add(addressLbl);
        pnl1.add(addressTxt);
        pnl1.setBounds(10, 50, 300, 250);
        infoPanel.setLayout(null);
        infoPanel.setBounds(0, 0, 320, 375);

        infoPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "User Information", TitledBorder.LEFT, TitledBorder.TOP));

    }

    public static void main(String[] args) {
      
        UserDetails a = new UserDetails();

    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == addimage) {
            int returnVal = fc.showOpenDialog(UserDetails.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(imageIcon);
                path = file.getAbsolutePath();
            }
        }
        if (a.getSource() == save) {
           
            File source = new File(path);
            File dest;
            int nameoccured = 0;
            for (int x = 0; x < UserVariables.username.length; x++) {
                if (UserVariables.username[x] != null && UserVariables.username[x].toLowerCase().equals(userTxt.getText().toLowerCase())) {
                    nameoccured++;
                }
            }
            if (nameoccured > 1) {
                dest = new File(System.getProperty("user.dir") + "\\images\\" + userTxt.getText() + nameoccured + ".png");

            } else {
                dest = new File(System.getProperty("user.dir") + "\\images\\" + userTxt.getText() + ".png");

            }
            try {
                Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(UserDetails.class.getName()).log(Level.SEVERE, null, ex);
            }

            String result = UserVariables.Update(firstnameTxt.getText(), lastnameTxt.getText(), middlenameTxt.getText(), contactTxt.getText(), addressTxt.getText(), path);
            menu.setText(UserVariables.firstname[UserVariables.currentUser]);
            dispose();
        }
        if (a.getSource() == cancel) {
            dispose();
        }
    }

}
