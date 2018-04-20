package client.view;


import client.controller.ClientController;
import model.Author;
import model.Chapter;
import model.Item;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static client.view.MainFrame.MAIN_FONT;


public class ItemPanel {

    private ClientController clientController;
    private String name;
    private JPanel itemPanel;

    private JLabel authorLabel = new JLabel("Author:");
    private JTextField authorFirstName;
    private JTextField authorSecondName;
    private JLabel yearOfPublicationLabel = new JLabel("Year of publication");
    private JTextField yearOfPublication;
    private MainFrame mainFrame;

    private JTabbedPane tabbedPane;

    private List<ChapterPanel> chapterPanels;

    ItemPanel(Item item, ClientController clientController, MainFrame mainFrame){
        this.clientController=clientController;
        this.mainFrame = mainFrame;
        itemPanel = new JPanel();
        name = item.getItemName();
        chapterPanels = new ArrayList<>();
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(MAIN_FONT);

        authorFirstName = new JTextField(item.getAuthor().getFirstName());
        authorSecondName = new JTextField(item.getAuthor().getSecondName());
        authorFirstName.setPreferredSize(new Dimension(200, 30));
        authorSecondName.setPreferredSize(new Dimension(200, 30));

        yearOfPublication = new JTextField("" + item.getYearOfPublication());
        yearOfPublication.setPreferredSize(new Dimension(50, 30));
        itemPanel.setLayout(new BorderLayout());

        for(Chapter chapter : item.getChapters()){
            chapterPanels.add(new ChapterPanel(chapter, this));
            tabbedPane.addTab(chapterPanels.get(chapterPanels.size()-1).getName(),chapterPanels.get(chapterPanels.size() - 1).getPanel());
        }

        JPanel topPanel = new JPanel();
        topPanel.add(authorLabel);
        topPanel.add(authorFirstName);
        topPanel.add(authorSecondName);
        topPanel.add(yearOfPublicationLabel);
        topPanel.add(yearOfPublication);
        itemPanel.add(topPanel, BorderLayout.NORTH);

        itemPanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton updateItemButton = new JButton("update Item");
        updateItemButton.addActionListener(e -> updateItem());
        buttonPanel.add(updateItemButton);

        JButton deleteItemButton = new JButton("delete Item");
        deleteItemButton.addActionListener(e -> deleteItem());
        buttonPanel.add(deleteItemButton);

        JButton addChapterButton = new JButton("add Chapter");
        addChapterButton.addActionListener(e -> {new AddChapterDialog(clientController, "add Chapter", this);});
        buttonPanel.add(addChapterButton);

        itemPanel.add(buttonPanel, BorderLayout.SOUTH);

        setFont();
    }

    void addChapter(String str) {
        Chapter chapter = new Chapter();
        chapter.setChapterName(str);
        chapter.setText("");
        chapterPanels.add(new ChapterPanel(chapter, this));
        tabbedPane.add(chapterPanels.get(chapterPanels.size()-1).getName(), chapterPanels.get(chapterPanels.size()-1).getPanel());
    }
    void deleteChapter(ChapterPanel chapterPanel){
        int i = chapterPanels.indexOf(chapterPanel);
        chapterPanels.remove(i);
        tabbedPane.remove(i);
    }

    private void deleteItem() {
        clientController.deleteItem(name);
        mainFrame.update();
    }

    private void updateItem() {
        Item item = new Item();
        item.setItemName(name);
        item.setYearOfPublication(Integer.parseInt(yearOfPublication.getText()));
        item.setAuthor(getAuthor());
        item.setChapters(getChapters());
        clientController.updateItem(item);
    }

    private Author getAuthor(){
        Author author = new Author();
        author.setFirstName(authorFirstName.getText());
        author.setSecondName(authorSecondName.getText());
        return author;
    }

    private void setFont(){
        authorFirstName.setFont(MAIN_FONT);
        authorSecondName.setFont(MAIN_FONT);
        yearOfPublication.setFont(MAIN_FONT);
        authorLabel.setFont(MAIN_FONT);
        yearOfPublicationLabel.setFont(MAIN_FONT);

    }

    public JPanel getPanel() {
        return itemPanel;
    }

    public String getName() {
        return name;
    }

    private List<Chapter> getChapters() {
        List<Chapter> chapters = new ArrayList<>();
        for (ChapterPanel chapterPanel : chapterPanels)
            chapters.add(chapterPanel.getChapter());
        return chapters;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public List<ChapterPanel> getChapterPanels() {
        return chapterPanels;
    }
}
