package client.view;


import client.controller.ClientController;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame {

    static Font MAIN_FONT = new Font("", Font.ITALIC, 17);

    private ClientController clientController;

    private JFrame mainFrame;
    private JTabbedPane tabbedPane;
    private List<ItemPanel> itemPanels;

    public MainFrame(ClientController clientController){
        itemPanels = new ArrayList<ItemPanel>();
        this.clientController = clientController;
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(MAIN_FONT);
        mainFrame = new JFrame("Rest Client");
        mainFrame.setSize(new Dimension(1440, 900));
        mainFrame.setPreferredSize(mainFrame.getSize());
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        init();
    }

    private void init(){
        update();
        mainFrame.add(tabbedPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();

        JButton updateButton = new JButton("update");
        updateButton.addActionListener(e -> update());
        buttonPanel.add(updateButton);

        JButton addItemButton = new JButton("add Item");
        addItemButton.addActionListener(e -> addItem());
        buttonPanel.add(addItemButton);

        mainFrame.add(buttonPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }


    void update() {
        tabbedPane.removeAll();
        for (Item item : clientController.getItems()) {
            itemPanels.add(new ItemPanel(item, clientController, this));
            tabbedPane.add(itemPanels.get(itemPanels.size() - 1).getName(), itemPanels.get(itemPanels.size() - 1).getPanel());
        }
        mainFrame.validate();
        mainFrame.repaint();
    }

    private void addItem() {
        new AddItemDialog(clientController, "add Item");
        update();
    }

}
