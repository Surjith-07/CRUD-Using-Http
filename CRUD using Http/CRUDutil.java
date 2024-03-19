import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CRUDutil {
    private final static String URL="https://gorest.co.in/public/v2/users";
    private final static String HEADER="Authorization";
    private final static String TOKEN="";
    private static final HttpClient httpClient =HttpClient.newHttpClient();;
    private static final Gson gson=new Gson();

    //GetAll------------------------------------------------------
    public static String getAllUser(){
        HttpRequest getAllRequest;
        try {
            getAllRequest=HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpResponse<String> getAllResponse;
        try {
            getAllResponse=httpClient.send(getAllRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<User> user=gson.fromJson(getAllResponse.body(),new TypeToken<List<User>>(){}.getType());
        return  gson.toJson(user);
    }


    //GetById----------------------------------------------------------
    public static  String getUserById(int id){
        HttpRequest getRequest= null;
        try {
            getRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL+"/"+id))
                    //                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                    //                .header(HEADER,TOKEN)
                    //                .header("accepts","application/json")
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getRequest.uri());
        HttpResponse<String> getResponse= null;
        try {
            getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(getResponse.statusCode());
        User user=gson.fromJson(getResponse.body(),User.class);
        return gson.toJson(user);
    }

    //Post------------------------------------------------------------------
    public static String createUser(User userToCreate){
        System.out.println(userToCreate.getName());
        String requestBody = gson.toJson(userToCreate);
        System.out.println(requestBody);

        HttpRequest postRequest= null;
        try {
            postRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL))
                    .header(HEADER,"Bearer "+TOKEN)
                    .header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        HttpResponse<String> postRespose= null;
        try {
            postRespose = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(postRequest.uri());
        System.out.println(postRespose.statusCode()+" "+postRespose.body());
        User user=gson.fromJson(postRespose.body(),User.class);
        return gson.toJson(user);
    }

    //Put or Patch------------------------------------------------------------
    public static String updateUser(int id,User userToUpdate) {
        System.out.println(userToUpdate.getName());
        String requestBody = gson.toJson(userToUpdate);
        System.out.println(requestBody);

        HttpRequest putRequest= null;
        try {
            putRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL+"/"+id))
                    .header(HEADER,"Bearer "+TOKEN)
                    .header("Content-Type","application/json")
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        System.out.println(putRequest.uri());

        HttpResponse<String> putRespose= null;
        try {
            putRespose = httpClient.send(putRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println(putRespose.statusCode()+" "+putRespose.body());
        User user=gson.fromJson(putRespose.body(),User.class);
        return gson.toJson(user);
    }

    //Delete----------------------------------------------------------------------
    public static String deletUser(int id) {
        HttpRequest deleteRequest= null;
        try {
            deleteRequest = HttpRequest.newBuilder()
                    .uri(new URI(URL.concat("/"+id)))
                    .header(HEADER,"Bearer "+TOKEN)
                    .header("Content-Type","application/json")
                    .DELETE()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        System.out.println(deleteRequest.uri());

        HttpResponse<String> deleteRespose= null;
        try {
            deleteRespose = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(deleteRespose.statusCode()+" "+deleteRespose.body());
        User user=gson.fromJson(deleteRespose.body(),User.class);
        return gson.toJson(user);
    }
}
