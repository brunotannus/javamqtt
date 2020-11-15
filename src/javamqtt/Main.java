/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javamqtt;
import java.util.Random;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ClienteMQTT clienteMQTT = new ClienteMQTT("tcp://broker.mqttdashboard.com:1883", null, null);
        clienteMQTT.iniciar();

        new Subscriber(clienteMQTT, "sensor/temperature/#", 0);

        while (true) {
            Thread.sleep(1000);
            Random rand = new Random();
            int n = rand.nextInt(30);
            n+=15;
            String mensagem = Integer.toString(n) + "°C";
            clienteMQTT.publicar("sensor/temperature/", mensagem.getBytes(), 0);
        }
    }
}