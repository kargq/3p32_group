package main;

import com.jgoodies.common.collect.ArrayListModel;
import main.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GemForm extends JPanel{
    private JPanel panel1;
    private JComboBox<ArmourInstanceModel> comboBox_armorInstance;
    private JTextField txtField_equipName;
    private JTextField txtField_gemLimit;
    private JTextField txtField_gemLife;
    private JComboBox<CharacterModel> comboBox_characters;
    private JButton btn_equipArmor;
    private JPanel panel_gemWrapper;
    private JButton btn_updateGem;
    private JButton btn_removeGem;
    private JTextField txtField_class;
    private JTextField txtField_gemPower;
    private JTextField txtField_gemName;
    private JTextField txtField_gemSpeed;
    private JTextField txtField_gemBlock;
    private JTextField txtField_gemDefense;
    private JTextField txtField_gemWill;
    private JTextField txtField_gemStrength;
    private JTextField txtField_gemLevelEditor;
    private JTextField txtField_currEquippedArmor;
    private JTextField txtField_currEquippedArmorInstanceId;
    private JComboBox comboBox_allGems;
    private JButton addGemButton;
    private JComboBox comboBox_gemEditorAllGems;
    private JList jList_attachedGems;
    private JTextField txtField_gemLevel;
    private JTextField txtField_equipLevel;

    private static ArmourInstanceModel selectedArmourInstance;
    private static EquipmentModel currEquipment;
    private static CharacterModel selectedCharacter;
    private static GemModel selectedGem;

    public GemForm(){
        comboBox_armorInstance.setModel(new DefaultComboBoxModel<>(API.getAllArmourInstances().toArray(new ArmourInstanceModel[0])));
        comboBox_characters.setModel(new DefaultComboBoxModel<>(API.getAllCharacters().toArray(new CharacterModel[0])));
        comboBox_gemEditorAllGems.setModel(new DefaultComboBoxModel<>(API.getAllGems().toArray(new GemModel[0])));
        comboBox_allGems.setModel(new DefaultComboBoxModel<>(API.getAllGems().toArray(new GemModel[0])));
        btn_updateGem.setEnabled(false);

        //armour instance selected.
        comboBox_armorInstance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedArmourInstance = (ArmourInstanceModel)comboBox_armorInstance.getSelectedItem();
                currEquipment = API.getEquipment(selectedArmourInstance.getEqpId());
                txtField_gemLimit.setText(currEquipment.getGemLimit().toString());
                txtField_equipName.setText(currEquipment.getEqpName());
                jList_attachedGems.setModel(new ArrayListModel<>(API.getAttachedGems(selectedArmourInstance)));
                txtField_equipLevel.setText(currEquipment.getElevel().toString());
            }
        });

        //character selected.
        comboBox_characters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCharacter = API.getCharacter(((CharacterModel)comboBox_characters.getSelectedItem()).getCharName());
                txtField_class.setText(selectedCharacter.getHasClass());
                updateCurrArmorFields();
            }
        });

        btn_updateGem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGem.setGlife(Integer.valueOf(txtField_gemLife.getText()));
                selectedGem.setGpower(Integer.valueOf(txtField_gemPower.getText()));
                selectedGem.setGname(txtField_gemName.getText());
                selectedGem.setGspeed(Integer.valueOf(txtField_gemSpeed.getText()));
                selectedGem.setGdefence(Integer.valueOf(txtField_gemDefense.getText()));
                selectedGem.setGwill(Integer.valueOf(txtField_gemWill.getText()));
                selectedGem.setGstrength(Integer.valueOf(txtField_gemStrength.getText()));
                selectedGem.setGlevel(Integer.valueOf(txtField_gemLevelEditor.getText()));
                selectedGem.setGblock(Integer.valueOf(txtField_gemBlock.getText()));

                API.updateGem(selectedGem);
                comboBox_allGems.setModel(new DefaultComboBoxModel<>(API.getAllGems().toArray(new GemModel[0])));
            }
        });

        //equip armor
        btn_equipArmor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.equipArmor(selectedCharacter, selectedArmourInstance);
                updateCurrArmorFields();
            }
        });

        btn_removeGem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.removeGem((GemModel)jList_attachedGems.getSelectedValue(), selectedArmourInstance);
                jList_attachedGems.setModel(new ArrayListModel<>(API.getAttachedGems(selectedArmourInstance)));
            }
        });
        comboBox_gemEditorAllGems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedGem = API.getGem((GemModel)comboBox_gemEditorAllGems.getSelectedItem());
                GemModel g = selectedGem;
                txtField_gemLife.setText(g.getGlife().toString());
                txtField_gemPower.setText(g.getGpower().toString());
                txtField_gemName.setText(g.getGname());
                txtField_gemSpeed.setText(g.getGspeed().toString());
                txtField_gemDefense.setText(g.getGdefence().toString());
                txtField_gemWill.setText(g.getGwill().toString());
                txtField_gemStrength.setText(g.getGstrength().toString());
                txtField_gemLevelEditor.setText(g.getGlevel().toString());
                txtField_gemBlock.setText(g.getGblock().toString());
                btn_updateGem.setEnabled(true);
            }
        });
        addGemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.addGemToArmor(selectedArmourInstance, (GemModel)comboBox_allGems.getSelectedItem());
                jList_attachedGems.setModel(new ArrayListModel<>(API.getAttachedGems(selectedArmourInstance)));
            }
        });
        comboBox_allGems.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField_gemLevel.setText(((GemModel)comboBox_allGems.getSelectedItem()).getGlevel().toString());
            }
        });
    }

    private void updateCurrArmorFields(){
        txtField_currEquippedArmor.setText("");
        txtField_currEquippedArmorInstanceId.setText("");

        EquipmentModel armour = API.getArmourEquipped(selectedCharacter);
        if (armour != null) //prints name of armour.
            txtField_currEquippedArmor.setText(armour.getEqpName());

        if (selectedCharacter.getArmourEquipped() != null) //prints instance id of armour.
            txtField_currEquippedArmorInstanceId.setText(selectedCharacter.getArmourEquipped().toString());

    }

    public JPanel getPanel(){
        return panel1;
    }
}
