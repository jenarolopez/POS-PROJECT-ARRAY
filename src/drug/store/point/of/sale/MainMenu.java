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
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import javax.swing.BorderFactory;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

/**
 *
 * @author User
 */
public class MainMenu extends JFrame implements ActionListener {

    JMenuBar menubar = new JMenuBar();

    public static JMenu menu = new JMenu("Menu");

    JMenuItem details = new JMenuItem("My details");
    JMenuItem changepw = new JMenuItem("Change password");
    JMenu client = new JMenu("Client users");

    JMenuItem viewClient = new JMenuItem("View all client");

    public static JPanel sidePanel = new JPanel();

    public static JPanel productPanel = new JPanel();
    public static JPanel[] itemPanel = new JPanel[100];
    public static JButton[] itemDetails = new JButton[100];
    public static JButton[] itemCart = new JButton[100];
    public static JLabel[] image = new JLabel[100];
    public static JLabel[] name = new JLabel[100];
    public static JLabel[] stock = new JLabel[100];
    public static JLabel cartCount = new JLabel();

    public static JScrollPane pane = new JScrollPane(productPanel);

    JButton transaction = new JButton("Transaction");
    JButton addproduct = new JButton("Add Product");
    JButton updateproduct = new JButton("Update Product");
    public static JButton deleteproduct = new JButton("Delete Product");
    public static JButton cancel = new JButton("Cancel Transaction");
     JButton checkout = new JButton("Checkout Product");

    JPanel optionPnl = new JPanel();

