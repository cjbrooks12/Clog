package com.caseyjbrooks.clog;

import com.caseyjbrooks.clog.parseltongue.Parseltongue;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class Clog {
    private static final String CLASS_NAME = Clog.class.getName();
    private static HashMap<String, Clog> profiles;
    private static Clog instance;

    private HashMap<String, ClogLogger> loggers;
    private ClogFormatter formatter;
    private String defaultTag;
    private String lastTag;
    private String lastLog;

    static {
        profiles = new HashMap<>();
        profiles.put(null, new Clog());
    }

    /**
     * Get the instance of Clog to log to. If the instance is defined, use that profile, otherwise
     * create a new default profile.
     *
     * @return the current Clog profile instance
     */
    private static Clog getInstance() {
        if(instance == null) {
            if(profiles == null || profiles.get(null) == null) {
                instance = new Clog();
            }
            else {
                instance = profiles.get(null);
            }
        }

        return instance;
    }

    /**
     * Initialize Clog with the default configuration, using a simple logger and the Parseltongue formatter
     */
    private Clog() {
        loggers = new HashMap<>();
        loggers.put(null, new DefaultLogger());
        formatter = new Parseltongue();
    }

    /**
     * Initialize a custom Clog instance to be used as a custom profile
     *
     * @param loggers
     * @param formatter
     */
    public Clog(HashMap<String, ClogLogger> loggers, ClogFormatter formatter) {
        this.loggers = loggers;
        this.formatter = formatter;
    }

// Log messages with Clog
//--------------------------------------------------------------------------------------------------

    /**
     * Handy function to get a loggable stack trace from a Throwable. Copied from the Android
     * Open Source Project Log.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/util/Log.java#329">Log.java#329</a>}.
     *
     * @param tr An exception to log
     */
    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }
        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, false);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    public static String format(String message, Object... args) {
        return getInstance().formatter.format(message, args);
    }

    public static int log(Throwable throwable)                                                    { return logger(null,       getStackTraceString(throwable)); }
    public static int   d(Throwable throwable)                                                    { return logger("d",        getStackTraceString(throwable)); }
    public static int   e(Throwable throwable)                                                    { return logger("e",        getStackTraceString(throwable)); }
    public static int   i(Throwable throwable)                                                    { return logger("i",        getStackTraceString(throwable)); }
    public static int   v(Throwable throwable)                                                    { return logger("v",        getStackTraceString(throwable)); }
    public static int   w(Throwable throwable)                                                    { return logger("w",        getStackTraceString(throwable)); }
    public static int wtf(Throwable throwable)                                                    { return logger("wtf",      getStackTraceString(throwable)); }

    public static int log(String tag, Throwable throwable)                                        { return logger(null,  tag, getStackTraceString(throwable)); }
    public static int   d(String tag, Throwable throwable)                                        { return logger("d",   tag, getStackTraceString(throwable)); }
    public static int   e(String tag, Throwable throwable)                                        { return logger("e",   tag, getStackTraceString(throwable)); }
    public static int   i(String tag, Throwable throwable)                                        { return logger("i",   tag, getStackTraceString(throwable)); }
    public static int   v(String tag, Throwable throwable)                                        { return logger("v",   tag, getStackTraceString(throwable)); }
    public static int   w(String tag, Throwable throwable)                                        { return logger("w",   tag, getStackTraceString(throwable)); }
    public static int wtf(String tag, Throwable throwable)                                        { return logger("wtf", tag, getStackTraceString(throwable)); }

    public static int log(String formatString, Object... args)                                    { return logger(null,       formatString,                    args); }
    public static int   d(String formatString, Object... args)                                    { return logger("d",        formatString,                    args); }
    public static int   e(String formatString, Object... args)                                    { return logger("e",        formatString,                    args); }
    public static int   i(String formatString, Object... args)                                    { return logger("i",        formatString,                    args); }
    public static int   v(String formatString, Object... args)                                    { return logger("v",        formatString,                    args); }
    public static int   w(String formatString, Object... args)                                    { return logger("w",        formatString,                    args); }
    public static int wtf(String formatString, Object... args)                                    { return logger("wtf",      formatString,                    args); }

    public static int log(String tag, String formatString, Object... args)                        { return logger(null,  tag, formatString,                    args); }
    public static int   d(String tag, String formatString, Object... args)                        { return logger("d",   tag, formatString,                    args); }
    public static int   e(String tag, String formatString, Object... args)                        { return logger("e",   tag, formatString,                    args); }
    public static int   i(String tag, String formatString, Object... args)                        { return logger("i",   tag, formatString,                    args); }
    public static int   v(String tag, String formatString, Object... args)                        { return logger("v",   tag, formatString,                    args); }
    public static int   w(String tag, String formatString, Object... args)                        { return logger("w",   tag, formatString,                    args); }
    public static int wtf(String tag, String formatString, Object... args)                        { return logger("wtf", tag, formatString,                    args); }

    public static int log(String formatString, Throwable throwable, Object... args)               { return logger(null,       formatString, throwable,         args); }
    public static int   d(String formatString, Throwable throwable, Object... args)               { return logger("d",        formatString, throwable,         args); }
    public static int   e(String formatString, Throwable throwable, Object... args)               { return logger("e",        formatString, throwable,         args); }
    public static int   i(String formatString, Throwable throwable, Object... args)               { return logger("i",        formatString, throwable,         args); }
    public static int   v(String formatString, Throwable throwable, Object... args)               { return logger("v",        formatString, throwable,         args); }
    public static int   w(String formatString, Throwable throwable, Object... args)               { return logger("w",        formatString, throwable,         args); }
    public static int wtf(String formatString, Throwable throwable, Object... args)               { return logger("wtf",      formatString, throwable,         args); }

    public static int log(String tag, String formatString, Throwable throwable, Object... args)   { return logger(null,  tag, formatString, throwable,         args); }
    public static int   d(String tag, String formatString, Throwable throwable, Object... args)   { return logger("d",   tag, formatString, throwable,         args); }
    public static int   e(String tag, String formatString, Throwable throwable, Object... args)   { return logger("e",   tag, formatString, throwable,         args); }
    public static int   i(String tag, String formatString, Throwable throwable, Object... args)   { return logger("i",   tag, formatString, throwable,         args); }
    public static int   v(String tag, String formatString, Throwable throwable, Object... args)   { return logger("v",   tag, formatString, throwable,         args); }
    public static int   w(String tag, String formatString, Throwable throwable, Object... args)   { return logger("w",   tag, formatString, throwable,         args); }
    public static int wtf(String tag, String formatString, Throwable throwable, Object... args)   { return logger("wtf", tag, formatString, throwable,         args); }

    /**
     * Format and log a message with the default tag to the specified logger. Passing null as 'logger'
     * will use the default logger.
     *
     * @param logger the key of the specific logger
     * @param formatString the string to be formatted and logged
     * @param args the list of objects to format into the format string
     * @return int
     */
    public static int logger(String logger, String formatString, Object... args) {
        return logger(logger, getTag(), formatString, args);
    }

    /**
     * Format and log a message and tag to the specified logger
     *
     * @param logger the key of the specific logger
     * @param tag the string to be used as a tag
     * @param formatString the string to be formatted and logged
     * @param args the list of objects to format into the format string
     * @return int
     */
    public static int logger(String logger, String tag, String formatString, Object... args) {
        return logger(logger, tag, formatString, null, args);
    }

    /**
     * Format and log a message, tag, and throwable to the specified logger
     *
     * @param logger the key of the specific logger
     * @param tag the string to be used as a tag
     * @param formatString the string to be formatted and logged
     * @param args the list of objects to format into the format string
     * @return int
     */
    public static int logger(String logger, String tag, String formatString, Throwable throwable, Object... args) {
        return getInstance().loggerInternal(logger, tag, formatString, throwable, args);
    }

    /**
     * Format and log a message, tag, and throwable to the specified logger
     *
     * @param logger the key of the specific logger
     * @param tag the string to be used as a tag
     * @param formatString the string to be formatted and logged
     * @param args the list of objects to format into the format string
     * @return int
     */
    private int loggerInternal(String logger, String tag, String formatString, Throwable throwable, Object... args) {
        String formattedMessage = formatter.format(formatString, args);
        lastLog = formattedMessage;
        lastTag = tag;

        if(loggers.containsKey(logger) && (loggers.get(logger) != null)) {
            if(throwable != null) {
                return loggers.get(logger).log(tag, formattedMessage, throwable);
            }
            else {
                return loggers.get(logger).log(tag, formattedMessage);
            }
        }
        else {
            if(throwable != null) {
                return loggers.get(null).log(tag, formattedMessage, throwable);
            }
            else {
                return loggers.get(null).log(tag, formattedMessage);
            }
        }
    }

