package com.company;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Relations {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        MyHashMap relations = new MyHashMap();
        String line;
        while (true) {
            line = reader.nextLine();
            if (line.equals("0")) {
                break;
            } else {
                String str = validateRelation(line);
                if (str != null) {
                    relations.put(str, null);
                } else System.out.println("Wrong Input");
            }
        }
        printResult(relations);
    }

    public static String validateRelation(String str) {
        str = str.trim();
        if (str.matches("[A-Za-z0-9]*[,][A-Za-z0-9]*")) {
            return str;
        } else return null;
    }

    public static void printResult(MyHashMap relations) {
        String result = "";
        if (reflexive(relations)) result = result.concat("Relation is Reflexive" + "\n");
        if (symmetric(relations)) result = result.concat("Relation is Symmetric" + "\n");
        if (antiSymmetric(relations)) result = result.concat("Relation is Anti-Symmetric" + "\n");
        if (transitive(relations)) result = result.concat("Relation is Transitive" + "\n");
        System.out.println(result);
    }

    public static boolean reflexive(MyHashMap relations) {
        for (String key : relations.keySet()) {
            for (int i = 0; i < relations.get(key).size(); i++) {
                if (relations.get(key).get(i).equals(key)) {
                    break;
                }
                if (i == relations.get(key).size() - 1) return false;
            }
        }
        return true;
    }

    public static boolean symmetric(MyHashMap relations) {
        for (String key : relations.keySet()) {
            List<String> list = relations.get(key);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(key)) continue;
                // (z,a),(no a,unknown)
                if (relations.get(list.get(i)) == null) {
                    return false;
                    // (z,a),(a,unknown)
                } else {
                    List<String> list1 = relations.get(list.get(i));
                    for (int j = 0; j < list1.size(); j++) {
                        if (list1.get(j).equals(key)) break;
                        if (j == list1.size() - 1) return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean antiSymmetric(MyHashMap relations) {
        for (String key : relations.keySet()) {
            List<String> list = relations.get(key);
            for (int i = 0; i < list.size(); i++) {
                // (z,z)
                if (list.get(i).equals(key)) continue;
                // (z,a),(a,unknown)
                if (relations.get(list.get(i)) != null) {
                    List<String> list1 = relations.get(list.get(i));
                    for (int j = 0; j < list1.size(); j++) {
                        if (list1.get(j).equals(key)) return false;
                        if (j == list1.size() - 1) break;
                    }
                }
            }
        }
        return true;
    }

    public static boolean transitive(MyHashMap relations) {
        for (String key : relations.keySet()) {
            List<String> list = relations.get(key);
            for (int i = 0; i < list.size(); i++) {
                // (z,a),(a,unknown)
                if (relations.get(list.get(i)) != null) {
                    // second = unknown
                    for (String key1 : relations.keySet()) {
                        if (key1.equals(key)){
                            List<String> list2 = relations.get(key1);
                            for (int j = 0; j < list2.size(); j++) {
                                String second = list2.get(j);
                                if (list2.get(j).equals(second)){break;
                                }else if(j == list2.size()-1)return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
