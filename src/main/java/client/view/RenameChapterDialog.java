package client.view;

import javax.swing.*;

class RenameChapterDialog extends Dialog {
    private ItemPanel itemPanel;
    private ChapterPanel chapterPanel;
    RenameChapterDialog(ItemPanel itemPanel, String string, ChapterPanel chapterPanel) {
        super(itemPanel.getClientController(), string);
        this.itemPanel = itemPanel;
        this.chapterPanel = chapterPanel;
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
            chapterPanel.setName(str);
            itemPanel.getTabbedPane().setTitleAt(itemPanel.getChapterPanels().indexOf(chapterPanel), str);
            dialog.dispose();
        }
    }
}