    MainMenu() {

        menu.removeAll();

        setJMenuBar(menubar);
        setLayout(null);
        setResizable(false);
        setSize(1300, 700);
        setLocationRelativeTo(null);
        setVisible(true);
        setVisible(true);
        setTitle("Drug Store POS");
        setResizable(false);
        setLocationRelativeTo(null);
        updateproducts();

        cancel.setBounds(1097, 20, 150, 24);
        checkout.setBounds(1097 - cancel.getWidth() - 10, 20, 150, 24);
        cartCount.setBounds(830, 20, 150, 24);
        cartCount.setText("Cart Count: " + Transaction.cartCount + " ");

        if (UserVariables.isAdmin[UserVariables.currentUser] == true) {
            optionPnl.add(transaction);
            optionPnl.add(addproduct);
            optionPnl.add(updateproduct);
            optionPnl.add(deleteproduct);
            deleteproduct.setVisible(true);
            client.setVisible(true);
        } else {
            optionPnl.add(transaction);
            optionPnl.add(addproduct);
            optionPnl.add(updateproduct);
            optionPnl.add(deleteproduct);
            updateproduct.setVisible(false);
            deleteproduct.setVisible(false);
            client.setVisible(false);
        }

        add(cancel);
        add(checkout);
        add(cartCount);
        cancel.addActionListener(this);
        checkout.addActionListener(this);

        transaction.addActionListener(this);
        addproduct.addActionListener(this);
        deleteproduct.addActionListener(this);
        updateproduct.addActionListener(this);

        optionPnl.setBounds(100, 130, 200, 250);
        optionPnl.setLayout(new GridLayout(0, 1, 30, 20));

        add(optionPnl);

        menu.setText(UserVariables.firstname[UserVariables.currentUser]);

        menu.add(details);
        details.addActionListener(this);
        changepw.addActionListener(this);
        viewClient.addActionListener(this);
        menu.add(changepw);

        client.add(viewClient);

        menubar.add(menu);
        menubar.add(client);

        sidePanel.setBounds(400, 30, 850, 580);
        sidePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "All Product", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 30)));

        sidePanel.add(pane);
        sidePanel.setLayout(null);
        add(sidePanel);

        cancel.setEnabled(false);
       
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                Login log = new Login();
                log.setVisible(true);
                dispose();
            }
        });

    }

    public static void closeTransaction() {

        cancel.setEnabled(false);
        

    }

    public static void updateproducts() {

        String[][] products = ProductVariables.viewProduct();
        
        cartCount.setText("Cart Count: " + Transaction.cartCount + " ");
        productPanel.removeAll();
        int height = 210;
        int productCount = products.length / 4;
        int z = products.length % 4;
        if (z != 0) {
            productCount++;
        }
        if (productCount == 0) {
            height = 220;
        } else {
            height = 220 * productCount + 1;
            if (height >= 530) {
                height = 530;
            }
        }

        pane.setBounds(10, 40, 830, height);
        pane.setBorder(createEmptyBorder());

        productPanel.setLayout(new GridLayout(0, 4, 8, 10));

        for (int x = 0; x < products.length; x++) {

            itemPanel[x] = new JPanel();
            itemPanel[x].setBackground(Color.LIGHT_GRAY);
            itemPanel[x].setPreferredSize(new Dimension(199, 200));
            itemPanel[x].setLayout(null);
            itemPanel[x].setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 10)));

            image[x] = new JLabel();
            image[x].setBounds(20, 10, 180 - 20, 130 - 20);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(products[x][5]).getImage().getScaledInstance(image[x].getWidth(), image[x].getHeight(), Image.SCALE_DEFAULT));
            image[x].setIcon(imageIcon);

            name[x] = new JLabel(products[x][1]);
            name[x].setBounds(0, 130, 199, 20);
            name[x].setHorizontalAlignment(JLabel.CENTER);
            name[x].setFont(new Font(name[x].getFont().toString(), Font.PLAIN, 16));

            stock[x] = new JLabel("Stock: " + products[x][4]);
            stock[x].setBounds(0, 148, 199, 20);
            stock[x].setHorizontalAlignment(JLabel.CENTER);
            stock[x].setFont(new Font(stock[x].getFont().toString(), Font.PLAIN, 10));

            itemDetails[x] = new JButton("Details");
            itemDetails[x].setBounds(10, 170, 80, 24);

            itemCart[x] = new JButton("Add Cart");
            itemCart[x].setBounds(95, 170, 95, 24);
            itemCart[x].setEnabled(false);

            final int productId = parseInt(products[x][0] + "");
            itemCart[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Transaction.addCart(productId);
                }
            });
            final int ctrl = x;

            itemDetails[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ProductVariables.getProduct(ctrl);

                }
            });

            itemPanel[x].add(itemCart[x]);

            itemPanel[x].add(itemDetails[x]);

            itemPanel[x].add(name[x]);
            itemPanel[x].add(image[x]);
            itemPanel[x].add(stock[x]);

            productPanel.add(itemPanel[x]);

        }
        productPanel.revalidate();

    }

    public static void updatecart() {

        String[][] products = ProductVariables.viewProduct();

        cartCount.setText("Cart Count: " + Transaction.cartCount + " ");

        productPanel.removeAll();
        int height = 210;
        int productCount = products.length / 4;
        int z = products.length % 4;
        if (z != 0) {
            productCount++;
        }
        if (productCount == 0) {
            height = 220;
        } else {
            height = 220 * productCount + 1;
            if (height >= 530) {
                height = 530;
            }
        }

        pane.setBounds(10, 40, 830, height);
        pane.setBorder(createEmptyBorder());

        productPanel.setLayout(new GridLayout(0, 4, 8, 10));

        for (int x = 0; x < products.length; x++) {

            itemPanel[x] = new JPanel();
            itemPanel[x].setBackground(Color.LIGHT_GRAY);
            itemPanel[x].setPreferredSize(new Dimension(199, 200));
            itemPanel[x].setLayout(null);
            itemPanel[x].setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Adobe Arabic", 1, 10)));

            image[x] = new JLabel();
            image[x].setBounds(20, 10, 180 - 20, 130 - 20);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(products[x][5]).getImage().getScaledInstance(image[x].getWidth(), image[x].getHeight(), Image.SCALE_DEFAULT));
            image[x].setIcon(imageIcon);

            name[x] = new JLabel(products[x][1]);
            name[x].setBounds(0, 130, 199, 20);
            name[x].setHorizontalAlignment(JLabel.CENTER);
            name[x].setFont(new Font(name[x].getFont().toString(), Font.PLAIN, 16));

            stock[x] = new JLabel("Stock: " + products[x][4]);
            stock[x].setBounds(0, 148, 199, 20);
            stock[x].setHorizontalAlignment(JLabel.CENTER);
            stock[x].setFont(new Font(stock[x].getFont().toString(), Font.PLAIN, 10));

            itemDetails[x] = new JButton("Details");
            itemDetails[x].setBounds(10, 170, 80, 24);

            itemCart[x] = new JButton("Add Cart");
            itemCart[x].setBounds(95, 170, 95, 24);

            if (products[x][4].equals("0")) {
                itemCart[x].setEnabled(false);
            }

            final int productId = parseInt(products[x][0] + "");
            itemCart[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Transaction.addCart(productId);
                }
            });
            final int ctrl = x;

            itemDetails[x].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ProductVariables.getProduct(ctrl);

                }
            });

            itemPanel[x].add(itemCart[x]);

            itemPanel[x].add(itemDetails[x]);

            itemPanel[x].add(name[x]);
            itemPanel[x].add(image[x]);
            itemPanel[x].add(stock[x]);

            productPanel.add(itemPanel[x]);

        }
        productPanel.revalidate();

    }

    public static void main(String[] args) {

        MainMenu menu = new MainMenu();
        menu.setVisible(true);

    }

    public void actionPerformed(ActionEvent a) {
        if (a.getSource() == cancel) {
            for (int x = 0; x < 100; x++) {
                if (itemCart[x] != null) {
                    itemCart[x].setEnabled(false);
                }
            }
            Transaction.cancel();
            Transaction.clearcart();
            cancel.setEnabled(false);
            deleteproduct.setEnabled(true);
           
        }
        if (a.getSource() == transaction) {
            for (int x = 0; x < 100; x++) {
                if (itemCart[x] != null) {
                    itemCart[x].setEnabled(true);

                }

            }
            deleteproduct.setEnabled(false);
            cancel.setEnabled(true);
          
        }
        if (a.getSource() == checkout) {
            Cart b = new Cart();
            b.setVisible(true);

        }
        if (a.getSource() == details) {
            UserDetails users = new UserDetails();
            users.setVisible(true);

        }
        if (a.getSource() == changepw) {
            ChangePassword users = new ChangePassword();
            users.setVisible(true);

        }
        if (a.getSource() == viewClient) {
            Accounts acc = new Accounts();
            acc.setVisible(true);

        }
        if (a.getSource() == addproduct) {
            AddProduct b = new AddProduct();
            b.setVisible(true);
        }
        if (a.getSource() == deleteproduct) {
            DeleteProduct b = new DeleteProduct();
            b.setVisible(true);
        }
        if (a.getSource() == updateproduct) {
            EditProducts b = new EditProducts();
            b.setVisible(true);
        }

    }
}
