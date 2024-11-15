package java_on.utils;

import java.util.List;

public class Printer {

    public static void print(String text) {
        System.out.println(Colors.ANSI_BLUE + text + Colors.ANSI_RESET);
    }

    public static void debugPrint(String text) {
        System.out.println(Colors.ANSI_CYAN + "DEBUG:" + text + Colors.ANSI_RESET);
    }

    public static void printSeparator() {
        System.out.println(Colors.ANSI_CYAN + "===============================================\n\n" + Colors.ANSI_RESET);
    }

    public static void debugPrint(List<String> texts) {
        texts.forEach(text -> System.out.println(Colors.ANSI_CYAN + "DEBUG:" + text + Colors.ANSI_RESET));
    }

    public static void debugPrint(int text) {
        System.out.println(Colors.ANSI_CYAN + "DEBUG:" + text + Colors.ANSI_RESET);
    }

}
