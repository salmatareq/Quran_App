package com.example.quran;

import javafx.scene.image.Image;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClient {

    public static ArrayList<Image> requestSurah(int surahNumber) {
        return requestFromServer("GET_SURA " + surahNumber);
    }

    public static ArrayList<Image> requestPage(int pageNumber) {

        return requestFromServer("SEARCH " + pageNumber);
    }

    public static boolean check(String s) {
        String request="check_user "+s;
        try {
            return requestUserServer(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean requestUserServer(String command) throws IOException {
        try (Socket socket = new Socket("localhost", 5000);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {
            out.writeUTF(command);
            String answer=in.readUTF();
            if(answer.equals("exist")){
                return true;
            }
            else return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    private static ArrayList<Image> requestFromServer(String command) {
        ArrayList<Image> images = new ArrayList<>();
        try (Socket socket = new Socket("localhost", 5000);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            out.writeUTF(command);
            int count = in.readInt();
            if (count == -1) return images;

            for (int i = 0; i < count; i++) {
                int len = in.readInt();
                byte[] data = new byte[len];
                in.readFully(data);
                images.add(new Image(new ByteArrayInputStream(data)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return images;
    }


}
