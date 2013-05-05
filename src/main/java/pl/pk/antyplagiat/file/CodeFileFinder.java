package pl.pk.antyplagiat.file;

import java.io.File;
import java.util.ArrayList;

public class CodeFileFinder {

    private ArrayList<String> codeFileExtensions;

    public ArrayList<String> getCodeFileExtensions() {
        return codeFileExtensions;
    }

    public void setCodeFileExtensions(ArrayList<String> codeFileExtensions) {
        this.codeFileExtensions = codeFileExtensions;
    }

    public CodeFileFinder() {
        codeFileExtensions = new ArrayList<String>();
        codeFileExtensions.add("java");
        codeFileExtensions.add("cs");
    }

    public ArrayList<String> findAllCodeFiles(String path) {
        ArrayList<String> codeFiles = new ArrayList<String>();

        File root = new File(path);
        if (!root.exists() || root.isFile()) {
            return codeFiles;
        }
        File[] list = root.listFiles();

        for (File file : list) {
            if (file.isDirectory()) {
                codeFiles.addAll(findAllCodeFiles(file.getAbsolutePath()));
            } else {
                if (isCodeFile(file)) {
                    codeFiles.add(file.getAbsolutePath());
                }
            }
        }
        return codeFiles;
    }

    private Boolean isCodeFile(File file) {
        for (String codeFileExtension : getCodeFileExtensions()) {
            if (getFileExtension(file).equals(codeFileExtension)) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }
}
