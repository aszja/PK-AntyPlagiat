package pl.pk.antyplagiat.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

public class Tests {

    @Test
    public void CodeFileFinderShouldFindCodeFiles() {
        CodeFileFinder finder = new CodeFileFinder();
        ArrayList<String> files = finder.findAllCodeFiles("D:\\Programy\\eclipse\\");
        Assert.assertTrue(files.size() > 0);
        for (String f : files) {
            System.out.println(f);
        }
    }

    @Test
    public void CodeFileFinderShouldReturnEmptyListWhenPathDoesntExists() {
        CodeFileFinder finder = new CodeFileFinder();
        ArrayList<String> files = finder.findAllCodeFiles("D:\\Programasd");
        Assert.assertEquals(files.size(), 0);
    }

    @Test
    public void CodeFileFinderShouldReturnEmptyListWhenPathIsNotDirectory() {
        CodeFileFinder finder = new CodeFileFinder();
        ArrayList<String> files = finder.findAllCodeFiles("D:\\Programy\\eclipse\\eclipse.exe");
        Assert.assertEquals(files.size(), 0);
    }

    @Test
    public void ImportThesisFileShouldCopyFileAndReturnPath() throws IOException {
        ThesisFileImporter importer = new ThesisFileImporter();
        String path = importer.importThesisFile("D:\\Programy\\eclipse\\eclipse.exe");
        Assert.assertNotNull(path);
        File copy = new File(path);
        System.out.println(path);
        Assert.assertTrue(copy.exists());

    }

    @Test
    public void CopyFileTest() throws IOException {
        File source = new File("D:\\Programy\\eclipse\\eclipse.exe");
        File destination = new File("D:\\Programy\\eclipse\\eclipse1.exe");
        FileUtil.copyFile(source, destination);
        Assert.assertTrue(destination.exists());
    }

    @Test
    public void FileService() throws IOException {
        FileService service = new FileService();
        ArrayList<String> importedFiles = service.importThesis("D:\\Programy\\eclipse\\eclipse.exe");
        for (String file : importedFiles) {
            System.out.println(file);
            Assert.assertTrue(new File(file).exists());
        }
    }
}
