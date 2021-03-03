/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drug.store.point.of.sale;

import static drug.store.point.of.sale.MainMenu.image;
import static drug.store.point.of.sale.MainMenu.itemCart;
import static drug.store.point.of.sale.MainMenu.itemDetails;
import static drug.store.point.of.sale.MainMenu.itemPanel;
import static drug.store.point.of.sale.RegisterUser.fc;
import static drug.store.point.of.sale.RegisterUser.file;
import static drug.store.point.of.sale.RegisterUser.path;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
public class EditProduct extends JFrame implements ActionListener {

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

    String path = ProductVariables.image[ProductVariables.currentProduct];

    JButton save = new JButton("Save");
    JButton addimg = new JButton("Add Image");
    JPanel pnl1 = new JPanel();
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    EditProduct() {
        image.setBounds(20, 30, 200, 200);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(ProductVariables.image[ProductVariables.currentProduct]).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));

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

        nameTxt.setText(ProductVariables.productname[ProductVariables.currentProduct]);
        brandTxt.setText(ProductVariables.productbrand[ProductVariables.currentProduct]);
        descriptionTxt.setText(ProductVariables.productdescription[ProductVariables.currentProduct]);
        priceTxt.setText(df2.format(ProductVariables.price[ProductVariables.currentProduct]) + "");
        stockTxt.setText(ProductVariables.productstock[ProductVariables.currentProduct] + "");

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
        setTitle("Edit Product");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EditProduct a = new EditProduct();

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == save) {

            String result = ProductVariables.updateProduct(nameTxt.getText().toString(),
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
                    EditProducts.updatetable();
                    dispose();
                }
            }

        }

        if (ae.getSource() == addimg) {
            int returnVal = fc.showOpenDialog(EditProduct.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fc.getSelectedFile();
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));
                image.setIcon(imageIcon);
                path = file.getAbsolutePath();
            }

        }
    }

}
