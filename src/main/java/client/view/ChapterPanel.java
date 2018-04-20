package client.view;

import model.Chapter;

import javax.swing.*;
import java.awt.*;

import static client.view.MainFrame.MAIN_FONT;

public class ChapterPanel {
    private JPanel chapterPanel;
    private String name;
    private JTextArea text;
    private ItemPanel itemPanel;

    ChapterPanel(Chapter chapter, ItemPanel itemPanel) {
        this.itemPanel = itemPanel;
        chapterPanel = new JPanel();
        chapterPanel.setLayout(new BorderLayout());
        name = chapter.getChapterName();
        text = new JTextArea(chapter.getText());
        text.setFont(MAIN_FONT);
        chapterPanel.add(text, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton renameChapterButton = new JButton("rename Chapter");
        renameChapterButton.addActionListener(e -> renameChapter());
        buttonPanel.add(renameChapterButton);
        JButton deleteChapterButton = new JButton("delete Chapter");
        deleteChapterButton.addActionListener(e -> deleteChapter());
        buttonPanel.add(deleteChapterButton);
        chapterPanel.add(buttonPanel, BorderLayout.SOUTH);

    }

    private void renameChapter() {
        new RenameChapterDialog(itemPanel, "rename Chapter", this);
    }

    private void deleteChapter() {
        itemPanel.deleteChapter(this);
    }

    Chapter getChapter(){
        Chapter chapter = new Chapter();
        chapter.setChapterName(name);
        chapter.setText(text.getText());
        return chapter;
    }

    public String getName() {
        return name;
    }

    public Component getPanel() {
        return chapterPanel;
    }

    public void setName(String name) {
        this.name = name;
    }
}
