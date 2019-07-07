package main;

import main.models.CharacterModel;
import main.models.HasEarnedModel;
import main.models.SkillModel;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

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
}
