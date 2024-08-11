package datastructure.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Trie {
    private TrieNode rootNode;

    Trie() {
        rootNode = new TrieNode();
    }

    void insert(String word) {
        TrieNode thisNode = this.rootNode;  // 루트 노드에서 시작
        for (int i = 0; i < word.length(); i++) {  // 문자열의 각 문자에 대해 반복
            char currentChar = word.charAt(i);  // 현재 문자 가져오기
            // 자식 노드 맵에서 현재 문자 키에 해당하는 노드 가져오기
            TrieNode childNode = thisNode.getChildNodes().get(currentChar);
            if (childNode == null) {  // 노드가 없으면 새롭게 생성하여 추가
                childNode = new TrieNode();
                thisNode.getChildNodes().put(currentChar, childNode);
                System.out.println(thisNode.getChildNodes().entrySet());
            }
            thisNode = childNode;  // 다음 문자로 이동
        }
        thisNode.setIsLastChar(true);  // 마지막 문자 노드에 단어 끝 표시
    }

//    void insert(String word) {
//        TrieNode thisNode = this.rootNode;
//        for (int i=0; i<word.length(); i++) {
//            thisNode = thisNode.getChildNodes().computeIfAbsent(word.charAt(i),
//                    c -> new TrieNode());
//        }
//        thisNode.setIsLastChar(true);
//    }

    boolean contains(String word) {
        TrieNode thisNode = this.rootNode;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            TrieNode node = thisNode.getChildNodes().get(character);
            if (node == null) {
                return false;
            }
            thisNode = node;
        }
        return thisNode.isLastChar();
    }

    void delete(String word) {
        delete(this.rootNode, word, 0);
    }

    private void delete(TrieNode thisNode, String word, int index) {
        char character = word.charAt(index);
        if (!thisNode.getChildNodes().containsKey(character)) {
            throw new Error("There is no [" + word + "] in this Trie.");
        }

        TrieNode childNode = thisNode.getChildNodes().get(character);
        index++;
        if (index == word.length()) {
            if (!childNode.isLastChar()) {
                throw new Error("There is no [" + word + "] in this Trie.");
            }
            childNode.setIsLastChar(false);

            if (childNode.getChildNodes().isEmpty()) {
                thisNode.getChildNodes().remove(character);
            } else {
                delete(childNode, word, index);
                if (!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
                    thisNode.getChildNodes().remove(character);
                }
            }
        }
    }
}


class TrieNode {
    private Map<Character, TrieNode> childNodes = new HashMap<>();
    private boolean isLastChar;

    Map<Character, TrieNode> getChildNodes() {
        return this.childNodes;
    }

    boolean isLastChar() {
        return this.isLastChar;
    }

    void setIsLastChar(boolean isLastChar) {
        this.isLastChar = isLastChar;
    }

}

public class Main {
    public static void main(String[] args) {
        String text = "abac";
        int k = 3;  // 찾고 싶은 k번째 접미어 (1-based index)

        // 접미어의 시작 인덱스를 저장하는 배열 생성
        Integer[] suffixArray = createSuffixArray(text);
        System.out.println(Arrays.toString(suffixArray));

        Arrays.sort(suffixArray, (i, j) -> text.substring(i).compareTo(text.substring(j)));
        int index = suffixArray[k - 1];

    }
    // 문자열의 모든 접미어의 시작 인덱스를 저장하는 배열 생성
    private static Integer[] createSuffixArray(String text) {
        int length = text.length();
        Integer[] suffixArray = new Integer[length];  // 접미어 시작 인덱스를 저장할 배열

        for (int i = 0; i < length; i++) {
            suffixArray[i] = i;  // 각 접미어의 시작 인덱스를 배열에 저장
        }
        return suffixArray;  // 접미어 인덱스 배열 반환
    }
}













