import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StylishShoppingCartApp {
    private JFrame frame;
    private DefaultListModel<String> cartListModel;
    private JLabel totalLabel;
    private double total = 0.0;

   
    private String[] availableItems = {"Apple - ₹10", "Banana - ₹5", "Milk - ₹30", "Bread - ₹25", "Orange - ₹15"};
    private double[] itemPrices = {10.0, 5.0, 30.0, 25.0, 15.0};

    public StylishShoppingCartApp() {
       
        frame = new JFrame("Shopping Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        
        
        frame.getContentPane().setBackground(Color.decode("#f7f7f7"));
        
        
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#4CAF50"));
        JLabel titleLabel = new JLabel("Shopping Cart");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);
        
        // Available items panel
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(availableItems.length, 1, 5, 5));
        itemsPanel.setBackground(Color.decode("#eeeeee"));
        itemsPanel.setBorder(BorderFactory.createTitledBorder("Available Items"));
        frame.add(itemsPanel, BorderLayout.WEST);
        
        for (int i = 0; i < availableItems.length; i++) {
            JButton itemButton = new JButton(availableItems[i]);
            itemButton.setBackground(Color.decode("#4CAF50"));
            itemButton.setForeground(Color.WHITE);
            itemButton.setFont(new Font("Arial", Font.PLAIN, 16));
            itemButton.setFocusPainted(false);
            int itemIndex = i;  // Capture the index
            itemButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addItemToCart(itemIndex);
                }
            });
            itemsPanel.add(itemButton);
        }

        // Shopping cart panel
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new BorderLayout());
        cartPanel.setBackground(Color.WHITE);
        cartPanel.setBorder(BorderFactory.createTitledBorder("Your Cart"));

        cartListModel = new DefaultListModel<>();
        JList<String> cartList = new JList<>(cartListModel);
        cartList.setFont(new Font("Arial", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(cartList);
        cartPanel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Total: ₹0.0");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalLabel.setForeground(Color.decode("#333333"));
        totalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        totalLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        cartPanel.add(totalLabel, BorderLayout.SOUTH);

        frame.add(cartPanel, BorderLayout.CENTER);

        // Buttons panel to hold the remove and buy buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.decode("#f7f7f7"));
        
        // Remove item button
        JButton removeButton = new JButton("Remove Selected Item");
        removeButton.setBackground(Color.decode("#FF5252"));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFont(new Font("Arial", Font.PLAIN, 16));
        removeButton.setFocusPainted(false);
        removeButton.setPreferredSize(new Dimension(200, 40));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cartList.getSelectedIndex();
                if (selectedIndex != -1) {
                    removeItemFromCart(selectedIndex);
                }
            }
        });

        // Buy button
        JButton buyButton = new JButton("Buy Now");
        buyButton.setBackground(Color.decode("#4CAF50"));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.PLAIN, 16));
        buyButton.setFocusPainted(false);
        buyButton.setPreferredSize(new Dimension(200, 40));
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartListModel.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Your cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Total Price: ₹" + total, "Purchase Complete", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Adding buttons to panel
        buttonPanel.add(removeButton);
        buttonPanel.add(buyButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // Add item to the cart
    private void addItemToCart(int index) {
        cartListModel.addElement(availableItems[index]);
        total += itemPrices[index];
        updateTotal();
    }

    // Remove item from the cart
    private void removeItemFromCart(int index) {
        String selectedItem = cartListModel.getElementAt(index);
        int itemIndex = getItemIndex(selectedItem);
        if (itemIndex != -1) {
            total -= itemPrices[itemIndex];
            cartListModel.remove(index);
            updateTotal();
        }
    }

    // Get item index by its name
    private int getItemIndex(String item) {
        for (int i = 0; i < availableItems.length; i++) {
            if (availableItems[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    // Update the total price display
    private void updateTotal() {
        totalLabel.setText("Total: ₹" + total);
    }

    public static void main(String[] args) {
        new StylishShoppingCartApp();
    }
}
