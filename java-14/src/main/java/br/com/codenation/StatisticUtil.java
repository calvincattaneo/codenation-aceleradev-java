package br.com.codenation;
import java.util.Arrays;

public class StatisticUtil {
	static int arraySize;
	
	public static int average(int[] elements) {
		int some = 0;
		arraySize = elements.length;
		
		if (arraySize == 0) return 0;
		
		for (int i = 0; i < arraySize; i++) {
			some += +elements[i];
		}
		return some / arraySize;
	}

	public static int mode(int[] elements) {
		Arrays.sort(elements);
		arraySize = elements.length;
		int	mode = 0, compared = 0;		
		int frequency = 1, highest = 1;
			
		for (int i = 0; i < arraySize; i++) {
			if (i != 0 || i == arraySize -1 ) compared = i - 1;
				else compared = i;
			
			if(elements[i] != elements[compared]) frequency = 1;
			
			while (elements[i] == elements[compared] && compared != i) {
				if (compared <= arraySize-2) compared++;
				frequency++;
			}

			if (frequency > highest ) {
				mode = elements[i];
				highest = frequency;		
			}		
		}
		return mode;
	}

	public static int median(int[] elements) {
		arraySize = elements.length;
		Arrays.sort(elements);
		int posMediana = 0;
		
		if (arraySize == 0) return 0;
		
		posMediana = (arraySize / 2);
		if (arraySize % 2 == 0) {	
			return (elements[posMediana - 1] + elements[posMediana]) / 2;
		}
		return elements[posMediana];
	}
}