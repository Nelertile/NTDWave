package io.github.nottoxicdev;

public class GameMeta {

    public static String ConstructGameMeta(String gameName, int major, int minor, String tag, int tagversion) {
        String verString = "";
        if (tag == "") {
            verString = (gameName + "; " + "v" + major + "." + minor);

        } else {
            verString = (gameName + "; " + "v" + major + "." + minor + "-" + tag + "." + tagversion);
        }
        return verString;
    }

    public static String ConstructModMeta(String modName, int major, int minor, String tag, int tagversion) {
        String modString = (modName + "; v" + major + "." + minor + "-" + tag + "." + tagversion);
        return modString;
    }
}
