import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("Введите строку для вычисления: ");
        String expression = scan.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        if (expression.contains(" ")) {
            expression = expression.replaceAll("\\s", "");
        }
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Операнда должно быть два");
        oper = defineOperation(expression);
        if (oper == null) throw new Exception("Операция не поддерживается");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        else throw new Exception("Чимла должны быть одного формата");
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            if (arabian <= 0) {
                throw new Exception("Римское число должно быть больше 0");
            }
            result = Roman.convertToRoman(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String defineOperation(String expression) {
        if (expression.contains("+")) return "+";
        if (expression.contains("-")) return "-";
        if (expression.contains("*")) return "*";
        if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String operand) {
        if (operand.equals("+")) return a + b;
        else if (operand.equals("-")) return a - b;
        else if (operand.equals("*")) return a * b;
        else if (operand.equals("/")) return a / b;
        else return 0;
    }
}

class Roman {
    static String[] romanArray = new String[] {"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV", "XXV",
            "XXVI", "XXVII", "XXVIII", "XXIX", "XXX"};
    public static boolean isRoman(String val) {
        for (String s : romanArray) {
            if (val.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}