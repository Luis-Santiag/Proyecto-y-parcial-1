package producer;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApiService {

    public static String obtenerTransacciones() throws Exception {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://hly784ig9d.execute-api.us-east-1.amazonaws.com/default/transacciones")
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}