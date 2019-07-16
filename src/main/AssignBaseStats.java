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
    private JPanel panel_classWrapper;
    private JButton btn_updateClass;
    private JPanel panel_classForm;
    private JComboBox comboBox_baseStatsClass;
    private JTextField txtField_class;
    private JTextField txtField_life;
    private JTextField txtField_power;
    private JTextField txtField_strength;
    private JTextField txtField_defense;
    private JTextField txtField_will;
    private JTextField txtField_speed;

    private final String EARNED = "Earned";
    private final String AUTO = "Auto";

    private static ClazzModel currClass;

    public AssignBaseStats() {
        comboBox_skills.setModel(new DefaultComboBoxModel(API.getAllSkills().toArray(new SkillModel[0])));
        comboBox_class.setModel(new DefaultComboBoxModel(API.getAllClasses().toArray(new ClazzModel[0])));
        comboBox_skillType.setModel(new DefaultComboBoxModel(new String[]{EARNED, AUTO}));
        comboBox_baseStatsClass.setModel(new DefaultComboBoxModel(API.getAllClasses().toArray(new ClazzModel[0])));

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
        btn_updateClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.updateClass(createClassFromFields());
                comboBox_class.setModel(new DefaultComboBoxModel(API.getAllClasses().toArray(new ClazzModel[0])));
                comboBox_baseStatsClass.setModel(new DefaultComboBoxModel(API.getAllClasses().toArray(new ClazzModel[0])));
            }
        });
        comboBox_baseStatsClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currClass = API.getClass(((ClazzModel)comboBox_baseStatsClass.getSelectedItem()).getClsName());
                txtField_class.setText(currClass.getClsName());
                txtField_life.setText(currClass.getBaseLife().toString());
                txtField_power.setText(currClass.getBasePower().toString());
                txtField_strength.setText(currClass.getBaseStrength().toString());
                txtField_defense.setText(currClass.getBaseStrength().toString());
                txtField_will.setText(currClass.getBaseWill().toString());
                txtField_speed.setText(currClass.getBaseSpeed().toString());
            }
        });
        btn_updateClass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClazzModel item = createClassFromFields();
                API.updateClass(item);
            }
        });
    }

    private ClazzModel createClassFromFields(){
        ClazzModel clazz = new ClazzModel();
        clazz.setClsName(txtField_class.getText());
        clazz.setBaseLife(Integer.valueOf(txtField_life.getText()));
        clazz.setBasePower(Integer.valueOf(txtField_power.getText()));
        clazz.setBaseStrength(Integer.valueOf(txtField_strength.getText()));
        clazz.setBaseDefence(Integer.valueOf(txtField_defense.getText()));
        clazz.setBaseWill(Integer.valueOf(txtField_will.getText()));
        clazz.setBaseSpeed(Integer.valueOf(txtField_speed.getText()));
        return clazz;
    }

    public JPanel getPanel(){
        return panel1;
    }
}
