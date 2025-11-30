package com.opensource.module.huawei.od;

import java.util.*;
import java.util.regex.*;

public class SerializationCodec {

    // 类型映射
    private static final Map<String, String> encodeTypeMap = new HashMap<>();
    private static final Map<String, String> decodeTypeMap = new HashMap<>();
    static {
        encodeTypeMap.put("Integer", "0");
        encodeTypeMap.put("String", "1");
        encodeTypeMap.put("Compose", "2");

        decodeTypeMap.put("0", "Integer");
        decodeTypeMap.put("1", "String");
        decodeTypeMap.put("2", "Compose");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine().trim();
        String dataLine = sc.nextLine().trim();

        try {
            if ("1".equals(command)) {
                System.out.println(encodeLine(dataLine));
            } else if ("2".equals(command)) {
                System.out.println(decodeLine(dataLine));
            } else {
                System.out.println("UNKNOWN_COMMAND");
            }
        } catch (Exception e) {
            System.out.println("ENCODE_ERROR".equals(e.getMessage()) || "DECODE_ERROR".equals(e.getMessage()) ? e.getMessage() : "ERROR");
        }
    }

    // ==================== 编码 ====================
    private static String encodeLine(String line) throws Exception {
        List<String> items = splitTopLevel(line);
        StringBuilder sb = new StringBuilder();
        for (String item : items) {
            try {
                String enc = encodeItem(item);
                if (!enc.isEmpty()) sb.append(enc);
            } catch (Exception e) {
                // 单条数据异常，跳过
            }
        }
        if (sb.length() == 0) throw new Exception("ENCODE_ERROR");
        return sb.toString();
    }

    private static String encodeItem(String item) throws Exception {
        item = item.trim();
        if (!item.startsWith("[") || !item.endsWith("]")) throw new Exception();
        item = item.substring(1, item.length() - 1).trim();

        List<String> parts = splitTopLevel(item, ',');
        if (parts.size() != 3) throw new Exception();

        String pos = parts.get(0).trim();
        String type = parts.get(1).trim();
        String value = parts.get(2).trim();

        if (!encodeTypeMap.containsKey(type)) return ""; // 不支持类型跳过

        String typeCode = encodeTypeMap.get(type);
        String data;
        if ("Compose".equals(type)) {
            data = encodeLine(value);
        } else {
            data = value;
        }
        int length = data.length();
        return pos + "#" + typeCode + "#" + length + "#" + data;
    }

    // ==================== 解码 ====================
    private static String decodeLine(String line) throws Exception {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        boolean hasValid = false;

        while (idx < line.length()) {
            try {
                int posEnd = line.indexOf('#', idx);
                if (posEnd == -1) throw new Exception();
                String pos = line.substring(idx, posEnd);

                int typeEnd = line.indexOf('#', posEnd + 1);
                if (typeEnd == -1) throw new Exception();
                String typeCode = line.substring(posEnd + 1, typeEnd);
                String type = decodeTypeMap.get(typeCode);
                if (type == null) throw new Exception();

                int lenEnd = line.indexOf('#', typeEnd + 1);
                if (lenEnd == -1) throw new Exception();
                int len = Integer.parseInt(line.substring(typeEnd + 1, lenEnd));

                if (lenEnd + 1 + len > line.length()) throw new Exception();
                String data = line.substring(lenEnd + 1, lenEnd + 1 + len);

                String decoded;
                if ("Compose".equals(type)) {
                    decoded = "[" + decodeLine(data) + "]";
                } else {
                    decoded = data;
                }

                if (hasValid) sb.append(",");
                sb.append("[").append(pos).append(",").append(type).append(",").append(decoded).append("]");
                hasValid = true;

                idx = lenEnd + 1 + len;

            } catch (Exception e) {
                // 单条异常，跳过剩余字符
                break;
            }
        }

        if (!hasValid) throw new Exception("DECODE_ERROR");
        return sb.toString();
    }

    // ==================== 工具方法 ====================
    private static List<String> splitTopLevel(String line) throws Exception {
        return splitTopLevel(line, ',');
    }

    private static List<String> splitTopLevel(String line, char delimiter) throws Exception {
        List<String> res = new ArrayList<>();
        int bracket = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '[') bracket++;
            if (c == ']') bracket--;
            if (bracket < 0) throw new Exception("ENCODE_ERROR");

            if (c == delimiter && bracket == 0) {
                res.add(sb.toString().trim());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }
        if (bracket != 0) throw new Exception("ENCODE_ERROR");
        if (sb.length() > 0) res.add(sb.toString().trim());
        return res;
    }
}