import java.util.StringJoiner;

public class Main {
	public static void main(String[] args) throws Exception {
		String myString = "ABBAA";
		String[] strArr = myString.split("");
		for (int i = 0; i < strArr.length; i++) {
			if (strArr[i].equals("A")) {
				strArr[i] = "B";
			} else {
				strArr[i] = "A";
			}
		}

		myString = "12345";

		StringJoiner sj = new StringJoiner(":");
		sj.add("0").add(String.valueOf(myString.charAt(0)));
	}
}