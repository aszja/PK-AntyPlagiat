package algorithms.helpers;

import java.util.ArrayList;
import java.util.List;

public class KarpRabinAlgorithm {

	private static final int r=256;   //liczba symboli alfabetu (char 0-255)
	private static final int q=9551;  //mo¿liwie du¿a liczba pierwsza  
	 
	public int power_modulo_fast(int a, int b, int m) {
		int i;
		int result = 1;
		long x = a%m;
		 
		for (i=1; i<=b; i<<=1) {
			x %= m;
			if ((b&i) != 0) {
			result *= x;
			result %= m;
			}
			x *= x;
		}
		 
		return result%m;
	}
	 
	public List<Integer> runKarpRabinAlgoritm(String wzorzec, String tekst)
	{
		int m,n,i,j,h1,h2,rm;
		
		List<Integer> result = new ArrayList<Integer>();
		
		n=tekst.length();
		m=wzorzec.length();
		h2=0;
		h1=0;
	 
		//algorytm Hornera do obliczenia funkcji haszujacej h(tekst[1..m])
		for (i=0; i<m; i++)
		{
			h2=((h2*r) + tekst.charAt(i));
			h2%=q;
		}
		
		//algorytm Hornera do obliczenia funkcji haszujacej h(wzorzec)
		for (i=0; i<m; i++)
		{
			h1=((h1*r) + wzorzec.charAt(i));
			h1%=q;
		}
		 
		//Algorytm KR (Karpa-Rabina)
		rm=power_modulo_fast(r, m-1, q);
		i=0;
		
		while (i<n-m)
		{
			j=0;
			if (h1==h2) 
			{
				while ((j<m)&&(wzorzec.charAt(j)==tekst.charAt(i+j)))
				{ 
					j++;
				}
			}
			if (j==m) 
			{
				result.add(i+1);
				//System.out.println("debug1:" + i+1);
			}
			h2=((h2-tekst.charAt(i)*rm)*r+tekst.charAt(i+m));
			h2%=q;
			if (h2<0)
			{ 
				h2+=q;
			}
			i++;
		}
		
		j=0;
		if (h1==h2) 
		{
			while ((j<m)&&(wzorzec.charAt(j)==tekst.charAt(i+j))) 
			{
				j++;
			}
		}
		if (j==m) 
		{
			result.add(i+1);
			//System.out.println("poczatek wzorca: " + i+1);
		}
		
		return result;
	}

}
