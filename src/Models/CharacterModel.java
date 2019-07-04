package Models;

public class CharacterModel {
    public String name;
    public int charLevel;
    public String className;

    public CharacterModel(String name, String className, int charLevel){
        this.name = name;
        this.className = className;
        this.charLevel = charLevel;
    }
}
