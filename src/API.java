import models.CharacterModel;

import java.util.List;

public class API {

    //need a list of ALL character models.
    public List<CharacterModel> getAllCharacters(){


        return null;
    }

    //need a list of all skills, available to the given class.
    public List<String> getAllSkills(String className){

        return null;
    }

    //--------- SETTERS ----------

    /**
     * This adds a skill to a character.
     *
     * @return Whether or not the operation was successful.
     */
    public boolean addSkill(String name, String skill){

        return false;
    }
}
