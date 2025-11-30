package com.opensource.module.huawei.od;

import java.util.*;

public class ExpressionEvaluation {

    // 分数类
    static class Fraction {
        long num;  // 分子
        long den;  // 分母 (始终 > 0)

        Fraction(long n, long d) {
            if (d == 0) throw new ArithmeticException("DIV0");
            if (d < 0) { n = -n; d = -d; }
            long g = gcd(Math.abs(n), d);
            this.num = n / g;
            this.den = d / g;
        }

        // 加法
        Fraction add(Fraction b) {
            return new Fraction(this.num * b.den + b.num * this.den,
                    this.den * b.den);
        }
        // 减法
        Fraction sub(Fraction b) {
            return new Fraction(this.num * b.den - b.num * this.den,
                    this.den * b.den);
        }
        // 乘法
        Fraction mul(Fraction b) {
            return new Fraction(this.num * b.num, this.den * b.den);
        }
        // 除法
        Fraction div(Fraction b) {
            if (b.num == 0) throw new ArithmeticException("DIV0");
            return new Fraction(this.num * b.den, this.den * b.num);
        }

        private long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        public String toString() {
            if (den == 1) return String.valueOf(num);
            return num + "/" + den;
        }
    }

    // -----------------------------
    // 中缀表达式 转 后缀 (Shunting Yard)
    // -----------------------------
    static List<String> toRPN(String expr) {
        List<String> output = new ArrayList<>();
        Stack<Character> ops = new Stack<>();

        int n = expr.length();
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = expr.charAt(i);
            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                if (number.length() > 0) {
                    output.add(number.toString());
                    number.setLength(0);
                }
                if (c == ' ') continue;

                if (c == '(') ops.push(c);
                else if (c == ')') {
                    while (!ops.isEmpty() && ops.peek() != '(')
                        output.add(String.valueOf(ops.pop()));
                    ops.pop(); // pop '('
                } else if (isOp(c)) {
                    while (!ops.isEmpty() && priority(ops.peek()) >= priority(c))
                        output.add(String.valueOf(ops.pop()));
                    ops.push(c);
                }
            }
        }

        if (number.length() > 0) output.add(number.toString());

        while (!ops.isEmpty()) output.add(String.valueOf(ops.pop()));

        return output;
    }

    static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    static int priority(char c) {
        if (c == '+' || c == '-') return 1;
        if (c == '*' || c == '/') return 2;
        return 0;
    }

    // -----------------------------
    // 计算后缀表达式
    // -----------------------------
    static Fraction evalRPN(List<String> rpn) {
        Stack<Fraction> st = new Stack<>();
        try {
            for (String token : rpn) {
                if (token.length() > 1 || Character.isDigit(token.charAt(0))) {
                    st.push(new Fraction(Long.parseLong(token), 1));
                } else {
                    Fraction b = st.pop();
                    Fraction a = st.pop();
                    switch (token) {
                        case "+":
                            st.push(a.add(b)); break;
                        case "-":
                            st.push(a.sub(b)); break;
                        case "*":
                            st.push(a.mul(b)); break;
                        case "/":
                            st.push(a.div(b)); break;
                    }
                }
            }
        } catch (ArithmeticException e) {
            if ("DIV0".equals(e.getMessage())) throw e;
        }
        return st.pop();
    }

    // -----------------------------
    // 主方法
    // -----------------------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expr = sc.nextLine().trim();

        try {
            List<String> rpn = toRPN(expr);
            Fraction ans = evalRPN(rpn);
            System.out.println(ans);
        } catch (ArithmeticException e) {
            System.out.println("ERROR");
        }
    }
}
