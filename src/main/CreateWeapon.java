package main;

import main.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

public class CreateWeapon extends JPanel{
    private JPanel panel;
    private JTextField txtField_weaponName;
    private JTextField txtField_will;
    private JTextField txtField_speed;
    private JTextField txtField_defense;
    private JTextField txtField_strength;
    private JTextField txtField_block;
    private JTextField txtField_power;
    private JTextField txtField_life;
    private JTextField txtField_level;
    private JTextField txtField_gemLimit;
    private JTextField txtField_currSecondaryEquip;
    private JTextField txtField_currMainEquip;
    private JTextField txtField_class;
    private JButton btn_createWeapon;
    private JButton equipWeaponButton;
    private JButton addEquipmentToClassButton;

    private JComboBox comboBox_weaponToEquip;
    private JComboBox comboBox_slot;
    private JComboBox<CharacterModel> comboBox_characters;
    private JComboBox<EquipmentModel> comboBox_allEquipment;
    private JComboBox<ClazzModel> comboBox_allClasses;
    private JComboBox comboBox_weaponType;
    private JFormattedTextField formattedTextField1;

    public static EquipmentModel selectedEquipment;
    public static ClazzModel selectedClass;
    public static CharacterModel selectedCharacter;

    public static EquipmentModel selectedEquipmentToEquip;
    public static String selectedEquipmentSlot;
    public static String selectedWeaponType;

    public NumberFormat numFormat = NumberFormat.getNumberInstance();
    public CreateWeapon() {

        comboBox_allEquipment.setModel(new DefaultComboBoxModel<>(API.getAllEquipment().toArray(new EquipmentModel[0])));
        comboBox_allClasses.setModel(new DefaultComboBoxModel<>(API.getAllClasses().toArray(new ClazzModel[0])));
        comboBox_characters.setModel(new DefaultComboBoxModel<>(API.getAllCharacters().toArray(new CharacterModel[0])));
        comboBox_slot.setModel(new DefaultComboBoxModel<>(new String[]{"Main", "Secondary"}));
        comboBox_weaponType.setModel(new DefaultComboBoxModel<>(new String[]{"Armour", "Main", "Secondary"}));

        btn_createWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int equipId = new Random().nextInt();

                EquipmentModel item = new EquipmentModel();
                item.setEqpId(equipId);
                item.setEqpName(txtField_weaponName.getText());
                item.setEwill(Integer.valueOf(txtField_will.getText()));
                item.setEspeed(Integer.valueOf(txtField_speed.getText()));
                item.setEdefence(Integer.valueOf(txtField_defense.getText()));
                item.setEstrength(Integer.valueOf(txtField_strength.getText()));
                item.setEblcok(Integer.valueOf(txtField_block.getText()));
                item.setEpower(Integer.valueOf(txtField_power.getText()));
                item.setElife(Integer.valueOf(txtField_life.getText()));
                item.setElevel(Integer.valueOf(txtField_level.getText()));
                item.setGemLimit(Integer.valueOf(txtField_gemLimit.getText()));

                if (selectedWeaponType.compareTo("Armour") == 0){
                    ArmourModel wepType = new ArmourModel();
                    wepType.setEqpId(equipId);
                    API.createEquipment(item, wepType);
                } else if (selectedWeaponType.compareTo("Main") == 0){
                    MainWeaponModel wepType = new MainWeaponModel();
                    wepType.setEqpId(equipId);
                    API.createEquipment(item, wepType);
                } else if (selectedWeaponType.compareTo("Secondary") == 0){
                    SecondaryEquipmentModel wepType = new SecondaryEquipmentModel();
                    wepType.setEqpId(equipId);
                    API.createEquipment(item, wepType);
                }

                comboBox_allEquipment.setModel(new DefaultComboBoxModel<>(API.getAllEquipment().toArray(new EquipmentModel[0])));
            }
        });

        addEquipmentToClassButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                API.addEquipmentToClass(selectedEquipment, selectedClass);
            }
        });

        //selected a character.
        comboBox_characters.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedCharacter = (CharacterModel)comboBox_characters.getSelectedItem();
                txtField_class.setText(selectedCharacter.getHasClass());

                EquipmentModel mainEquip= API.getMainEquipment(selectedCharacter);
                if (mainEquip != null)
                    txtField_currMainEquip.setText(mainEquip.getEqpName());
                else
                    txtField_currMainEquip.setText("");


                EquipmentModel secEquip= API.getSecondaryEquipment(selectedCharacter);
                if (secEquip != null)
                    txtField_currSecondaryEquip.setText(secEquip.getEqpName());
                else
                    txtField_currSecondaryEquip.setText("");

                comboBox_weaponToEquip.setModel(new DefaultComboBoxModel<>(API.getEquipmentForClass(selectedCharacter).toArray(new EquipmentModel[0])));
            }
        });
        comboBox_weaponToEquip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedEquipmentToEquip = (EquipmentModel)comboBox_weaponToEquip.getSelectedItem();
            }
        });
        comboBox_slot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedEquipmentSlot = (String)comboBox_slot.getSelectedItem();
            }
        });
        equipWeaponButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedEquipmentSlot.compareTo("Main") == 0){
                    API.equipMainWeapon(selectedEquipmentToEquip, selectedCharacter);
                    EquipmentModel mainEquip= API.getMainEquipment(selectedCharacter);
                    if (mainEquip != null)
                        txtField_currMainEquip.setText(mainEquip.getEqpName());
                }else {
                    API.equipSecondaryWeapon(selectedEquipmentToEquip, selectedCharacter);
                    EquipmentModel secEquip= API.getSecondaryEquipment(selectedCharacter);
                    if (secEquip != null)
                        txtField_currSecondaryEquip.setText(secEquip.getEqpName());
                }
            }
        });
        comboBox_allEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedEquipment = (EquipmentModel)comboBox_allEquipment.getSelectedItem();
            }
        });
        comboBox_allClasses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedClass = (ClazzModel)comboBox_allClasses.getSelectedItem();
            }
        });
        comboBox_weaponType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedWeaponType = (String)comboBox_weaponType.getSelectedItem();
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
