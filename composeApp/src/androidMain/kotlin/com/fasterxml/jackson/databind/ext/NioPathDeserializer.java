package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBase;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Set;

public class NioPathDeserializer extends StdScalarDeserializer<Path> {
    private static final boolean areWindowsFilePathsSupported;
    private static final long serialVersionUID = 1;
    static {
        File[] fileArrListRoots = File.listRoots();
        int length = fileArrListRoots.length;
        boolean z = false;
        int r3 = 0;
        while (true) {
            if (r3 >= length) {
                break;
            }
            String path = fileArrListRoots[r3].getPath();
            if (path.length() >= 2 && Character.isLetter(path.charAt(0)) && path.charAt(1) == ':') {
                z = true;
                break;
            }
            r3++;
        }
        areWindowsFilePathsSupported = z;
    }
    public NioPathDeserializer() {
        super(Path.class);
    }
    public Path deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        if (!jsonParser.hasToken(JsonToken.VALUE_STRING)) {
            return (Path) deserializationContext.handleUnexpectedToken(Path.class, jsonParser);
        }
        String text = jsonParser.getText();
        if (text.indexOf(58) < 0) {
            return Paths.get(text);
        }
        if (areWindowsFilePathsSupported && text.length() >= 2 && Character.isLetter(text.charAt(0)) && text.charAt(1) == ':') {
            return Paths.get(text);
        }
        try {
            URI r0 = new URI(text);
            try {
                return Paths.get(r0);
            } catch (FileSystemNotFoundException e2) {
                try {
                    String scheme = r0.getScheme();
                    Iterator it = ServiceLoader.load(FileSystemProvider.class).iterator();
                    while (it.hasNext()) {
                        FileSystemProvider fileSystemProvider = (FileSystemProvider) it.next();
                        if (fileSystemProvider.getScheme().equalsIgnoreCase(scheme)) {
                            return fileSystemProvider.getPath(r0);
                        }
                    }
                    return (Path) deserializationContext.handleInstantiationProblem(this.handledType(), text, e2);
                } catch (Throwable th) {
                    th.addSuppressed(e2);
                    return (Path) deserializationContext.handleInstantiationProblem(this.handledType(), text, th);
                }
            } catch (Throwable th2) {
                return (Path) deserializationContext.handleInstantiationProblem(this.handledType(), text, th2);
            }
        } catch (URISyntaxException e3) {
            return (Path) deserializationContext.handleInstantiationProblem(handledType(), text, e3);
        }
    }
    protected BeanDeserializerBase withByNameInclusion(Set<String> set, Set<String> set2) {
        return null;
    }
}
