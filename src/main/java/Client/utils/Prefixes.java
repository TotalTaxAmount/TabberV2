package Client.utils;

import Client.utils.ConsoleColor;

public interface Prefixes {
    String SUCCESS = ConsoleColor.ANSI_GREEN + "[✓] " + ConsoleColor.ANSI_RESET;
    String ERROR = ConsoleColor.ANSI_RED + "[X] " + ConsoleColor.ANSI_RESET;
    String WARN = ConsoleColor.ANSI_YELLOW + "[W] " + ConsoleColor.ANSI_RESET;
    String INFO = ConsoleColor.ANSI_CYAN + "[I] " + ConsoleColor.ANSI_RESET;
}