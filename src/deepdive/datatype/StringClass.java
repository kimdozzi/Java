package deepdive.datatype;

public class StringClass {
    public static void main(String[] args) {
        String str = "apple";

        // 길이 반환
        str.length();

        // 빈 문자열 체크
        str.isEmpty();

        // 문자 찾기
        str.charAt(0);
        str.indexOf("p"); // 1 -> 인덱스 0부터 n-1까지 해당 문자열의 위치를 찾아서 반환
        str.lastIndexOf("p"); // 2

        // 문자 자르기
        str.substring(3); // 인덱스 3을 포함한 위치부터 끝까지 문자열 자름
        str.substring(1,3); // 1부터 2(3-1)위치까지 자름

        // 문자 치환
        str.replace('p', 'f');
        str.replaceAll("pp", "/");
        str.replaceFirst("p", "f");

        // 동일 여부 판단
        str.equals("apple");

        // 포함 여부 판단
        str.contains("app");

        // 문자 비교
        str.compareTo("banana");

        // 문자 앞뒤 공백 제거
        str.trim();

        // 문자 캐스팅 변환
        Integer.parseInt("100");
        Integer.toString(100);


        // StringBuilder
        StringBuilder sb = new StringBuilder();

        // append
        sb.append("apple");

        // insert
        sb.insert(sb.length(), "s");

        // delete
        sb.delete(0,1);

        sb.deleteCharAt(sb.length()-1);

        // update
        sb.setCharAt(sb.length()-1, 'e');

        // reverse
        sb.reverse();

        // 문자열 절대 길이 줄이기(?)
        sb.setLength(3);

    }
}