// Configure Clog
//--------------------------------------------------------------------------------------------------

    /**
     * Add a new Clog profile named 'key'
     *
     * @param key the key of the profile
     * @param clog the profile
     */
    public static void addProfile(String key, Clog clog) {
        profiles.put(key, clog);
    }

    /**
     * Remove the profile at 'key'
     *
     * @param key the key of the profile to remove
     */
    public static void removeProfile(String key) {
        profiles.remove(key);
    }

    /**
     * Keys the current profile to the one named 'key'
     *
     * @param key the key of the profile to use
     */
    public static void setCurrentProfile(String key) {
        Clog.instance = profiles.get(key);
    }

    /**
     * Add a new Clog profile named 'key', and then switches to that profile
     *
     * @param key
     * @param clog
     */
    public static void setCurrentProfile(String key, Clog clog) {
        addProfile(key, clog);
        setCurrentProfile(key);
    }

    /**
     * Returns all currently configured profiles
     *
     * @return the map of profiles
     */
    public static HashMap<String, Clog> getProfiles() {
        return profiles;
    }

    /**
     * Set all profiles
     *
     * @param profiles the profile set to use
     */
    public static void setAllProfiles(HashMap<String, Clog> profiles) {
        Clog.profiles = profiles;
    }



    /**
     * Get the loggers for the current profile
     *
     * @return the map of loggers
     */
    public static HashMap<String, ClogLogger> getLoggers() {
        return getInstance().loggers;
    }

    /**
     * Get the keys for all loggers in the current profile
     *
     * @return a list of String keys which map to loggers
     */
    public static ArrayList<String> getLoggerKeys() {
        return new ArrayList<>(getInstance().loggers.keySet());
    }

    /**
     * Replace all loggers in the current profile with a new set
     *
     * @param loggers the new hashmap of loggers
     */
    public static void setLoggers(HashMap<String, ClogLogger> loggers) {
        getInstance().loggers = loggers;
    }

    /**
     * Add a new logger to the current profile, replacing any existing logger already set with the key
     *
     * @param key the logger key
     * @param logger the logger to add or replace
     */
    public static void addLogger(String key, ClogLogger logger) {
        getInstance().loggers.put(key, logger);
    }

    /**
     * Sets the default logger to use with the current profile, replacing any existing default logger.
     * This default logger will be used in calls to Clog.log(...) or Clog.logger(null, ...)
     *
     * @param logger the logger to add or replace
     */
    public static void setDefaultLogger(ClogLogger logger) {
        getInstance().loggers.put(null, logger);
    }

    /**
     * Remove the logger at the specified key from the current profile
     *
     * @param key the key mapping to the logger to be removed
     */
    public static void removeLogger(String key) {
        getInstance().loggers.remove(key);
    }

    /**
     * Set the Clog formatter implementation to be used in the current profile
     *
     * @param formatter the formatter implementation to use
     */
    public static void getFormatter(ClogFormatter formatter) {
        getInstance().formatter = formatter;
    }

    /**
     * Get the Clog formatter implementation used in the current profile
     *
     * @return the current formatter
     */
    public static ClogFormatter getFormatter() {
        return getInstance().formatter;
    }

    /**
     * Get the tag from the most recently logged message in the current profile
     *
     * @return the tag on last message logged
     */
    public static String getLastTag() {
        return getInstance().lastTag;
    }

    /**
     * Get the most recently logged message in the current profile
     *
     * @return the last message logged
     */
    public static String getLastLog() {
        return getInstance().lastLog;
    }

