package com.opensource.module.huawei.od;

import java.util.*;
import java.util.regex.*;

public class SerializationCodec {
    // 类型长度映射
    static Map<String, Integer> typeLength = new HashMap<>() {{
        put("0", 7);
        put("1", 6);
        put("2", 7);
    }};
    // 类型字符串映射
    static Map<String, String> typeToStringMp = new HashMap<>() {{
        put("0", "Integer");
        put("1", "String");
        put("2", "Compose");
    }};
    // 标志是否出现格式错误
    static boolean flag = false;

    static class Item {
        // 位置
        String pos;
        // 长度
        int count;
        // 类型 数字形式
        String type;
        // 值类型的值
        String value;
        // 复合类型的子数据
        List<Item> children;

        Item(String pos, String type, int count, String value) {
            this.pos = pos;
            this.type = type;
            this.count = count;
            this.value = value;
            this.children = new ArrayList<>();
        }
    }

    // 最顶层按照括号进行切割 
    static List<String> splitTopLevelItem(String input) {
        List<String> res = new ArrayList<>();
        int depth = 0;
        int start = 0;
        int n = input.length();
        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (c == '[') {
                if (depth == 0) {
                    start = i;
                }
                depth++;
            } else if (c == ']') {
                depth--;
                if (depth == 0) {
                    String tmp = input.substring(start, i + 1);
                    res.add(tmp);
                }
            }
            // 出现括号乱序问题 右括号大于左括号数量
            if (depth < 0) {
                flag = true;
                break;
            }
        }

