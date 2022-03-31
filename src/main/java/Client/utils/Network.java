package Client.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Locale;

public class Network {
    public static String getPublicIp() {
        String ip = "";
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            ip = in.readLine().trim();
        } catch (IOException e) {
            ip = "Error";
        }
        return ip;
    }

    public static String getLocalIp() {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (Exception ignored) {
            ip = "Error";
        }
        return ip;
    }

    public static String getCountry() {
        String country = "";
        Locale locale = Locale.getDefault();
        country = locale.getCountry();
        return country;
    }

    public static String getHostname() {
        String hostname = "";
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (Exception ignored) {
            hostname = "Error";
        }
        return hostname;
    }

    // get the city name from the IP address
    public static String getCity(String ip) {
        String city = "";
        try {
            URL url = new URL("ip-api.com/json/" + ip);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            city = in.readLine().trim();
        } catch (IOException e) {
            city = "Error";
        }
        return city;
    }

    // get the MAC address
    public static String getMAC() {
        String mac = "";
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] macAddress = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < macAddress.length; i++) {
                sb.append(String.format("%02X%s", macAddress[i], (i < macAddress.length - 1) ? "-" : ""));
            }
            mac = sb.toString();
        } catch (Exception ignored) {
            mac = "Error";
        }
        return mac.replaceAll("-", ":");
    }
}

