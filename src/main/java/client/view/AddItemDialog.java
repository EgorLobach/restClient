package client.view;

import client.controller.ClientController;
import model.Author;
import model.Chapter;
import model.Item;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class AddItemDialog extends Dialog {


    AddItemDialog(ClientController clientController, String string) {
        super(clientController, string);
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    void action() {
        if(name.getText().isEmpty()){
            JOptionPane.showMessageDialog(dialog, "Field is empty",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String str = name.getText();
            List<Chapter> chapters = new ArrayList<>();
            Chapter chapter = new Chapter();
            chapter.setChapterName("new chapter");
            chapter.setText("");
            chapters.add(chapter);
            Author author = new Author();
            author.setFirstName("");
            author.setSecondName("");
            Item item = new Item();
            item.setAuthor(author);
            item.setChapters(chapters);
            item.setItemName(str);
            item.setYearOfPublication(2018);
            clientController.addItem(item);
            dialog.dispose();
        }
    }
}
