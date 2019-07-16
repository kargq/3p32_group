package main;

import main.models.ClazzModel;
import main.models.SkillModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignBaseStats {
    private JPanel panel1;
    private JPanel panel_skills;
    private JComboBox comboBox_skills;
    private JComboBox comboBox_class;
    private JComboBox comboBox_skillType;
    private JPanel panel_skillsWrapper;
    private JButton btn_addSkill;

    private final String EARNED = "Earned";
    private final String AUTO = "Auto";

    public AssignBaseStats() {
        comboBox_skills.setModel(new DefaultComboBoxModel(API.getAllSkills().toArray(new SkillModel[0])));
        comboBox_class.setModel(new DefaultComboBoxModel(API.getAllClasses().toArray(new ClazzModel[0])));
        comboBox_skillType.setModel(new DefaultComboBoxModel(new String[]{EARNED, AUTO}));

        btn_addSkill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkillModel skill = (SkillModel)comboBox_skills.getSelectedItem();
                ClazzModel clazz = (ClazzModel)comboBox_class.getSelectedItem();
                String type = (String)comboBox_skillType.getSelectedItem();

                if (type.compareTo(EARNED) == 0)
                    API.addSkillToEarned(skill, clazz);
                else if (type.compareTo(AUTO) == 0)
                    API.addSkillToAuto(skill, clazz);
            }
        });
    }
}
