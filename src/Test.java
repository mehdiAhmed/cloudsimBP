
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String memoire = "1,952";
		memoire = memoire.replace(",", ".");
		double mem = Double.parseDouble(memoire);
		System.out.println(mem);
		

	}

}
