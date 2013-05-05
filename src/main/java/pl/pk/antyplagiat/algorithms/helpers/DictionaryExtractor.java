package pl.pk.antyplagiat.algorithms.helpers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryExtractor {

    public String cleanParagrapg(String paragraph) {
        // delete quotes
        while (paragraph.contains("\"")) {
            try {
                int quoteStart = paragraph.indexOf("\"");
                int quoteEnd = paragraph.indexOf("\"", quoteStart + 1);
                paragraph = paragraph.replace(paragraph.substring(quoteStart, quoteEnd + 1), "");
            } catch (StringIndexOutOfBoundsException ex) {
                System.out.println("Wystąpil problem z cytatami - ktorys moglbyc niedomkniety!");
                break;
            }
        }

        // delete przyimki with length >= 3
        String przyimki[] = {"dla", "ponieważ", "oraz", "dlatego", "nad", "poza", "przed", "ponad", "spoza", "pod", "który", "która", "które", "których", "chociaż", "iż", "tych", "tamtych"};
        for (String przyimek : przyimki) {
            paragraph = paragraph.replaceAll(" " + przyimek + " ", "");
        }

        System.out.println(paragraph);

        return paragraph;
    }

    public List<String> getWordsFromParagraph(String paragraph) {
        Pattern p = Pattern.compile("[\\wżźćńółęąśŻŹĆĄŚĘŁÓŃ']{3,50}");
        Matcher m = p.matcher(paragraph);

        List<String> words = new ArrayList<String>();

        while (m.find()) {
            words.add(m.group().toLowerCase());
        }

        return words;
    }

    public Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new HashMap<String, Integer>();

        for (String word : words) {
            if (map.containsKey(word)) {
                // get number of occurrences for this word 
                // increment it  
                // and put back again  
                map.put(word, map.get(word) + 1);
            } else {
                // this is first time we see this word, set value '1' 
                map.put(word, 1);
            }
        }

        return map;
    }

    public ArrayList<Map.Entry<String, Integer>> sortWordsByCount(Map<String, Integer> wordsCount) {
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsCount.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue().compareTo(b.getValue());
            }
        });

        return entries;
    }

    public List<String> getDictionary(ArrayList<Map.Entry<String, Integer>> entries, int percentOfWords) {

        int count = (entries.size() * percentOfWords) / 100;

        List<String> dictionary = new ArrayList<String>();

        for (int i = 0; i < count; i++) {
            dictionary.add(entries.get(i).getKey());
            dictionary.add(entries.get(entries.size() - i - 1).getKey());
        }

        //to debug
        //System.out.println(map.toString());
        //System.out.println(entries.toString());

        return dictionary;
    }
}
