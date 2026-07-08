package javax.activation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileDataSource implements DataSource {
    private final File _file;
    private FileTypeMap typeMap;
    public FileDataSource(final File file) {
        typeMap = null;
        _file = file;
    }
    public FileDataSource(final String str) {
        this(new File(str));
    }
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(_file);
    }
    public OutputStream getOutputStream() throws IOException {
        return new FileOutputStream(_file);
    }
    public String getContentType() {
        final FileTypeMap fileTypeMap = typeMap;
        if (null == fileTypeMap) {
            return FileTypeMap.getDefaultFileTypeMap().getContentType(_file);
        }
        return fileTypeMap.getContentType(_file);
    }
    public String getName() {
        return _file.getName();
    }
    public File getFile() {
        return _file;
    }
    public void setFileTypeMap(final FileTypeMap fileTypeMap) {
        typeMap = fileTypeMap;
    }
}
