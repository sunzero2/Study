package gugudan;

public class Gugudan {

	public static void main(String[] args) {
		int dan = 13;
		
		for(int i = 2; i <= dan; i = i + 3) {
			for(int j = 1; j < 4; j++) {
				String output = outputConcat(i, j);
				if((i + 1) <= dan) {
					output = output.concat(outputConcat((i + 1), j));
					if((i + 2) <= dan) {
						output = output.concat(outputConcat((i + 2), j));
					}
				}
				System.out.println(output);
			}
			
			System.out.println();
		}
	}
	
	public static String outputConcat(int dan, int multiply) {
		String format = dan + "*" + multiply + "=" + dan * multiply + "\t\t";
		return format;
	}
}
