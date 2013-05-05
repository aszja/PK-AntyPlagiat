package pl.pk.antyplagiat.algorithms;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pl.pk.antyplagiat.algorithms.helpers.SourceCodeExtractor;

public class SourceCodeAlgoritm {

    private SourceCodeExtractor s = new SourceCodeExtractor();

    /*
     * parametr wejsciowy to string zawierajacy source code
     * metoda tworzy slownik zawierajacy metode, zmienna lub konstruktor i jej ilosc wystapien w pliku
     * 
     */
    public Map<String, Integer> createDictionary(String sourceFile) {
        sourceFile = s.deleteCommnents(sourceFile);
        return s.createDictioriesOfMethodsAndFields(sourceFile);
    }

    /*
     * metoda jako paremetr przyjmuje stringa zawierajacy source code
     * output: lista uzytych bibliotek, defaultowo przewidziane: 
     *  import - java
     *  include - c**
     *  using - c#
     *  
     * Aby rozszerzyc o kolejne obsluge kolejnych jezykow wystarczy dodac linijke:
     * libraries.addAll(s.getListOfUsedLibraries(sourceFile, key)); 
     * gdze key - ozn. slowo kluczowe do zaladowania biblioteki w danym jezyku programowania
     * percentageLimit 86% - ozn. ze jezeli podobienstwo wyniesie 90% to metoda zwroci true
     */
    public List<String> getListOfUsedLibraries(String sourceFile) {
        sourceFile = s.deleteCommnents(sourceFile);
        List<String> libraries = s.getListOfUsedLibraries(sourceFile, "import");
        libraries.addAll(s.getListOfUsedLibraries(sourceFile, "include"));
        libraries.addAll(s.getListOfUsedLibraries(sourceFile, "using"));
        // dodaj tutaj obsluge innych jezykow, jezeli potrzeba

        return libraries;
    }

    /*
     * parametry to listy bibliotek oraz limit, jezeli podobienstwo bibliotek przekracza limit to zwraca true, jezenie nie to false
     */
    public boolean checkSimilarityOfUsedLibraries(List<String> libs, List<String> libsFromDatabase, int percentLimit) {
        DocsAlgoritm da = new DocsAlgoritm();
        return da.compareDictionaries(libs, libsFromDatabase, percentLimit);
    }

    /*
     * pramerty to slowniki metod, pol i konstuktorow z ich ilosciami wystapien
     * jezeli podobienstwo przekroczy limit to metoda zwraca true
     * percentageLimit 86% - ozn. ze jezeli podobienstwo wyniesie 90% to metoda zwroci true
     */
    public boolean checkSimilarityOfDictionariesLength(Map<String, Integer> dictionary, Map<String, Integer> dictionaryFromDatabase, int percentLimit) {
        int dictCount = 0;
        int DBdictCount = 0;
        percentLimit = 100 - percentLimit;

        Iterator it = dictionary.values().iterator();
        while (it.hasNext()) {
            dictCount += (Integer) it.next();
        }

        Iterator DBit = dictionaryFromDatabase.values().iterator();
        while (DBit.hasNext()) {
            DBdictCount += (Integer) DBit.next();
        }

        double similarity = (((double) dictCount / DBdictCount) * 100) - 100;

        if (similarity < 0) {
            similarity = similarity * (-1);
        }

        if (similarity <= percentLimit) {
            return true;
        }

        return false;
    }
}
