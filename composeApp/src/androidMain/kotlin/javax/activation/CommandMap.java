package javax.activation;

public abstract class CommandMap {
    private static CommandMap defaultCommandMap;
    public abstract DataContentHandler createDataContentHandler(String str);
    public abstract CommandInfo[] getAllCommands(String str);
    public abstract CommandInfo getCommand(String str, String str2);
    public String[] getMimeTypes() {
        return null;
    }
    public abstract CommandInfo[] getPreferredCommands(String str);
    public static CommandMap getDefaultCommandMap() {
        if (null == CommandMap.defaultCommandMap) {
            defaultCommandMap = new MailcapCommandMap();
        }
        return defaultCommandMap;
    }
    public static void setDefaultCommandMap(CommandMap commandMap) {
        SecurityManager securityManager = System.getSecurityManager();
        if (null != securityManager) {
            try {
                securityManager.checkSetFactory();
            } catch (SecurityException e2) {
                if (CommandMap.class.getClassLoader() != commandMap.getClass().getClassLoader()) {
                    throw e2;
                }
            }
        }
        defaultCommandMap = commandMap;
    }
    public CommandInfo[] getPreferredCommands(String str, DataSource dataSource) {
        return getPreferredCommands(str);
    }
    public CommandInfo[] getAllCommands(String str, DataSource dataSource) {
        return getAllCommands(str);
    }
    public CommandInfo getCommand(String str, String str2, DataSource dataSource) {
        return getCommand(str, str2);
    }
    public DataContentHandler createDataContentHandler(String str, DataSource dataSource) {
        return createDataContentHandler(str);
    }
}
