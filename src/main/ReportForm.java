package main;

import main.ReportGenerator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportForm {
    private JPanel panel1;
    private JButton listClanButton;
    private JTextField txtField_characterName;
    private JButton listSkillsForCharacterButton;
    private JButton listStatsForCharacterButton;
    private JButton listArmorWeaponsGemsButton;

    public ReportForm() {
        listClanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportGenerator.generateClanReport();
            }
        });
        listStatsForCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportGenerator.generateCharachterStatsReport(txtField_characterName.getText());
            }
        });
        listSkillsForCharacterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportGenerator.generateUseableSkillsReport(txtField_characterName.getText());
            }
        });
        listArmorWeaponsGemsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportGenerator.generateEquipmentReport(txtField_characterName.getText());
            }
        });
    }

    public JPanel getPanel(){
        return panel1;
    }
}
