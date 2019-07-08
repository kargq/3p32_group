package main;

import main.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Random;

public class API {

    //get skills given character class name.
    public static List<SkillModel> getSkills(String className){
        Session session = Database.openSession();
        Query query = session.createQuery("SELECT skill from AutoSkillModel auto, SkillModel skill WHERE skill.skillId = auto.skillId and auto.clsName = :className");
        query.setParameter("className", className);
        List<SkillModel> data = query.list();
        session.close();
        return data;
    }

    //get list of skills character has earned.
    public static List<String> getEarnedSkills(String charName){
        Session session = Database.openSession();
        Query query = session.createQuery("SELECT skill.sname FROM HasEarnedModel hasEarned, SkillModel skill WHERE hasEarned.charName = :charName and hasEarned.skillId = skill.skillId");
        query.setParameter("charName", charName);
        List<String> data = query.list();
        session.close();
        return data;
    }

    //need a list of ALL character models.
    public static List<CharacterModel> getAllCharacters(){
        Session session = Database.openSession();
        Query query = session.createQuery("SELECT C FROM CharacterModel C");
        List<CharacterModel> data = query.list();
        session.close();
        return data;
    }

    //need a list of all skills, available to the given class.
    public List<String> getAllSkills(String className){

        return null;
    }

    public static List<EquipmentModel> getAllEquipment(){
        Session session = Database.openSession();
        Query query = session.createQuery("SELECT E FROM EquipmentModel E");
        List<EquipmentModel> data = query.list();
        session.close();
        return data;
    }

    public static List<EquipmentModel> getAllClasses(){
        Session session = Database.openSession();
        Query query = session.createQuery("SELECT C FROM ClazzModel C");
        List<EquipmentModel> data = query.list();
        session.close();
        return data;
    }

    public static EquipmentModel getMainEquipment(CharacterModel character){
        Session session = Database.openSession();
        Query<EquipmentModel> query = session.createQuery("SELECT E FROM CharacterModel  C, EquipmentModel E, MainWeaponInstanceModel I " +
                "WHERE I.eqpId = E.eqpId and " +
                "C = :character and " +
                "C.mainEquipped = I.mainWeaponInstanceId", EquipmentModel.class);
        query.setParameter("character", character);
        EquipmentModel item = query.uniqueResult();
        session.close();
        return item;
    }

    public static EquipmentModel getSecondaryEquipment(CharacterModel character){
        Session session = Database.openSession();
        Query<EquipmentModel> query = session.createQuery("SELECT E FROM CharacterModel  C, EquipmentModel E, SecondaryEquipmentInstanceModel I " +
                "WHERE I.eqpId = E.eqpId and " +
                "C = :character and " +
                "C.secondaryEquipped = I.secondaryWeaponInstanceId", EquipmentModel.class);
        query.setParameter("character", character);
        EquipmentModel item = query.uniqueResult();
        session.close();
        return item;
    }

    public static List<EquipmentModel> getEquipmentForClass(CharacterModel character){
        Session session = Database.openSession();
        Query<EquipmentModel> query = session.createQuery("SELECT E FROM EquipmentModel E, ClassEquipmentModel C WHERE C.clsName = :characterClass and C.eqpId = E.eqpId", EquipmentModel.class);
        query.setParameter("characterClass", character.getHasClass());
        List<EquipmentModel> items = query.list();
        session.close();
        return items;
    }

    //--------- SETTERS ----------

    //adds skill to a character.
    public static void addSkill(String charName,int skillId){
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        HasEarnedModel item = new HasEarnedModel();
        item.setCharName(charName);
        item.setSkillId(skillId);
        session.save(item);
        transaction.commit();
        session.close();
    }

    public static void createEquipment(EquipmentModel item, Object equipmentType) {
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        session.save(equipmentType);
        transaction.commit();
        session.close();
    }

    public static void addEquipmentToClass(EquipmentModel equipModel, ClazzModel classModel) {
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        ClassEquipmentModel item = new ClassEquipmentModel();
        item.setClsName(classModel.getClsName());
        item.setEqpId(equipModel.getEqpId());
        session.save(item);
        transaction.commit();
        session.close();
    }

    public static void equipMainWeapon(EquipmentModel item, CharacterModel character) {
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        MainWeaponInstanceModel newWeapon = new MainWeaponInstanceModel();

        int instanceId = new Random().nextInt();
        newWeapon.setEqpId(item.getEqpId());
        newWeapon.setMainWeaponInstanceId(instanceId);
        session.save(newWeapon);

        Query<CharacterModel> query = session.createQuery("SELECT C FROM CharacterModel C WHERE C.charName = :characterId", CharacterModel.class);
        query.setParameter("characterId", character.getCharName());

        CharacterModel c = query.uniqueResult();
        c.setMainEquipped(instanceId);

        session.update(c);
        transaction.commit();
        session.close();
    }

    public static void equipSecondaryWeapon(EquipmentModel item, CharacterModel character) {
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        SecondaryEquipmentInstanceModel newWeapon = new SecondaryEquipmentInstanceModel();
        int instanceId = new Random().nextInt();

        newWeapon.setEqpId(item.getEqpId());
        newWeapon.setSecondaryWeaponInstanceId(instanceId);
        session.save(newWeapon);

        Query<CharacterModel> query = session.createQuery("SELECT C FROM CharacterModel C WHERE C.charName = :characterId", CharacterModel.class);
        query.setParameter("characterId", character.getCharName());

        CharacterModel c = query.uniqueResult();
        c.setSecondaryEquipped(instanceId);

        session.update(c);
        transaction.commit();
        session.close();
    }
}
