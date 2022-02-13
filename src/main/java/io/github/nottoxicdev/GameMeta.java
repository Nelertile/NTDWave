package io.github.nottoxicdev;

public class GameMeta {

    public static String ConstructGameMeta(int major, int minor, String tag, int tagversion) {
        String verString = ("v" + major + "." + minor + "-" + tag + "." + tagversion);
        return verString;
    }

    public static String ConstructModMeta(String modName, int major, int minor, String tag, int tagversion) {
        String modString = (modName + "; v" + major + "." + minor + "-" + tag + "." + tagversion);
        return modString;
    }
}
