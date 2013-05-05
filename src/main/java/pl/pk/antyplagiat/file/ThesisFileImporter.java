package pl.pk.antyplagiat.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThesisFileImporter {

    private String thesisFolderName;
    private String codeFolderName;
    private String basePath;

    public ThesisFileImporter() {
        thesisFolderName = "ThesisFiles";
        codeFolderName = "CodeFiles";
        basePath = System.getProperty("user.dir");
    }

    public String getThesisFolderName() {
        return thesisFolderName;
    }

    public void setThesisPath(String thesisFolderName) {
        this.thesisFolderName = thesisFolderName;
    }

    public String getCodeFolderName() {
        return codeFolderName;
    }

    public void setFolderName(String codeFolderName) {
        this.codeFolderName = codeFolderName;
    }

    public String importCodeFile(String path, String thesisName) throws IOException {

        String destinationFolder = FileUtil.combine(basePath, getCodeFolderName());
        if (!new File(destinationFolder).exists()) {
            new File(destinationFolder).mkdirs();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        Date today = new Date();
        String importDate = formatter.format(today);

        File source = new File(path);

        String codeFileName = importDate + "_" + thesisName + "_" + source.getName();

        String destinationPath = FileUtil.combine(destinationFolder, codeFileName);

        File destination = new File(destinationPath);
        FileUtil.copyFile(source, destination);
        return destinationPath;
    }

    public String importThesisFile(String path) throws IOException {
        String destinationFolder = FileUtil.combine(basePath, getThesisFolderName());
        if (!new File(destinationFolder).exists()) {
            new File(destinationFolder).mkdirs();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
        Date today = new Date();
        String importDate = formatter.format(today);
        File source = new File(path);
        String fileName = importDate + "_" + source.getName();
        String destinationPath = FileUtil.combine(destinationFolder, fileName);

        File destination = new File(destinationPath);
        FileUtil.copyFile(source, destination);
        return destinationPath;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
