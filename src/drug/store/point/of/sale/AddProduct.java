/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class AddProduct extends JFrame implements ActionListener {

    public static JFileChooser fc = new JFileChooser();
    public static File file;

    JLabel image = new JLabel();
    JLabel name = new JLabel("Mefgenamein");
    JLabel brand = new JLabel("RITEMED");
    JLabel description = new JLabel("LOL");
    JLabel price = new JLabel("200.00");

    JTextField nameTxt = new JTextField(16);
    JTextField brandTxt = new JTextField(16);
    JTextField descriptionTxt = new JTextField(16);
    JTextField priceTxt = new JTextField(16);
    JTextField stockTxt = new JTextField(16);

    String path = System.getProperty("user.dir") + "\\images\\noimage.png";

    JButton save = new JButton("Save");
    JButton addimg = new JButton("Add Image");
    JPanel pnl1 = new JPanel();

    AddProduct() {
        image.setBounds(20, 30, 200, 200);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));

        image.setIcon(imageIcon);

        pnl1.setLayout(new FlowLayout(FlowLayout.LEFT));
        addimg.setBounds(20, 235, 200, 24);

        name.setText("Name:");
        brand.setText("Brand:");
        description.setText("Description:");
        price.setText("Price:");
        pnl1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 16)));
        save.setBounds(363, 265, 80, 24);
        save.addActionListener(this);
        add(save);
        add(addimg);
        pnl1.add(name);
        pnl1.add(nameTxt);
        pnl1.add(brand);
        pnl1.add(brandTxt);
        pnl1.add(description);
        pnl1.add(descriptionTxt);
        pnl1.add(price);
        pnl1.add(priceTxt);
        pnl1.add(new JLabel("Stock"));
        pnl1.add(stockTxt);
        pnl1.setBounds(250, 0, 200, 265);

        add(pnl1);
        add(image);
        addimg.addActionListener(this);
        setLayout(null);
        setResizable(false);
        setSize(500, 325);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Product Details");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        AddProduct a = new AddProduct();

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {

            String result = ProductVariables.addProduct(nameTxt.getText().toString(),
                    brandTxt.getText().toString(),
                    descriptionTxt.getText().toString(),
                    stockTxt.getText().toString(), path,
                    priceTxt.getText().toString());
            if (result.equals("complete")) {
                JOptionPane.showMessageDialog(null, "Please complete the form...", "Alert", 1);

            } else {
                if (result.equals("used")) {
                    JOptionPane.showMessageDialog(null, "Product name already used...", "Alert", 1);
                } else if (result.equals("invalid")) {
                    JOptionPane.showMessageDialog(null, "Invalid Amount...", "Alert", 1);

                } else {
                    MainMenu.updateproducts();
                    dispose();
                }
            }

        }

        if (ae.getSource() == addimg) {
            int returnVal = fc.showOpenDialog(AddProduct.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(imageIcon);
                path = file.getAbsolutePath();
            }

        }
    }

}
