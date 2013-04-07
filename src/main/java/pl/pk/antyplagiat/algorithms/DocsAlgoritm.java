package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import algorithms.helpers.DictionaryExtractor;
import algorithms.helpers.KarpRabinAlgorithm;

public class DocsAlgoritm {
	
	/* 
	 * pkt 2,3,4 z dokumentu analizy
	 * metoda jako parametr wejsciowy przyjmuje paragraf, a zwraca liste, w której znajduja
	 * siê percentOdWords% wyrazow najrzadziej i najczesciej wystepujacych
	 */
	public List<String> createDictionaryFromParagraph(String paragraph, int percentOfWords)
	{
		DictionaryExtractor ext = new DictionaryExtractor();
		paragraph = ext.cleanParagrapg(paragraph);
		List<String> words = ext.getWordsFromParagraph(paragraph);
		Map<String, Integer> wordsCount = ext.countWords(words);
		ArrayList<Map.Entry<String, Integer>> entries = ext.sortWordsByCount(wordsCount);		
		List<String> dictionary = ext.getDictionary(entries, percentOfWords);
		
		////debug
		System.out.println("dictionary created: " +dictionary.toString());
		return dictionary;
	}
	
	/* 
	 * pkt 5 z dokumentu analizy
	 * dictionary - jest to slownik do paragrafu z pracy ktora sprawdzamy
	 * dictionaryFromDatabase - jest to slownik do paragrafu z pracy z naszej bazy danych
	 * metoda porownuje te dwa slowniki i jezeli ich podobienstwo przekracza procentowy limit podany jako parametr
	 * metoda zwraca true, a to oznacza ze nalezy uruchomic algorytm Karpa-Rabina
	 * percentageLimit 86% - ozn. ze jezeli podobienstwo wyniesie 90% to metoda zwroci true
	 */
	public boolean compareDictionaries(List<String> dictionary, List<String> dictionaryFromDatabase, int percentageLimit)
	{
		int count = dictionary.size()>dictionaryFromDatabase.size()? dictionary.size():dictionaryFromDatabase.size();
		
		int matches = 0;
		for(String word : dictionary)
		{
			for(String match : dictionaryFromDatabase)
			{
				if(word.equals(match))
				{
					matches++;
					break;
				}
			}
			
		}
		
		double result = (double)matches/count * 100;
		
		if(result > percentageLimit)
		{
			return true;
		}
		
		return false;
	}
	
	/*
	 * metoda uruchamia algorytm KarpaRabina dla textow przekazanych w parametrach wejsciowych
	 * jezeli nie znaleziono zadnych powtorzen zwraca null
	 * jezeli znaleziono powtorzenia, zwraca mape, gdzie kazdy element to: znaleziony wzorzec z lista indeksow poczatkowych wystapienia tego wzorca w tekscie
	 */
	public Map<String, List<Integer>> runAlgorithmKarpaRabina(String paragraph, String paragraphFromDatabase, int limitPowtorzen)
	{
		KarpRabinAlgorithm kra = new KarpRabinAlgorithm();
		
		int dlugoscWzorca = 5;
		
		int dlugoscParagrafu = paragraph.length();
		int iloscWzorcow = dlugoscParagrafu/50;
		
		
		//System.out.println("wylosowano" + rand);
		int znalezionychWzorcow = 0;
		
		Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
		
		for(int i = 0; i < iloscWzorcow; i++)
		{
			try
			{
				int rand = (int) (Math.random()*50);
				int index = rand + (i*50);
				int beginIndex = paragraph.indexOf(" ", index);
				int endIndex = beginIndex +1; 
				for(int j=0; j<dlugoscWzorca; j++)
				{
					endIndex = paragraph.indexOf(" ", endIndex)+1;
				}
				String wzorzec = "";
				
			
				wzorzec = paragraph.substring(beginIndex, endIndex);
			
				List<Integer> indeksy = kra.runKarpRabinAlgoritm(wzorzec, paragraphFromDatabase);
				if(indeksy.size() > 0)
				{
					znalezionychWzorcow++;
					result.put(wzorzec, indeksy);
				}
				}
			catch(IndexOutOfBoundsException e)
			{
				System.out.println("koniec tekstu");
			}
		}
		
		return result; 
	}
	
}
