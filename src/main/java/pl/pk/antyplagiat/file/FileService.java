package pl.pk.antyplagiat.file;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import pl.pk.antyplagiat.file.CodeFileFinder;

public class FileService {

    public ArrayList<String> importThesis(String thesisFilePath) throws IOException {
        File thesisFile = new File(thesisFilePath);
        if (!thesisFile.exists()) {
            throw new IOException("thesis file doesn't exists");
        }
        ArrayList<String> importedFiles = new ArrayList<String>();


        ThesisFileImporter importer = new ThesisFileImporter();

        String thesisPath = importer.importThesisFile(thesisFilePath);
        importedFiles.add(thesisPath);

        CodeFileFinder codeFileFinder = new CodeFileFinder();

        String thesisFolder = thesisFile.getParent();
        for (String codeFilePath : codeFileFinder.findAllCodeFiles(thesisFolder)) {
            importedFiles.add(importer.importCodeFile(codeFilePath, thesisFile.getName()));
        }
        return importedFiles;
    }
}
