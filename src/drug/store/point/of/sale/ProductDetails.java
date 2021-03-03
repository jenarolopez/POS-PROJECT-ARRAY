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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class ProductDetails extends JFrame implements ActionListener {

    JLabel image = new JLabel();
    JLabel name = new JLabel("Mefgenamein");
    JLabel brand = new JLabel("RITEMED");
    JLabel description = new JLabel("LOL");
    JLabel price = new JLabel("200.00");
    JButton close = new JButton("Close");
    JPanel pnl1 = new JPanel();

    ProductDetails() {
        image.setBounds(20, 30, 200, 200);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(ProductVariables.image[ProductVariables.currentProduct]).getImage().getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_DEFAULT));

        image.setIcon(imageIcon);
       

        pnl1.setLayout(new GridLayout(0, 1, 10, 10));

        name.setText("Name: \t" + ProductVariables.productname[ProductVariables.currentProduct]);
        brand.setText("Brand: \t" + ProductVariables.productbrand[ProductVariables.currentProduct]);
        description.setText("Description: \t" + ProductVariables.productdescription[ProductVariables.currentProduct]);
        price.setText("Price: \t" + ProductVariables.productdescription[ProductVariables.currentProduct]);
        pnl1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Product Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 16)));
        close.setBounds(363, 200, 80, 24);
        close.addActionListener(this);
        add(close);
        pnl1.add(name);
        pnl1.add(brand);
        pnl1.add(description);
        pnl1.add(price);
        pnl1.setBounds(250, 30, 200, 150);
        add(pnl1);
        add(image);
        setLayout(null);
        setResizable(false);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Product Details");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
      
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == close) {
            dispose();
        }
    }

}
