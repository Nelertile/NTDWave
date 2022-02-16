package io.github.nottoxicdev;

public class GameMeta {

    public enum TAG {
        dev,
        beta,
        full;
    }

    public static String ConstructGameMeta(String gameName, int major, int minor, TAG tag, int tagversion) {
        String verString = "";
        if (tag == TAG.full) {
            verString = (gameName + "; v" + major + "." + minor);

        } else {
            verString = (gameName + "; " + "v" + major + "." + minor + "-" + tag + "." + tagversion);
        }
        return verString;
    }

    public static String ConstructModMeta(String modName, int major, int minor, TAG tag, int tagversion) {
        String modString = "";
        if (tag == TAG.full) {
            modString = (modName + "; v" + major + "." + minor + "-" + tag + "." + tagversion);
        } else {
            modString = (modName + "; v" + major + "." + minor);
        }
        return modString;
    }
}