        // 括号左右对数不匹配
        if (depth != 0) {
            flag = true;
        }
        return res;
    }

    // 编码
    static List<Item> encode(String input) {
        List<Item> res = new ArrayList<>();
        if (input.isEmpty()) {
            return res;
        }

        // 解析输入是否已[ 开头 以 ] 结尾
        if (input.charAt(0) != '[' || input.charAt(input.length() - 1) != ']') {
            flag = true;
            return res;
        }

        List<String> ans = splitTopLevelItem(input);
        // 处理字符串过程中括号对数不匹配,出现异常情况,结束
        if (flag) {
            return res;
        }
        int n = ans.size();

        for (int i = 0; i < n; i++) {
            String itemStr = ans.get(i);
            // 取出左右边界括号
            itemStr = itemStr.substring(1, itemStr.length() - 1);

            List<String> currentAns = new ArrayList<>();

            StringBuilder tmp = new StringBuilder();
            int m = itemStr.length();
            int j = 0;

            for (; j < m; j++) {
                char c = itemStr.charAt(j);
                if (c != ',') {
                    tmp.append(c);
                } else {
                    currentAns.add(tmp.toString());
                    tmp.setLength(0);
                    if (currentAns.size() == 2) {
                        break;
                    }
                }
            }
            // 根据类型进行不同处理
            String type = currentAns.get(currentAns.size() - 1);
            // 值类型
            if (type.equals("Integer") || type.equals("String")) {
                String value = itemStr.substring(j + 1);
                int count = value.length();
                String typeStr = currentAns.get(1).equals("Integer") ? "0" : "1";
                res.add(new Item(currentAns.get(0), typeStr, count, value));
                // 复合类型    
            } else if (type.equals("Compose")) {
                // 下一层嵌套的内容
                String childrenItemStr = itemStr.substring(j + 1);
                // 递归处理 获得子元素
                List<Item> children = encode(childrenItemStr);
                // 计算长度
                int m2 = children.size();
                // 计算长度 嵌套类型的总长度 = 各子片段的长度 + 各子长度类型字符串的长度
                int count = 0;
                String typeStr = "2";
                for (int k = 0; k < m2; k++) {
                    Item item = children.get(k);
                    count += item.count;
                    count += typeLength.get(item.type);
                }

                // 添加一个复合类型
                Item composeItem = new Item(currentAns.get(0), typeStr, count, "");
                composeItem.children = children;
                res.add(composeItem);
            }
        }
        return res;
    }

    // 递归格式化输出编码结果
    static void formatEncodePrint(List<Item> items) {
        for (Item item : items) {
            if (item.type.equals("0") || item.type.equals("1")) {
                System.out.print(item.pos + "#" + item.type + "#" + item.count + "#" + item.value);
            } else {
                System.out.print(item.pos + "#" + item.type + "#" + item.count + "#");
                formatEncodePrint(item.children);
            }
        }
    }

    // 递归格式化输出解码结果
    static void formatDecodePrint(List<Item> items) {
        int n = items.size();
        for (int i = 0; i < n; i++) {
            Item item = items.get(i);
            if (item.type.equals("0") || item.type.equals("1")) {
                System.out.print("[" + item.pos + "," + typeToStringMp.get(item.type) + "," + item.value + "]");
            } else {
                System.out.print("[" + item.pos + "," + typeToStringMp.get(item.type) + ",");
                formatDecodePrint(item.children);
                System.out.print("]");
            }
            if (i != n - 1) {
                System.out.print(",");
            }
        }
    }

    // 通用 split 函数
    static List<String> split(String str, String delimiter) {
        List<String> result = new ArrayList<>();
        int start = 0;
        int end = str.indexOf(delimiter);
        while (end != -1) {
            result.add(str.substring(start, end));
            start = end + delimiter.length();
            end = str.indexOf(delimiter, start);
        }
        // 添加最后一个部分
        result.add(str.substring(start));
        return result;
    }

    // 关键：获取子复合类型的字符串内容
    static String getChildrenInput(List<String> parts, int[] index, int count) {
        String pos = "";
        String type = "";
        String value = "";
        int subCount = -1;

        StringBuilder res = new StringBuilder();

        int n = parts.size();
        int tmpCount = 0;

        while (index[0] < n && tmpCount < count) {
            if (pos.equals("")) {
                pos = parts.get(index[0]);
            } else if (type.equals("")) {
                type = parts.get(index[0]);
            } else if (subCount == -1) {
                subCount = Integer.parseInt(parts.get(index[0]));
            } else {
                // 累计长度：类型长度 + 数据长度
                tmpCount += typeLength.get(type) + subCount;

                if (type.equals("0") || type.equals("1")) {
                    value = parts.get(index[0]);
                    if (value.length() < subCount) {
                        // 数据不足，格式错误
                        flag = true;
                        break;
                    }
                    String valuePart = value.substring(0, subCount);
                    String remainngStr = value.substring(subCount);
                    parts.set(index[0], remainngStr);

                    res.append(pos).append("#").append(type).append("#").append(subCount).append("#").append(valuePart);
                    // 因为下一条数据被拼接到当前字符串中，所以退回一步，让decode处理
                    index[0]--;
                } else {
                    // Compose 类型递归处理
                    res.append(pos).append("#").append(type).append("#").append(subCount).append("#");

                    res.append(getChildrenInput(parts, index, subCount));
                    // 递归结束后回退一位，
                    index[0]--;
                }
                // 重置
                pos = "";
                type = "";
                value = "";
                subCount = -1;
            }
            index[0]++;
        }

        // 最后长度没对上则标记错误
        if (tmpCount != count) {
            flag = true;
        }
        return res.toString();
    }

    // 解码
    static List<Item> decode(String input) {
        List<Item> res = new ArrayList<>();
        List<String> parts = split(input, "#");
        int m = parts.size();
        String pos = "";
        String type = "";
        String value = "";
        int count = -1;
        for (int i = 0; i < m; i++) {
            if (pos.equals("")) {
                pos = parts.get(i);
            } else if (type.equals("")) {
                type = parts.get(i);
            } else if (count == -1) {
                count = Integer.parseInt(parts.get(i));
            } else {
                if (type.equals("1") || type.equals("0")) {
                    value = parts.get(i);
                    // 两种情况:
                    // 1. 最后一个数据,长度应该等于count
                    // 2. 不是最后一个数组,长度应该大于count , 由 内容 + 下一个数据的位置编号
                    if ((i != m - 1 && value.length() <= count) || (i == m - 1 && value.length() != count)) {
                        flag = true;
                        break;
                    }
                    String remainngStr = value.substring(count);
                    value = value.substring(0, count);
                    res.add(new Item(pos, type, count, value));
                    parts.set(i, remainngStr);
                    i--;
                } else {
                    int[] j = new int[]{i};
                    String childrenInput = getChildrenInput(parts, j, count);
                    if (flag) {
                        break;
                    }
                    res.add(new Item(pos, type, count, ""));
                    List<Item> childrenItem = decode(childrenInput);
                    res.get(res.size() - 1).children = childrenItem;
                    i = j[0] - 1;
                }
                pos = "";
                type = "";
                count = -1;
                value = "";
            }
        }
        if (!pos.equals("") || !type.equals("") || count != -1 || !value.equals("")) {
            flag = true;
        }
        return res;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();

        if (command == 1) {
            List<Item> items = encode(input);
            if (flag) System.out.println("ENCODE_ERROR");
            else formatEncodePrint(items);
        } else if (command == 2) {
            List<Item> items = decode(input);
            if (flag) System.out.println("DECODE_ERROR");
            else formatDecodePrint(items);
        }
    }
}