// Set and get the default logger tag.
//--------------------------------------------------------------------------------------------------

    /**
     * Get the default tag to be used with all logging messages in the current profile. A default tag
     * of 'null' indicates that all logged messages will use the caller's simple class name as the tag.
     *
     * @return the default tag, or null
     */
    public static String getDefaultTag() {
        return getInstance().defaultTag;
    }

    /**
     * Set the default tag to be used with all logging messages in the current profile. A default tag
     * of 'null' indicates that all logged messages will use the caller's simple class name as the tag.
     *
     * @param defaultTag the default tag, or null
     */
    public static void setDefaultTag(String defaultTag) {
        getInstance().defaultTag = defaultTag;
    }

    /**
     * Get the default tag in the current profile. If the default tag is defined, use that,
     * otherwise attempt to find the caller simple class name and use that as the tag.
     *
     * @return the default tag
     */
    private static String getTag() {
        if(getInstance().defaultTag != null) {
            return getInstance().defaultTag;
        }
        else {
            return findCallerClassName();
        }
    }

    /**
     * Finds the external class name that directly called a Clog method. Copied from the Android
     * Open Source Project LogUtil.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/tools/tradefederation/+/master/src/com/android/tradefed/log/LogUtil.java#324">LogUtil.java#324</a>}.
     *
     * @return The simple class name (or full-qualified if an error occurs getting a ref to
     *         the class) of the external class that called a CLog method, or "Unknown" if
     *         the stack trace is empty or only contains CLog class names.
     */
    private static String findCallerClassName() {
        return findCallerClassName(null);
    }

    /**
     * Finds the external class name that directly called a CLog method. Copied from the Android
     * Open Source Project LogUtil.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/tools/tradefederation/+/master/src/com/android/tradefed/log/LogUtil.java#336">LogUtil.java#336</a>}.
     *
     * @param t (Optional) the stack trace to search within, exposed for unit testing
     * @return The simple class name (or full-qualified if an error occurs getting a ref to
     *         the class) of the external class that called a CLog method, or "Unknown" if
     *         the stack trace is empty or only contains CLog class names.
     */
    private static String findCallerClassName(Throwable t) {
        String className = "Unknown";
        if (t == null) {
            t = new Throwable();
        }
        StackTraceElement[] frames = t.getStackTrace();
        if (frames.length == 0) {
            return className;
        }
        // starting with the first frame's class name (this CLog class)
        // keep iterating until a frame of a different class name is found
        int f;
        for (f = 0; f < frames.length; f++) {
            className = frames[f].getClassName();
            if (!className.equals(CLASS_NAME)) {
                break;
            }
        }
        return parseClassName(className);
    }

    /**
     * Parses the simple class name out of the full class name. If the formatting already
     * looks like a simple class name, then just returns that. Copied from the Android
     * Open Source Project LogUtil.java class.
     *
     * See {@link <a href="https://android.googlesource.com/platform/tools/tradefederation/+/master/src/com/android/tradefed/log/LogUtil.java#368">LogUtil.java#368</a>}.
     *
     * @param fullName the full class name to parse
     * @return The simple class name
     */
    private static String parseClassName(String fullName) {
        int lastdot = fullName.lastIndexOf('.');
        String simpleName = fullName;
        if (lastdot != -1) {
            simpleName = fullName.substring(lastdot + 1);
        }
        // handle inner class names
        int lastdollar = simpleName.lastIndexOf('$');
        if (lastdollar != -1) {
            simpleName = simpleName.substring(0, lastdollar);
        }
        return simpleName;
    }
}
