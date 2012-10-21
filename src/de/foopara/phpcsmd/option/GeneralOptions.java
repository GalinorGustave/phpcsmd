package de.foopara.phpcsmd.option;

import java.util.EnumMap;
import org.openide.util.Lookup;

/**
 *
 * @author n.specht
 */
public class GeneralOptions {

    public static final String _PREFIX = "phpcsmd.general.";

    public enum Settings {
        THREADED, UPDATEONSAVE, NOTIFY, IGNORE, DEBUGLOG, TIMEOUT
    }

    private static final EnumMap<GeneralOptions.Settings, String> keys = new EnumMap<GeneralOptions.Settings, String>(GeneralOptions.Settings.class);
    static {
        keys.put(GeneralOptions.Settings.THREADED, "threaded");
        keys.put(GeneralOptions.Settings.UPDATEONSAVE, "updateonsave");
        keys.put(GeneralOptions.Settings.NOTIFY, "notification");
        keys.put(GeneralOptions.Settings.IGNORE, "ignorepattern");
        keys.put(GeneralOptions.Settings.DEBUGLOG, "debuglog");
        keys.put(GeneralOptions.Settings.TIMEOUT, "timeout");

    }

    private static final EnumMap<GeneralOptions.Settings, GenericOption.SettingTypes> types = new EnumMap<GeneralOptions.Settings, GenericOption.SettingTypes>(GeneralOptions.Settings.class);
    static {
        types.put(GeneralOptions.Settings.THREADED, GenericOption.SettingTypes.BOOLEAN);
        types.put(GeneralOptions.Settings.UPDATEONSAVE, GenericOption.SettingTypes.BOOLEAN);
        types.put(GeneralOptions.Settings.NOTIFY, GenericOption.SettingTypes.BOOLEAN);
        types.put(GeneralOptions.Settings.DEBUGLOG, GenericOption.SettingTypes.BOOLEAN);
        types.put(GeneralOptions.Settings.TIMEOUT, GenericOption.SettingTypes.INTEGER);
    }

    private static final EnumMap<GeneralOptions.Settings, String> defaults = new EnumMap<GeneralOptions.Settings, String>(GeneralOptions.Settings.class);
    static {
        defaults.put(GeneralOptions.Settings.THREADED, "false");
        defaults.put(GeneralOptions.Settings.UPDATEONSAVE, "false");
        defaults.put(GeneralOptions.Settings.NOTIFY, "false");
        defaults.put(GeneralOptions.Settings.IGNORE, "\\.(svn|git)|\\.(phtml|html|xml|txt|java|svg|png|jpg|gif)$");
        defaults.put(GeneralOptions.Settings.DEBUGLOG, "false");
        defaults.put(GeneralOptions.Settings.TIMEOUT, "5000");
    }

    public static Object load(GeneralOptions.Settings key, Lookup lkp) {
        String defaultVal = "";
        if (GeneralOptions.defaults.containsKey(key)) {
            defaultVal = GeneralOptions.defaults.get(key);
        }

        String val = GenericOption.loadMerged(_PREFIX + keys.get(key), defaultVal, lkp);

        if (!types.containsKey(key)) {
            return val;
        }

        return GenericOption.castValue(val, types.get(key));
    }

    public static Object loadOriginal(GeneralOptions.Settings key) {
        String defaultVal = "";
        if (GeneralOptions.defaults.containsKey(key)) {
            defaultVal = GeneralOptions.defaults.get(key);
        }

        String val = GenericOption.loadModul(_PREFIX +keys.get(key), defaultVal);

        if (!types.containsKey(key)) {
            return val;
        }

        return GenericOption.castValue(val, types.get(key));
    }

    public static void set(GeneralOptions.Settings key, Object value) {
        String val = value.toString();
        if (types.containsKey(key)) {
            val = GenericOption.castValueToString(value, types.get(key));
        }
        GenericOption.setModul(_PREFIX + keys.get(key), val);
    }

    public static void overwrite(GeneralOptions.Settings key, Object value, Lookup lkp) {
        String val = value.toString();
        if (types.containsKey(key)) {
            val = GenericOption.castValueToString(value, types.get(key));
        }
        GenericOption.setProject(_PREFIX + keys.get(key), val, lkp);
    }
}