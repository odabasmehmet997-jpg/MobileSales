package org.simpleframework.xml.transform;

import java.io.File;


class FileTransform implements Transform<File> {
    FileTransform() {
    }

    
    @Override // org.simpleframework.xml.transform.Transform
    public File read(final String str) {
        return new File(str);
    }

    @Override // org.simpleframework.xml.transform.Transform
    public String write(final File file) {
        return file.getPath();
    }
}
