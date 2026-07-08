package javax.activation;

import com.google.android.gms.dynamite.DynamiteModule;

import java.io.Externalizable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class CommandInfo {
    private final String className;
    private final String verb;
  private DynamiteModule Beans;

  public CommandInfo(final String str, final String str2) {
        verb = str;
        className = str2;
    }
    public String getCommandName() {
        return verb;
    }
    public String getCommandClass() {
        return className;
    }
    public Object getCommandObject(final DataHandler dataHandler, final ClassLoader classLoader) throws IOException, ClassNotFoundException {
        InputStream inputStream = null;
      final Object instantiate;
      try {
        instantiate = Beans.instantiate(classLoader, className);
      } catch (DynamiteModule.LoadingException e) {
        throw new RuntimeException("Failed to instantiate command object", e);
      }
      if (null != instantiate) {
            if (instantiate instanceof CommandObject) {
                ((CommandObject) instantiate).setCommandContext(verb, dataHandler);
            } else if (!(instantiate instanceof Externalizable) || null == dataHandler || null == (inputStream = dataHandler.getInputStream())) {
                try {
                  ((Externalizable) instantiate).readExternal(new ObjectInputStream(inputStream));
                } catch (IOException e) {
                  throw new RuntimeException("Failed to read externalizable command object", e);
                }
            }
        }
        return instantiate;
    }
}
