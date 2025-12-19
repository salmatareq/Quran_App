package src;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started on port 5000...");

            while (true) {
                Socket client = serverSocket.accept();
                pool.submit(() -> handleClient(client));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void handleClient(Socket socket) {
        try (
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream())
        ) {
            String request = in.readUTF();
            System.out.println("Client says: " + request);

            if (request.startsWith("SEARCH")) {
                String query = request.substring(7);
                if (query.matches("\\d+")) {
                    int pageNumber = Integer.parseInt(query);
                    sendPage(pageNumber, out);
                } else {

                    out.writeInt(-1);
                }

            } else if (request.startsWith("GET_SURA")) {
                int surahNumber = Integer.parseInt(request.split(" ")[1]);
                sendSurahByNumber(surahNumber, out);
            } else if (request.startsWith("check_user")) {
                String name_pass = request.substring(11).trim();
                checkUser(name_pass, out);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void checkUser(String user_pass, DataOutputStream out) throws IOException {
        File file = new File("Server/users/user list.txt");
        boolean exists = false;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (user_pass.equals(line)) {
                    exists = true;
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (exists) {
            out.writeUTF("exist");
        } else {
            out.writeUTF("not_exist");
        }
    }

    static void sendPage(int pageNumber, DataOutputStream out) throws IOException {
        boolean found = false;

        for (int i = 1; i <= 114; i++) {
            File folder = new File("Server/quran/" + i);
            if (!folder.exists()) continue;

            File[] pages = folder.listFiles();
            if (pages == null || pages.length == 0) continue;


            Arrays.sort(pages, (a, b) -> {
                int n1 = extractNumber(a.getName());
                int n2 = extractNumber(b.getName());
                return Integer.compare(n1, n2);
            });

            int start = -1;
            for (int j = 0; j < pages.length; j++) {
                int currentPage = extractNumber(pages[j].getName());

                if (currentPage >= pageNumber) {
                    if (start == -1) out.writeInt(pages.length - j);
                    start = 1;

                    byte[] bytes = Files.readAllBytes(pages[j].toPath());
                    out.writeInt(bytes.length);
                    out.write(bytes);


                    found = true;

                }
            }

            if (found) {
                System.out.println(
                        "Sent pages from page " + pageNumber + " to end of Surah " + i
                );
                break;
            }
        }

        if (!found) {
            out.writeInt(-1);
        }
    }


    static void sendSurahByNumber(int number, DataOutputStream out) throws IOException {
        File folder = new File("Server/quran/" + number);
        if (!folder.exists()) {
            System.out.println("not fonud");
            out.writeInt(-1);
        } else {
            sendImages(folder.listFiles(), out);
            System.out.println("Sent Surah " + number);
        }
    }

    static void sendImages(File[] files, DataOutputStream out) throws IOException {
        Arrays.sort(files, (a, b) -> {
            int n1 = extractNumber(a.getName());
            int n2 = extractNumber(b.getName());
            return Integer.compare(n1, n2);
        });

        out.writeInt(files.length);
        for (File f : files) {
            byte[] bytes = Files.readAllBytes(f.toPath());
            out.writeInt(bytes.length);
            out.write(bytes);
        }
        out.flush();
    }

    static int extractNumber(String filename) {
        try {
            return Integer.parseInt(filename.replace(".jpg", "").trim());
        } catch (Exception e) {
            return 0;
        }
    }

}