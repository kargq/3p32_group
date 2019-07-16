package main;

import main.models.*;
import org.hibernate.Session;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Kindeep Singh Kargil
 * <p>
 * Use methods:
 * 1. generateClanReport()
 * 2. generateUseableSkillsReport(String characterName)
 * 3. generateCharachterStatsReport(String character_name)
 * 4. generateEquipmentReport(String character_name)
 * <p>
 * For on button click methods. all will open the report in the default browser.
 */
public class ReportGenerator {

    /**
     * Create a report which will list all of the clans, where for each clan it will identify the chief followed
     * by a list of every member of that clan.
     */
    public static void generateClanReport() {
        StringBuilder html = new StringBuilder();
        html.append("<html><head></head><body>");
        Session session = Database.openSession();
        ((List<ClanModel>) session.createQuery("SELECT Cln FROM ClanModel Cln").list()).forEach(cl -> {
            System.out.println(cl.getClanname());
            html.append("<h3>Clan</h3>");
            html.append("<b>Name:</b> " + cl.getClanname() + "<br/>");
            html.append("<b>Chief:</b> " + cl.getChief() + "<br/>");
            html.append("Members: - <br/>");
            html.append("<table border=1px>");
            html.append(boxInTag(boxInTag("Character name", "th"), "tr"));
            List<ClanMemberModel> members = session.createQuery("SELECT Mem FROM ClanMemberModel Mem WHERE Mem.clnName = :clanName")
                    .setParameter("clanName", cl.getClanname())
                    .list();
            for (ClanMemberModel mem : members) {
                System.out.println(mem.getCharName());
                html.append("<tr>");
                html.append(boxInTag(mem.getCharName(), "td"));
                html.append("</tr>");
            }
            html.append("</table>");
        });
        session.close();
        html.append("</body></html>");
        openInBrowser(html, "Clan Report");
    }

