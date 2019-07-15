package main;

import main.models.*;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReportGenerator {

    /**
     * Create a report which will list all of the clans, where for each clan it will identify the chief followed
     * by a list of every member of that clan.
     */
    static void generateClanReport() {
        Session session = Database.openSession();
        ((List<ClanModel>) session.createQuery("SELECT Cln FROM ClanModel Cln").list()).forEach(cl -> {
            System.out.println(cl.getClanname());
            List<ClanMemberModel> members = session.createQuery("SELECT Mem FROM ClanMemberModel Mem WHERE Mem.clnName = :clanName")
                    .setParameter("clanName", cl.getClanname())
                    .list();
            for (ClanMemberModel mem : members) {
                System.out.println(mem.getCharName());
            }
        });
        session.close();
    }

    /**
     * Create a report which will take in a character name, identified as an input parameter, and list all of the
     * skills which the character can currently use.
     *
     * @param c
     */
    static void generateUseableSkillsReport(CharacterModel c) {
        System.out.println("Useable skills for character: " + c + c.getHasClass() + c.getCharLevel());
        Session session = Database.openSession();

        List<SkillModel> auto_skills =
                session.createQuery("SELECT SK FROM AutoSkillModel ASK, SkillModel SK WHERE ASK.clsName = :char_class and ASK.skillId = SK.skillId")
                        .setParameter("char_class", c.getHasClass())
                        .list();

        List<SkillModel> earned_skills =
                session.createQuery("SELECT SK FROM HasEarnedModel HE, SkillModel SK WHERE HE.skillId = SK.skillId AND HE.charName = :char_name")
                        .setParameter("char_name", c.getCharName())
                        .list();
        for (SkillModel skill : auto_skills) {
            System.out.println(skill.getSkillId());
        }
        for (SkillModel skill : earned_skills) {
            System.out.println(skill.getSkillId());
        }

        List<SkillModel> useable_skills = new LinkedList<>();
        useable_skills.addAll(auto_skills);
        useable_skills.addAll(earned_skills);
        for (SkillModel skill : useable_skills) {
            System.out.println(skill.getSkillId());
        }

        session.close();
    }

    static void generateUseableSkillsReport(String character_name) {
        generateCharachterStatsReport(getCharacterByName(character_name));
    }

    static CharacterModel getCharacterByName(String characterName) {
        Session session = Database.openSession();
        CharacterModel res = (CharacterModel) session.createQuery("SELECT C FROM CharacterModel C where C.charName = :char_name")
                .setParameter("char_name", characterName)
                .list()
                .get(0);
        session.close();
        return res;
    }

    /**
     * Create a report which will take in a character name, identified as an input parameter, and list the
     * character’s total stats with all equipment and gems taken into consideration.
     * <p>
     * stats: speed, block, power, will, life, defence, strength
     *
     * @param c
     */
    static void generateCharachterStatsReport(CharacterModel c) {
        System.out.println("Stats for character: " + c + c.getHasClass() + c.getCharLevel());
        Session session = Database.openSession();
        List<EquipmentModel> equipment = session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :main_weapon_id or EQ.eqpId = :secondary_weapon_id or EQ.eqpId = :armour_id")
                .setParameter("main_weapon_id", c.getMainEquipped())
                .setParameter("secondary_weapon_id", c.getSecondaryEquipped())
                .setParameter("armour_id", c.getArmourEquipped())
                .list();

        List<GemModel> main_gems = session.createQuery("SELECT GM FROM GemModel GM, MainEmbedModel ME where ME.mainWeaponInstanceId = :main_weapon_id and GM.gemId = ME.gemId")
                .setParameter("main_weapon_id", c.getMainEquipped())
                .list();

        List<GemModel> secondary_gems = session.createQuery("SELECT GM FROM GemModel GM, SecondaryEmbedModel ME where ME.secondaryWeaponInstanceId = :weapon_id and GM.gemId = ME.gemId")
                .setParameter("weapon_id", c.getMainEquipped())
                .list();

        List<GemModel> armour_gems = session.createQuery("SELECT GM FROM GemModel GM, ArmourEmbedModel ME where ME.armourInstanceId = :weapon_id and GM.gemId = ME.gemId")
                .setParameter("weapon_id", c.getMainEquipped())
                .list();

        List<GemModel> all_gems = new ArrayList<>();
        all_gems.addAll(main_gems);
        all_gems.addAll(secondary_gems);
        all_gems.addAll(armour_gems);

        List<StatsContainer> all_stats = new ArrayList<>();

        for (EquipmentModel m : equipment) {
            all_stats.add(new StatsContainer(m.getEspeed(), m.getEblcok(), m.getEpower(), m.getEwill(), m.getElife(),
                    m.getEdefence(), m.getEstrength()));
        }

        for (GemModel g : all_gems) {
            all_stats.add(new StatsContainer(g.getGspeed(), g.getGblock(), g.getGpower(), g.getGwill(), g.getGlife(),
                    g.getGdefence(), g.getGstrength()));
        }


        StatsContainer totalStats = new StatsContainer();
        for (StatsContainer statsContainer : all_stats) {
            totalStats = StatsContainer.add(totalStats, statsContainer);
        }

        System.out.println(totalStats);
        session.close();
    }

    static void generateCharachterStatsReport(String character_name) {
        generateCharachterStatsReport(getCharacterByName(character_name));
    }

    /**
     * Create a report which will take in a character name, identified as an input parameter, and list all
     * the armor and weapons that character has equipped, along with the gems on the equipment.
     *
     * @param c
     */
    static void generateEquipmentReport(CharacterModel c) {
        System.out.println("Equipment for character: " + c + c.getHasClass() + c.getCharLevel());
        Session session = Database.openSession();


        List<EquipmentModel> mainWeapon = session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :main_weapon_id ")
                .setParameter("main_weapon_id", c.getMainEquipped())
                .list();

        List<GemModel> mainGems =
                session.createQuery("SELECT GM FROM GemModel GM, MainEmbedModel ME where ME.mainWeaponInstanceId = :main_weapon_id and GM.gemId = ME.gemId")
                        .setParameter("main_weapon_id", c.getMainEquipped())
                        .list();

        System.out.println("Main Weapon: " + mainWeapon);
        System.out.println("Main Weapon Gems: " + mainGems);

        List<EquipmentModel> secondaryWeapon =
                session.createQuery("SELECT EQ from EquipmentModel EQ where  EQ.eqpId = :secondary_weapon_id ")
                        .setParameter("secondary_weapon_id", c.getSecondaryEquipped())
                        .list();

        List<GemModel> secondaryGems =
                session.createQuery("SELECT GM FROM GemModel GM, SecondaryEmbedModel ME where ME.secondaryWeaponInstanceId = :weapon_id and GM.gemId = ME.gemId")
                        .setParameter("weapon_id", c.getMainEquipped())
                        .list();

        System.out.println("Secondary Weapon: " + secondaryWeapon);
        System.out.println("Secondary Weapon Gems: " + secondaryGems);

        List<EquipmentModel> armour =
                session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :armour_id")
                        .setParameter("armour_id", c.getArmourEquipped())
                        .list();

        List<GemModel> armourGems =
                session.createQuery("SELECT GM FROM GemModel GM, ArmourEmbedModel ME where ME.armourInstanceId = :weapon_id and GM.gemId = ME.gemId")
                        .setParameter("weapon_id", c.getMainEquipped())
                        .list();

        System.out.println("Armour: " + mainWeapon);
        System.out.println("Armour Gems: " + mainGems);
        session.close();
    }

    static void generateEquipmentReport(String character_name) {
        generateEquipmentReport(getCharacterByName(character_name));
    }

    public static void main(String[] args) {
        Session session = Database.openSession();
        List<CharacterModel> all_chars = session.createQuery("SELECT C FROM CharacterModel C").list();
        session.close();
        generateClanReport();
        for (CharacterModel c : all_chars) {
            generateUseableSkillsReport(c);
        }
        generateUseableSkillsReport("Kindeep");
        generateCharachterStatsReport("Kindeep");
        generateEquipmentReport("Kindeep");
    }
}


class StatsContainer {
    int speed, block, power, will, life, defence, strength = 0;

    StatsContainer() {
    }

    StatsContainer(int speed, int block, int power, int will, int life, int defence, int strength) {
        this.speed = speed;
        this.block = block;
        this.power = power;
        this.will = will;
        this.life = life;
        this.defence = defence;
        this.strength = strength;
    }

    static StatsContainer add(StatsContainer a, StatsContainer b) {
        return new StatsContainer(a.speed + b.speed, a.block + b.block, a.power + b.power,
                a.will + b.will, a.life + b.life, a.defence + b.defence, a.strength + b.strength);
    }

    @Override
    public String toString() {
        return "Speed: " + speed + " Block: " + block + " Power: " + power + " Will: " + will + " Life: " + life
                + " Defence: " + defence + " Strength: " + strength;
    }
}