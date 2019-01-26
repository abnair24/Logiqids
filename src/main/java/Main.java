import api.Download;
import api.History;
import api.Login;
import response.HistoryResponse;
import response.LoginResponse;
import response.Worksheet_history;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {

        String path = getPath();

        LoginResponse loginResponse = new Login().login();
        String session_token = loginResponse.getSession_token();
        int user_id = loginResponse.getUser_id();

        HistoryResponse historyResponse = new History().getHistory(session_token,user_id,1);
        int totalPages = historyResponse.getTotal_pages();

        for(int i = 1;i<=totalPages;i++) {

            historyResponse = new History().getHistory(session_token,user_id,i);

            List<Worksheet_history> worksheet_history = historyResponse.getWorksheet_history();

            Consumer<Worksheet_history> getAndDownloadPdf = worksheetHistory -> {

                byte[] eachPdf = getEachPdf(session_token, user_id, worksheetHistory.getId(), worksheetHistory.getTopic().getId());

                downloadLocally(String.valueOf(worksheetHistory.getId()), eachPdf,path);
            };

            worksheet_history.parallelStream().forEach(getAndDownloadPdf);
        }
    }

    private static byte[] getEachPdf(String session_token, int user_id,int id, int topicId) {
        return new Download().downloadPDF(session_token, id,
                topicId, user_id);
    }

    private static void downloadLocally(String fileName, byte[] eachPdf,String path) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(path+ "/" + fileName+".pdf");
            fos.write(eachPdf);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPath() {
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStamp = localDateTime.toString()
                .replace(":","-")
                .replace(".","-");

        String path = "";

        try {
            path  = Files
                    .createDirectory(Paths
                            .get(System.getProperty("user.home")+"/Desktop/Logiqids/"+timeStamp)).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}