    static String getCSS() {
        return "<style>\n@charset \"UTF-8\";\n" +
                "/*!\n" +
                " * Bootstrap v4.3.1 (https://getbootstrap.com/)\n" +
                " * Copyright 2011-2019 The Bootstrap Authors\n" +
                " * Copyright 2011-2019 Twitter, Inc.\n" +
                " * Licensed under MIT (https://github.com/twbs/bootstrap/blob/master/LICENSE)\n" +
                " */\n" +
                ":root {\n" +
                "  --blue: #007bff;\n" +
                "  --indigo: #6610f2;\n" +
                "  --purple: #6f42c1;\n" +
                "  --pink: #e83e8c;\n" +
                "  --red: #dc3545;\n" +
                "  --orange: #fd7e14;\n" +
                "  --yellow: #ffc107;\n" +
                "  --green: #28a745;\n" +
                "  --teal: #20c997;\n" +
                "  --cyan: #17a2b8;\n" +
                "  --white: #fff;\n" +
                "  --gray: #6c757d;\n" +
                "  --gray-dark: #343a40;\n" +
                "  --primary: #007bff;\n" +
                "  --secondary: #6c757d;\n" +
                "  --success: #28a745;\n" +
                "  --info: #17a2b8;\n" +
                "  --warning: #ffc107;\n" +
                "  --danger: #dc3545;\n" +
                "  --light: #f8f9fa;\n" +
                "  --dark: #343a40;\n" +
                "  --breakpoint-xs: 0;\n" +
                "  --breakpoint-sm: 576px;\n" +
                "  --breakpoint-md: 768px;\n" +
                "  --breakpoint-lg: 992px;\n" +
                "  --breakpoint-xl: 1200px;\n" +
                "  --font-family-sans-serif: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\";\n" +
                "  --font-family-monospace: SFMono-Regular, Menlo, Monaco, Consolas, \"Liberation Mono\", \"Courier New\", monospace;\n" +
                "}\n" +
                "\n" +
                "*,\n" +
                "*::before,\n" +
                "*::after {\n" +
                "  box-sizing: border-box;\n" +
                "}\n" +
                "\n" +
                "html {\n" +
                "  font-family: sans-serif;\n" +
                "  line-height: 1.15;\n" +
                "  -webkit-text-size-adjust: 100%;\n" +
                "  -webkit-tap-highlight-color: rgba(0, 0, 0, 0);\n" +
                "}\n" +
                "\n" +
                "article, aside, figcaption, figure, footer, header, hgroup, main, nav, section {\n" +
                "  display: block;\n" +
                "}\n" +
                "\n" +
                "body {\n" +
                "  margin: 0;\n" +
                "  font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, \"Noto Sans\", sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\", \"Noto Color Emoji\";\n" +
                "  font-size: 1rem;\n" +
                "  font-weight: 400;\n" +
                "  line-height: 1.5;\n" +
                "  color: #212529;\n" +
                "  text-align: left;\n" +
                "  background-color: #fff;\n" +
                "}\n" +
                "\n" +
                "[tabindex=\"-1\"]:focus {\n" +
                "  outline: 0 !important;\n" +
                "}\n" +
                "\n" +
                "hr {\n" +
                "  box-sizing: content-box;\n" +
                "  height: 0;\n" +
                "  overflow: visible;\n" +
                "}\n" +
                "\n" +
                "h1, h2, h3, h4, h5, h6 {\n" +
                "  margin-top: 0;\n" +
                "  margin-bottom: 0.5rem;\n" +
                "}\n" +
                "\n" +
                "p {\n" +
                "  margin-top: 0;\n" +
                "  margin-bottom: 1rem;\n" +
                "}\n" +
                "\n" +
                "abbr[title],\n" +
                "abbr[data-original-title] {\n" +
                "  text-decoration: underline;\n" +
                "  text-decoration: underline dotted;\n" +
                "  cursor: help;\n" +
                "  border-bottom: 0;\n" +
                "  text-decoration-skip-ink: none;\n" +
                "}\n" +
                "\n" +
                "address {\n" +
                "  margin-bottom: 1rem;\n" +
                "  font-style: normal;\n" +
                "  line-height: inherit;\n" +
                "}\n" +
                "\n" +
                "ol,\n" +
                "ul,\n" +
                "dl {\n" +
                "  margin-top: 0;\n" +
                "  margin-bottom: 1rem;\n" +
                "}\n" +
                "\n" +
                "ol ol,\n" +
                "ul ul,\n" +
                "ol ul,\n" +
                "ul ol {\n" +
                "  margin-bottom: 0;\n" +
                "}\n" +
                "\n" +
                "dt {\n" +
                "  font-weight: 700;\n" +
                "}\n" +
                "\n" +
                "dd {\n" +
                "  margin-bottom: 0.5rem;\n" +
                "  margin-left: 0;\n" +
                "}\n" +
                "\n" +
                "blockquote {\n" +
                "  margin: 0 0 1rem;\n" +
                "}\n" +
                "\n" +
                "b,\n" +
                "strong {\n" +
                "  font-weight: bolder;\n" +
                "}\n" +
                "\n" +
                "small {\n" +
                "  font-size: 80%;\n" +
                "}\n" +
                "\n" +
                "sub,\n" +
                "sup {\n" +
                "  position: relative;\n" +
                "  font-size: 75%;\n" +
                "  line-height: 0;\n" +
                "  vertical-align: baseline;\n" +
                "}\n" +
                "\n" +
                "sub {\n" +
                "  bottom: -0.25em;\n" +
                "}\n" +
                "\n" +
                "sup {\n" +
                "  top: -0.5em;\n" +
                "}\n" +
                "\n" +
                "a {\n" +
                "  color: #007bff;\n" +
                "  text-decoration: none;\n" +
                "  background-color: transparent;\n" +
                "}\n" +
                "a:hover {\n" +
                "  color: #0056b3;\n" +
                "  text-decoration: underline;\n" +
                "}\n" +
                "\n" +
                "a:not([href]):not([tabindex]) {\n" +
                "  color: inherit;\n" +
                "  text-decoration: none;\n" +
                "}\n" +
                "a:not([href]):not([tabindex]):hover, a:not([href]):not([tabindex]):focus {\n" +
                "  color: inherit;\n" +
                "  text-decoration: none;\n" +
                "}\n" +
                "a:not([href]):not([tabindex]):focus {\n" +
                "  outline: 0;\n" +
                "}\n" +
                "\n" +
                "pre,\n" +
                "code,\n" +
                "kbd,\n" +
                "samp {\n" +
                "  font-family: SFMono-Regular, Menlo, Monaco, Consolas, \"Liberation Mono\", \"Courier New\", monospace;\n" +
                "  font-size: 1em;\n" +
                "}\n" +
                "\n" +
                "pre {\n" +
                "  margin-top: 0;\n" +
                "  margin-bottom: 1rem;\n" +
                "  overflow: auto;\n" +
                "}\n" +
                "\n" +
                "figure {\n" +
                "  margin: 0 0 1rem;\n" +
                "}\n" +
                "\n" +
                "img {\n" +
                "  vertical-align: middle;\n" +
                "  border-style: none;\n" +
                "}\n" +
                "\n" +
                "svg {\n" +
                "  overflow: hidden;\n" +
                "  vertical-align: middle;\n" +
                "}\n" +
                "\n" +
                "table {\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "caption {\n" +
                "  padding-top: 0.75rem;\n" +
                "  padding-bottom: 0.75rem;\n" +
                "  color: #6c757d;\n" +
                "  text-align: left;\n" +
                "  caption-side: bottom;\n" +
                "}\n" +
                "\n" +
                "th {\n" +
                "  text-align: inherit;\n" +
                "}\n" +
                "\n" +
                "label {\n" +
                "  display: inline-block;\n" +
                "  margin-bottom: 0.5rem;\n" +
                "}\n" +
                "\n" +
                "button {\n" +
                "  border-radius: 0;\n" +
                "}\n" +
                "\n" +
                "button:focus {\n" +
                "  outline: 1px dotted;\n" +
                "  outline: 5px auto -webkit-focus-ring-color;\n" +
                "}\n" +
                "\n" +
                "input,\n" +
                "button,\n" +
                "select,\n" +
                "optgroup,\n" +
                "textarea {\n" +
                "  margin: 0;\n" +
                "  font-family: inherit;\n" +
                "  font-size: inherit;\n" +
                "  line-height: inherit;\n" +
                "}\n" +
                "\n" +
                "button,\n" +
                "input {\n" +
                "  overflow: visible;\n" +
                "}\n" +
                "\n" +
                "button,\n" +
                "select {\n" +
                "  text-transform: none;\n" +
                "}\n" +
                "\n" +
                "select {\n" +
                "  word-wrap: normal;\n" +
                "}\n" +
                "\n" +
                "button,\n" +
                "[type=button],\n" +
                "[type=reset],\n" +
                "[type=submit] {\n" +
                "  -webkit-appearance: button;\n" +
                "}\n" +
                "\n" +
                "button:not(:disabled),\n" +
                "[type=button]:not(:disabled),\n" +
                "[type=reset]:not(:disabled),\n" +
                "[type=submit]:not(:disabled) {\n" +
                "  cursor: pointer;\n" +
                "}\n" +
                "\n" +
                "button::-moz-focus-inner,\n" +
                "[type=button]::-moz-focus-inner,\n" +
                "[type=reset]::-moz-focus-inner,\n" +
                "[type=submit]::-moz-focus-inner {\n" +
                "  padding: 0;\n" +
                "  border-style: none;\n" +
                "}\n" +
                "\n" +
                "input[type=radio],\n" +
                "input[type=checkbox] {\n" +
                "  box-sizing: border-box;\n" +
                "  padding: 0;\n" +
                "}\n" +
                "\n" +
                "input[type=date],\n" +
                "input[type=time],\n" +
                "input[type=datetime-local],\n" +
                "input[type=month] {\n" +
                "  -webkit-appearance: listbox;\n" +
                "}\n" +
                "\n" +
                "textarea {\n" +
                "  overflow: auto;\n" +
                "  resize: vertical;\n" +
                "}\n" +
                "\n" +
                "fieldset {\n" +
                "  min-width: 0;\n" +
                "  padding: 0;\n" +
                "  margin: 0;\n" +
                "  border: 0;\n" +
                "}\n" +
                "\n" +
                "legend {\n" +
                "  display: block;\n" +
                "  width: 100%;\n" +
                "  max-width: 100%;\n" +
                "  padding: 0;\n" +
                "  margin-bottom: 0.5rem;\n" +
                "  font-size: 1.5rem;\n" +
                "  line-height: inherit;\n" +
                "  color: inherit;\n" +
                "  white-space: normal;\n" +
                "}\n" +
                "\n" +
                "progress {\n" +
                "  vertical-align: baseline;\n" +
                "}\n" +
                "\n" +
                "[type=number]::-webkit-inner-spin-button,\n" +
                "[type=number]::-webkit-outer-spin-button {\n" +
                "  height: auto;\n" +
                "}\n" +
                "\n" +
                "[type=search] {\n" +
                "  outline-offset: -2px;\n" +
                "  -webkit-appearance: none;\n" +
                "}\n" +
                "\n" +
                "[type=search]::-webkit-search-decoration {\n" +
                "  -webkit-appearance: none;\n" +
                "}\n" +
                "\n" +
                "::-webkit-file-upload-button {\n" +
                "  font: inherit;\n" +
                "  -webkit-appearance: button;\n" +
                "}\n" +
                "\n" +
                "output {\n" +
                "  display: inline-block;\n" +
                "}\n" +
                "\n" +
                "summary {\n" +
                "  display: list-item;\n" +
                "  cursor: pointer;\n" +
                "}\n" +
                "\n" +
                "template {\n" +
                "  display: none;\n" +
                "}\n" +
                "\n" +
                "[hidden] {\n" +
                "  display: none !important;\n" +
                "}\n" +
                "\n" +
                "h1, h2, h3, h4, h5, h6,\n" +
                ".h1, .h2, .h3, .h4, .h5, .h6 {\n" +
                "  margin-bottom: 0.5rem;\n" +
                "  font-weight: 500;\n" +
                "  line-height: 1.2;\n" +
                "}\n" +
                "\n" +
                "h1, .h1 {\n" +
                "  font-size: 2.5rem;\n" +
                "}\n" +
                "\n" +
                "h2, .h2 {\n" +
                "  font-size: 2rem;\n" +
                "}\n" +
                "\n" +
                "h3, .h3 {\n" +
                "  font-size: 1.75rem;\n" +
                "}\n" +
                "\n" +
                "h4, .h4 {\n" +
                "  font-size: 1.5rem;\n" +
                "}\n" +
                "\n" +
                "h5, .h5 {\n" +
                "  font-size: 1.25rem;\n" +
                "}\n" +
                "\n" +
                "h6, .h6 {\n" +
                "  font-size: 1rem;\n" +
                "}\n" +
                "table {\n" +
                "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "td, th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #144d9c;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>";
    }

    static void openInBrowser(StringBuilder content, String name) {
        content.append(getCSS());
        try {
            File file = new File(name.trim() + ".html");
            FileWriter writer = new FileWriter(file);
            writer.append(content);
            writer.close();
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Multiple calls override previous calls here
     *
     * @param content
     */
    static void openInBrowser(StringBuilder content) {
        openInBrowser(content, "temp.html");
    }

    static String boxInTag(String innerHTML, String tag) {
        return "<" + tag + ">" + innerHTML + "</" + tag + ">";
    }

    static String boxInTag(Object innerHTML, String tag) {
        return boxInTag(innerHTML == null ? "" : innerHTML.toString(), tag);
    }

    /**
     * Create a report which will take in a character name, identified as an input parameter, and list all of the
     * skills which the character can currently use.
     *
     * @param c
     */
    static void generateUseableSkillsReport(CharacterModel c) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head></head><body>");
        System.out.println("Useable skills for character: " + c + c.getHasClass() + c.getCharLevel());
        html.append("<h3> Character name: " + c.getCharName() + "</h3>");
        html.append("Useable skills for character: -");
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

        html.append("<table>");
        html.append("<tr>");
        html.append(boxInTag("ID", "th"));
        html.append(boxInTag("name", "th"));
        html.append(boxInTag("description", "th"));
        html.append(boxInTag("power cost", "th"));
        html.append(boxInTag("min level", "th"));
        html.append("</tr>");
        for (SkillModel skill : useable_skills) {
            System.out.println(skill.getSkillId());
            html.append("<tr>");
            html.append(boxInTag(skill.getSkillId(), "td"));
            html.append(boxInTag(skill.getSname(), "td"));
            html.append(boxInTag(skill.getSdescription(), "td"));
            html.append(boxInTag(skill.getPowerCost(), "td"));
            html.append(boxInTag(skill.getMinLevel(), "td"));
            html.append("</tr>");
        }
        html.append("</table>");
        html.append("</body></html>");
        openInBrowser(html, c.getCharName().trim() + " skill report");
        session.close();
    }

    public static void generateUseableSkillsReport(String characterName) {
        generateUseableSkillsReport(getCharacterByName(characterName));
    }

    /**
     * @param characterName
     * @return
     */
    static CharacterModel getCharacterByName(String characterName) {
        Session session = Database.openSession();
        List<CharacterModel> res = session.createQuery("SELECT C FROM CharacterModel C where C.charName = :char_name")
                .setParameter("char_name", characterName)
                .list();
        session.close();
        if (res.size() < 1) {
            throw new CharacterNotFoundException();
        }
        return res.get(0);
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
        StringBuilder html = new StringBuilder();
        html.append("<html><head></head><body>");
        System.out.println("Stats for character: " + c + c.getHasClass() + c.getCharLevel());
        Session session = Database.openSession();
//        List<EquipmentModel> equipment = session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :main_weapon_id or EQ.eqpId = :secondary_weapon_id or EQ.eqpId = :armour_id")
//                .setParameter("main_weapon_id", c.getMainEquipped())
//                .setParameter("secondary_weapon_id", c.getSecondaryEquipped())
//                .setParameter("armour_id", c.getArmourEquipped())
//                .list();

        List<EquipmentModel> mainWeapon = session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :main_weapon_id ")
                .setParameter("main_weapon_id", c.getMainEquipped())
                .list();

        List<EquipmentModel> secondaryWeapon =
                session.createQuery("SELECT EQ from EquipmentModel EQ where  EQ.eqpId = :secondary_weapon_id ")
                        .setParameter("secondary_weapon_id", c.getSecondaryEquipped())
                        .list();

        List<EquipmentModel> armour =
                session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :armour_id")
                        .setParameter("armour_id", c.getArmourEquipped())
                        .list();

        List<EquipmentModel> equipment = new LinkedList<>();
        equipment.addAll(mainWeapon);
        equipment.addAll(secondaryWeapon);
        equipment.addAll(armour);

        System.out.println("eqp" + equipment.size());

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

        System.out.println(all_stats + "" + all_stats.size());

        StatsContainer totalStats =
                new StatsContainer(c.getCharSpeed(), c.getCharBlock(), c.getCharPower(), c.getCharWill(), c.getCharLife()
                        , c.getCharDefence(), c.getCharStrength());

        for (StatsContainer statsContainer : all_stats) {
            totalStats = StatsContainer.add(totalStats, statsContainer);
        }

        html.append("Character name: " + c.getCharName() + "<br/>");
        html.append("Cumulative stats: -" + "<br/>");
        html.append(boxInTag("speed: ", "b") + totalStats.getSpeed() + "<br/>");
        html.append(boxInTag("block: ", "b") + totalStats.getBlock() + "<br/>");
        html.append(boxInTag("power: ", "b") + totalStats.getPower() + "<br/>");
        html.append(boxInTag("will: ", "b") + totalStats.getWill() + "<br/>");
        html.append(boxInTag("life: ", "b") + totalStats.getLife() + "<br/>");
        html.append(boxInTag("defence: ", "b") + totalStats.getDefence() + "<br/>");
        html.append(boxInTag("strength: ", "b") + totalStats.getStrength() + "<br/>");
        System.out.println(totalStats);
        html.append("</body></html>");
        openInBrowser(html, c.getCharName().trim() + " stats report");
        session.close();
    }

    public static void generateCharachterStatsReport(String character_name) {
        generateCharachterStatsReport(getCharacterByName(character_name));
    }

    static String equipmentRow(EquipmentModel e) {
        StringBuilder html = new StringBuilder();
        html.append("<tr>");
        html.append(boxInTag("" + e.getEqpId(), "td"));
        html.append(boxInTag(e.getEqpName(), "td"));
        html.append(boxInTag("" + e.getEwill(), "td"));
        html.append(boxInTag("" + e.getEspeed(), "td"));
        html.append(boxInTag("" + e.getEdefence(), "td"));
        html.append(boxInTag("" + e.getEstrength(), "td"));
        html.append(boxInTag("" + e.getEblcok(), "td"));
        html.append(boxInTag("" + e.getElife(), "td"));
        html.append(boxInTag("" + e.getElevel(), "td"));
        html.append(boxInTag("" + e.getGemLimit(), "td"));
        html.append("</tr>");
        return html.toString();
    }

    static String equipmentHeader() {
        StringBuilder html = new StringBuilder();
        html.append("<tr>");
        html.append(boxInTag("ID", "th"));
        html.append(boxInTag("name", "th"));
        html.append(boxInTag("will", "th"));
        html.append(boxInTag("speed", "th"));
        html.append(boxInTag("defence", "th"));
        html.append(boxInTag("strength", "th"));
        html.append(boxInTag("block", "th"));
        html.append(boxInTag("life", "th"));
        html.append(boxInTag("min level", "th"));
        html.append(boxInTag("gem limit", "th"));
        html.append("</tr>");
        return html.toString();
    }

    static String gemHeader() {
        StringBuilder html = new StringBuilder();
        html.append(boxInTag("ID", "th"));
        html.append(boxInTag("name", "th"));
        html.append(boxInTag("life", "th"));
        html.append(boxInTag("power", "th"));
        html.append(boxInTag("speed", "th"));
        html.append(boxInTag("defence", "th"));
        html.append(boxInTag("will", "th"));
        html.append(boxInTag("strength", "th"));
        html.append(boxInTag("level", "th"));
        html.append(boxInTag("block", "th"));
        return html.toString();

    }

    static String gemRow(GemModel g) {
        StringBuilder html = new StringBuilder();
        html.append(boxInTag("" + g.getGemId(), "td"));
        html.append(boxInTag("" + g.getGname(), "td"));
        html.append(boxInTag("" + g.getGlife(), "td"));
        html.append(boxInTag("" + g.getGpower(), "td"));
        html.append(boxInTag("" + g.getGspeed(), "td"));
        html.append(boxInTag("" + g.getGdefence(), "td"));
        html.append(boxInTag("" + g.getGwill(), "td"));
        html.append(boxInTag("" + g.getGstrength(), "td"));
        html.append(boxInTag("" + g.getGlevel(), "td"));
        html.append(boxInTag("" + g.getGblock(), "td"));
        return html.toString();
    }

    /**
     * Create a report which will take in a character name, identified as an input parameter, and list all
     * the armor and weapons that character has equipped, along with the gems on the equipment.
     *
     * @param c
     */
    static void generateEquipmentReport(CharacterModel c) {
        StringBuilder html = new StringBuilder();
        html.append("<html><head></head><body>");
        System.out.println("Equipment for character: " + c + c.getHasClass() + c.getCharLevel());
        Session session = Database.openSession();


        List<EquipmentModel> mainWeapon = session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :main_weapon_id ")
                .setParameter("main_weapon_id", c.getMainEquipped())
                .list();

        if (mainWeapon.size() != 0) {

        }


        List<GemModel> mainGems =
                session.createQuery("SELECT GM FROM GemModel GM, MainEmbedModel ME where ME.mainWeaponInstanceId = :main_weapon_id and GM.gemId = ME.gemId")
                        .setParameter("main_weapon_id", c.getMainEquipped())
                        .list();

        System.out.println("Main Weapon: " + mainWeapon);
        System.out.println("Main Weapon Gems: " + mainGems);

        html.append("<h4>Main Weapon</h4>");
        html.append("<table>");
        html.append(equipmentHeader());
        for (EquipmentModel mWeapon : mainWeapon) {
            html.append(equipmentRow(mWeapon));
        }
        html.append("</table>");

        html.append("<h5>Gems</h5>");
        html.append("<table>");
        html.append(gemHeader());
        for (GemModel gem : mainGems) {
            html.append(gemRow(gem));
        }
        html.append("</table>");

        List<EquipmentModel> secondaryWeapon =
                session.createQuery("SELECT EQ from EquipmentModel EQ where  EQ.eqpId = :secondary_weapon_id ")
                        .setParameter("secondary_weapon_id", c.getSecondaryEquipped())
                        .list();

        html.append("<h4>Secondary Weapon</h4>");
        html.append("<table>");
        html.append(equipmentHeader());
        for (EquipmentModel wp : secondaryWeapon) {
            html.append(equipmentRow(wp));
        }
        html.append("</table>");

        List<GemModel> secondaryGems =
                session.createQuery("SELECT GM FROM GemModel GM, SecondaryEmbedModel ME where ME.secondaryWeaponInstanceId = :weapon_id and GM.gemId = ME.gemId")
                        .setParameter("weapon_id", c.getSecondaryEquipped())
                        .list();

        System.out.println("Secondary Weapon: " + secondaryWeapon);
        System.out.println("Secondary Weapon Gems: " + secondaryGems);

        html.append("<h5>Gems</h5>");
        html.append("<table>");
        html.append(gemHeader());
        for (GemModel gem : secondaryGems) {
            html.append(gemRow(gem));
        }
        html.append("</table>");

        List<EquipmentModel> armour =
                session.createQuery("SELECT EQ from EquipmentModel EQ where EQ.eqpId = :armour_id")
                        .setParameter("armour_id", c.getArmourEquipped())
                        .list();

        html.append("<h4>Armour</h4>");
        html.append("<table>");
        html.append(equipmentHeader());
        for (EquipmentModel wp : armour) {
            html.append(equipmentRow(wp));
        }
        html.append("</table>");

        List<GemModel> armourGems =
                session.createQuery("SELECT GM FROM GemModel GM, ArmourEmbedModel ME where ME.armourInstanceId = :weapon_id and GM.gemId = ME.gemId")
                        .setParameter("weapon_id", c.getArmourEquipped())
                        .list();

        System.out.println("Armour: " + mainWeapon);
        System.out.println("Armour Gems: " + mainGems);

        System.out.println("main: " + c.getMainEquipped() + " secondary: " + c.getSecondaryEquipped() + " armour: " + c.getArmourEquipped());;

        html.append("<h5>Gems</h5>");
        html.append("<table>");
        html.append(gemHeader());
        for (GemModel gem : armourGems) {
            html.append(gemRow(gem));
        }
        html.append("</table>");

        html.append("</body></html>");
        openInBrowser(html, c.getCharName().trim() + " equipment report");
        session.close();
    }

    public static void generateEquipmentReport(String character_name) {
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
    private Integer speed, block, power, will, life, defence, strength = 0;

    StatsContainer() {
    }

    StatsContainer(Integer speed, Integer block, Integer power, Integer will, Integer life, Integer defence, Integer strength) {
        this.speed = speed == null ? 0 : speed;
        this.block = block == null ? 0 : block;
        this.power = power == null ? 0 : power;
        this.will = will == null ? 0 : will;
        this.life = life == null ? 0 : life;
        this.defence = defence == null ? 0 : defence;
        this.strength = strength == null ? 0 : strength;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getBlock() {
        return block;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getWill() {
        return will;
    }

    public Integer getLife() {
        return life;
    }

    public Integer getDefence() {
        return defence;
    }

    public Integer getStrength() {
        return strength;
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

class CharacterNotFoundException extends RuntimeException {

}