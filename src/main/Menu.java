package main;

import com.jgoodies.common.collect.ArrayListModel;
import main.API;
import main.models.CharacterModel;
import main.models.SkillModel;
import main.CreateWeapon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class Menu extends JPanel{
    private JTabbedPane tabbedPane1;
    private JComboBox<CharacterModel> comboBox_characters;
    private JComboBox comboBox_skills;
    private JPanel panel;
    private JPanel Form;
    private JButton btn_addSkill;
    private JTextField txtField_class;
    private JTextField txtField_charLevel;
    private JTextField txtField_requiredLevel;
    private JList list_earnedSkills;

    private static CharacterModel currSelectedCharacter;
    private static SkillModel currSelectedSkill;

    public Menu() {
        tabbedPane1.addTab("Create Weapon", new CreateWeapon().getPanel());
        tabbedPane1.addTab("Modify Gems", new GemForm().getPanel());
        tabbedPane1.addTab("Modify Base Stats", new AssignBaseStats().getPanel());

        tabbedPane1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                CharacterModel[] chars = API.getAllCharacters().toArray(new CharacterModel[0]);
                ComboBoxModel<CharacterModel> model = new DefaultComboBoxModel<>(chars);
                comboBox_characters.setModel(model);
            }
        });
        comboBox_characters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currSelectedCharacter = (CharacterModel)comboBox_characters.getSelectedItem();
                currSelectedSkill = null;
                newCharacterSelected();
            }
        });

        comboBox_skills.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newSkillSelected();
            }
        });
        btn_addSkill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.addSkill(currSelectedCharacter.getCharName(), currSelectedSkill.getSkillId());

                //update has-earned list.
                List<String> earnedSkills = API.getEarnedSkills(currSelectedCharacter.getCharName());
                list_earnedSkills.setModel(new ArrayListModel<>(earnedSkills));

                //update button.
                newSkillSelected();
            }
        });
    }

    private boolean isValidSkillToAdd(){
        ListModel<String> list = list_earnedSkills.getModel();
        for (int i = 0; i < list.getSize(); i++)
            if (list.getElementAt(i).equals(currSelectedSkill.getSname())) return false;

        if (currSelectedSkill.getMinLevel() > currSelectedCharacter.getCharLevel()) return false;

        return true;
    }

    private void newCharacterSelected(){
        btn_addSkill.setEnabled(false);

        //update skills combobox.
        SkillModel[] skills = API.getSkills(currSelectedCharacter.getHasClass()).toArray(new SkillModel[0]);
        comboBox_skills.setModel(new DefaultComboBoxModel<>(skills));

        //update text fields.
        txtField_class.setText(currSelectedCharacter.getHasClass());
        txtField_charLevel.setText(currSelectedCharacter.getCharLevel().toString());
        txtField_requiredLevel.setText("");

        //update has-earned list.
        List<String> earnedSkills = API.getEarnedSkills(currSelectedCharacter.getCharName());
        list_earnedSkills.setModel(new ArrayListModel<>(earnedSkills));

    }

    public void newSkillSelected(){
        currSelectedSkill= (SkillModel)comboBox_skills.getSelectedItem();
        txtField_requiredLevel.setText(currSelectedSkill.getMinLevel().toString());
        if (isValidSkillToAdd())
            btn_addSkill.setEnabled(true);
        else
            btn_addSkill.setEnabled(false);
    }


    public JPanel getPanel(){
        return panel;
    }
}
