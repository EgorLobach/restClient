package client.view;

import client.controller.ClientController;

import javax.swing.*;
import java.awt.*;

import static client.view.MainFrame.MAIN_FONT;

abstract class Dialog {
    JDialog dialog = new JDialog();

    JTextField name = new JTextField(30);
    ClientController clientController;

    Dialog(ClientController clientController, String string) {
        this.clientController = clientController;
        dialog.setSize(300, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        dialog.setTitle(string);
        JButton button = new JButton(string);
        JLabel nameLabel = new JLabel("Name");

        nameLabel.setFont(MAIN_FONT);
        name.setFont(MAIN_FONT);

        dialog.add(nameLabel, BorderLayout.NORTH);
        dialog.add(name, BorderLayout.CENTER);
        dialog.add(button, BorderLayout.SOUTH);

        button.addActionListener(e -> action());
    }

    abstract void action();
}
