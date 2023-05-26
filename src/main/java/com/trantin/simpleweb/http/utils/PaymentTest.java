package com.trantin.simpleweb.http.utils;


import com.trantin.simpleweb.http.entity.Order;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.rmi.server.UID;
import java.util.Base64;

public class PaymentTest {

    public static String SendRequest(Order order){
        try {
            // Создание URL-объекта
            URL url = new URL("https://api.yookassa.ru/v3/payments");
            String redirectURL = "https://www.localhost:8080/main";

            String idempotenceKey = new UID().toString();

            // Создание HttpURLConnection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Установка метода запроса
            connection.setRequestMethod("POST");

            // Установка заголовков
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Basic MzE4ODY5OnRlc3RfUGo4d1RrMUQyZml4VE9BV2RsLWFpOTRKTEFjMHhYX0JxaTF1aGY3a1Nwaw==");
            connection.setRequestProperty("Idempotence-Key", idempotenceKey);

            // Создание тела запроса
            String requestBody = "{\"amount\": {\"value\": \"" + order.orderSum() + "\", \"currency\": \"RUB\"}, " +
                    "\"capture\": true, " +
                    "\"confirmation\": {\"type\": \"redirect\", \"return_url\": \"" + redirectURL + "\"}, " +
                    "\"description\": \"Заказ №" + order.getId() + "\"}";

            // Отправка тела запроса
            connection.setDoOutput(true);
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(requestBody.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            // Получение ответа
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Чтение ответа
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Вывод ответа
            System.out.println("Response: " + response.toString());

            // Закрытие соединения
            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
