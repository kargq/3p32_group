package main;

import main.models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Random;

/**
 * This class is used to create mock data.
 */
public class Toybox {

    /**
     * call this when there is a database reset.
     */
    public static void generateMassiveMockData(){

        Random rand = new Random();

        //------- generates skills.
        String[] skillNames = new String[]{
                "Rolls",
                "Royce",
                "To The Skies",
                "Phantom Instinct",
                "Dexterious Training",
                "Skill Swipe",
                "Judgment Draw",
                "Ghostwalk",
                "Calling Card",
                "Miles Cartes",
                "Devil's Luck",
                "RKO"};

        for (int i = 0; i < skillNames.length; i++)
            Toybox.uploadItem(Toybox.createMockSkill(skillNames[i], i));

        //------- generates classes.
        String[] classNames = new String[]{"Warrior", "Mage", "Thief", "Pirate"};
        for (String name: classNames)
            Toybox.uploadItem(Toybox.createMockClass(name));

        //------- generate 3 auto skills per class.
        for (int i = 0; i < classNames.length; i++){
            for (int j = 0; j < 3; j++){
                Toybox.uploadItem(Toybox.createMockAutoSkill(classNames[i], i*3 + j));
            }
        }

        //------- generate 3 earned skills per class.
        for (int i = 0; i < classNames.length; i++){
            for (int j = 0; j < 3; j++){
                Toybox.uploadItem(Toybox.createMockEarnedSkill(classNames[i], i*3 + j));
            }
        }

        String[] charNames = new String[]{"Aaran", "Aaren", "Aarez", "Aarman", "Aaron", "Aaron-James", "Aarron", "Aaryan", "Aaryn", "Aayan", "Aazaan", "Abaan", "Abbas", "Abdallah", "Abdalroof","Abdurraheem", "Abdur-Rahman", "Abdur-Rehmaan", "Abel", "Abhinav", "Abhisumant", "Abid", "Abir", "Abraham", "Abu", "Abubakar", "Ace", "Adain", "Adam", "Adam-James", "Addison", "Addisson", "Adegbola", "Adegbolahan", "Aden", "Adenn", "Adie", "Adil", "Aditya", "Adnan", "Adrian", "Adrien", "Aedan", "Aedin", "Aedyn", "Aeron", "Afonso", "Ahmad", "Ahmed", "Ahmed-Aziz", "Ahoua", "Ahtasham", "Aiadan", "Aidan", "Aiden", "Aiden-Jack", "Aiden-Vee", "Aidian", "Aidy", "Ailin", "Aiman", "Ainsley", "Ainslie", "Airen", "Airidas", "Airlie", "AJ", "Ajay", "A-Jay", "Ajayraj", "Akan", "Akram", "Al", "Ala", "Alan", "Alanas", "Alasdair", "Alastair", "Alber", "Albert", "Albie", "Aldred", "Alec", "Aled", "Aleem", "Aleksandar", "Aleksander", "Aleksandr", "Aleksandrs", "Alekzander", "Alessandro", "Alessio", "Alex", "Alexander", "Alexei", "Alexx", "Alexzander", "Alf", "Alfee", "Alfie", "Alfred", "Alfy", "Alhaji", "Al-Hassan", "Ali", "Aliekber", "Alieu", "Alihaider", "Alisdair", "Alishan", "Alistair", "Alistar", "Alister", "Aliyaan", "Allan", "Allan-Laiton", "Allen", "Allesandro", "Allister", "Ally", "Ammar", "Ammer", "Amolpreet", "Amos", "Amrinder", "Amrit", "Amro", "Anay", "Andrea", "Andreas", "Andrei", "Andrejs", "Andrew", "Andy", "Anees", "Anesu", "Angel", "Angelo", "Angus", "Anir", "Anis", "Anish", "Anmolpreet", "Annan", "Anndra", "Anselm", "Anthony", "Anthony-John", "Antoine", "Anton", "Antoni", "Antonio", "Antony", "Antonyo", "Anubhav", "Aodhan", "Aon", "Aonghus", "Apisai", "Arafat", "Aran", "Arandeep", "Arann", "Aray", "Arayan", "Archibald", "Archie", "Arda", "Ardal", "Ardeshir", "Areeb", "Areez", "Aref", "Arfin", "Argyle", "Argyll", "Ari", "Aria", "Arian", "Arihant", "Aristomenis", "Aristotelis", "Arjuna", "Arlo", "Armaan", "Arman", "Armen", "Arnab", "Arnav", "Arnold", "Aron", "Aronas", "Arran", "Arrham", "Arron", "Arryn", "Arsalan", "Artem", "Arthur", "Artur", "Arturo", "Arun", "Arunas", "Arved", "Arya", "Aryan", "Aryankhan", "Aryian", "Aryn", "Asa", "Asfhan", "Ash", "Ashlee-jay", "Ashley", "Ashton", "Ashton-Lloyd", "Ashtyn", "Ashwin", "Asif", "Asim", "Aslam", "Asrar", "Austen", "Austin", "Avani", "Averon", "Avi", "Avinash", "Avraham", "Awais", "Awwal", "Axel", "Ayaan", "Ayan", "Aydan", "Ayden", "Aydin", "Aydon", "Ayman", "Ayomide", "Ayren", "Ayrton", "Aytug", "Ayub", "Ayyub", "Azaan", "Azedine", "Azeem", "Azim", "Aziz", "Azlan", "Azzam", "Azzedine", "Babatunmise", "Babur", "Bader", "Badr", "Badsha", "Bailee", "Bailey", "Bailie", "Bailley", "Baillie", "Baley", "Balian", "Banan", "Barath", "Barkley", "Barney", "Baron", "Barrie", "Barry", "Bartlomiej", "Bartosz", "Basher", "Basile", "Baxter", "Baye", "Bayley", "Beau", "Beinn", "Bekim", "Believe", "Ben", "Bendeguz", "Benedict", "Benjamin", "Benjamyn", "Benji", "Benn", "Bennett", "Benny", "Benoit", "Bentley", "Berkay", "Bernard", "Bertie", "Bevin", "Bezalel", "Bhaaldeen", "Bharath", "Bilal", "Bill", "Billy", "Binod", "Bjorn", "Blaike", "Blaine", "Blair", "Blaire", "Blake", "Blazej", "Blazey", "Blessing", "Blue", "Blyth", "Bo", "Boab", "Bob", "Bobby", "Bobby-Lee", "Bodhan", "Boedyn", "Bogdan", "Bohbi", "Bony", "Bowen", "Bowie", "Boyd", "Bracken", "Brad", "Bradan", "Braden", "Bradley", "Bradlie", "Bradly", "Brady", "Bradyn", "Braeden", "Braiden", "Brajan", "Brandan", "Branden", "Brandon", "Brandonlee", "Brandon-Lee", "Brandyn", "Brannan", "Brayden", "Braydon", "Braydyn", "Breandan", "Brehme", "Brendan", "Brendon", "Brendyn", "Breogan", "Bret", "Brett", "Briaddon", "Brian", "Brodi", "Brodie", "Brody", "Brogan", "Broghan", "Brooke", "Brooklin", "Brooklyn", "Bruce", "Bruin", "Bruno", "Brunon", "Bryan", "Bryce"};
        //------- generate some characters.
        for (int i = 0; i < charNames.length; i++){
            uploadItem(createMockCharacter(charNames[i], classNames[rand.nextInt(classNames.length)]));
        }


    }

    public static void uploadItem(Object item){
        Session session = Database.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(item);
        transaction.commit();
        session.close();
    }

    public static EarnedSkillModel createMockEarnedSkill(String className, int skillId){
        EarnedSkillModel item = new EarnedSkillModel();
        item.setClsName(className);
        item.setSkillId(skillId);
        return item;
    }


    public static AutoSkillModel createMockAutoSkill(String className, int skillId){
        AutoSkillModel item = new AutoSkillModel();
        item.setClsName(className);
        item.setSkillId(skillId);
        return item;
    }

    public static CharacterModel createMockCharacter(String name, String className){
        Random rand = new Random();
        CharacterModel item = new CharacterModel();
        item.setCharName(name);
        item.setHasClass(className);
        item.setCharLevel(rand.nextInt(100));
        return item;
    }

    public static ClazzModel createMockClass(String className){
        ClazzModel item= new ClazzModel();
        item.setClsName(className);
        return item;
    }

    public static SkillModel createMockSkill(String name, int id){
        SkillModel item = new SkillModel();
        Random rand = new Random();
        item.setSkillId(id);
        item.setSname(name);
        item.setPowerCost(rand.nextInt(500));
        item.setMinLevel(rand.nextInt(99));

        return item;
    }
}
