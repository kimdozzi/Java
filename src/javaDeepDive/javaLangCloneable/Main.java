package javaDeepDive.javaLangCloneable;

import java.util.Arrays;

/*
 * clone 메소드
 * - Object.clone() 메소드는 인스턴스 객체의 복제를 위한 메소드
 * - 해당 인스턴스를 복제하여 새로운 인스턴스를 생성해 그 참조값을 반환
 * */
public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[5][5];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = i + j;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }

        int[][] clone_arr = new int[5][5];
        for (int i=0; i<arr.length; i++) {
            clone_arr[i] = arr[i].clone();
        }
        System.out.println("hashCode");
        System.out.println("arr : " + arr.hashCode());
        System.out.println("clone_arr : " + clone_arr.hashCode());
        System.out.println(arr.equals(clone_arr));
        System.out.println("Arrays.deepHashCode");
        System.out.println("arr : " + Arrays.deepHashCode(arr));
        System.out.println("clone_arr : " + Arrays.deepHashCode(clone_arr));
        System.out.println(Arrays.deepEquals(arr, clone_arr) + "\n");

        for (int i=0; i< clone_arr.length; i++) {
            for (int j=0; j<clone_arr[i].length; j++) {
                clone_arr[i][j] = i + j + 10;
            }
        }
        // TODO hashCode와 Arrays.deepHashCode 비교
        System.out.println("hashCode");
        System.out.println("arr : " + arr.hashCode());
        System.out.println("clone_arr : " + clone_arr.hashCode());
        System.out.println(arr.equals(clone_arr));
        System.out.println("Arrays.deepHashCode");
        System.out.println("arr : " + Arrays.deepHashCode(arr));
        System.out.println("clone_arr : " + Arrays.deepHashCode(clone_arr));
        System.out.println(Arrays.deepEquals(arr, clone_arr));
    }
}

// https://inpa.tistory.com/entry/JAVA-%E2%98%95-Object-%ED%81%B4%EB%9E%98%EC%8A%A4-clone-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%96%95%EC%9D%80-%EB%B3%B5%EC%82%AC-%EA%B9%8A%EC%9D%80-%EB%B3%B5%EC%82%AC