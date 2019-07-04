import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Menu extends JPanel{
    private JTabbedPane tabbedPane1;
    private JComboBox comboBox_characters;
    private JComboBox comboBox_skills;
    private JPanel panel;
    private JPanel Form;
    private JButton btn_addSkill;
    private JTextField txtField_class;
    private JTextField txtField_charLevel;
    private JTextField txtField_requiredLevel;

    public Menu() {
        tabbedPane1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
