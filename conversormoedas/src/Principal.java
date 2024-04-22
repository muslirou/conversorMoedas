import com.google.gson.*;
import conversormoedas.calculadora.Calculadora;
import conversormoedas.modulos.Menu;
import conversormoedas.modulos.Moedas;
import conversormoedas.modulos.MoedasExchange;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int opcao = 0;
        List<Moedas> listaConversoes =new ArrayList<>();
        URI uri = URI.create("https://v6.exchangerate-api.com/v6/fe9ed8a9070f010ff96124f2/latest/USD");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        while (opcao != 7) {
            Calculadora calculadora = new Calculadora();
            Menu menu = new Menu();

            System.out.println(menu.getMenuPrincipal());
            opcao = leitura.nextInt();

            try {
                //Criando um novo cliente Http, contruindo uma requisição e criando o arquivo json
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.valueOf(uri)))
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                //Realizando o Parse, convertendo em Object e criando um novo Object com o filtro conversion_rates
                JsonParser parser = new JsonParser();
                JsonObject jsobj = parser.parse(json).getAsJsonObject();
                JsonObject novoJson = jsobj.getAsJsonObject("conversion_rates");

                //Realizando um novo filtro através da classe MoedasExchange e incluindo na classe principal
                MoedasExchange novaMoedasExchange = gson.fromJson(novoJson, MoedasExchange.class);
                Moedas novaMoeda = new Moedas(novaMoedasExchange);

                if (opcao == 1) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao1(valor, novaMoeda.getPesoArgentino());
                    System.out.println(String.format("Resultado da conversão: ARS$ %.2f", resultado));
                } else if (opcao == 2) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao2(valor, novaMoeda.getPesoArgentino());
                    System.out.println(String.format("Resultado da conversão: US$ %.2f", resultado));
                } else if (opcao == 3) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao1(valor, novaMoeda.getReal());
                    System.out.println(String.format("Resultado da conversão: R$ %.2f", resultado));
                } else if (opcao == 4) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao2(valor, novaMoeda.getReal());
                    System.out.println(String.format("Resultado da conversão: US$ %.2f", resultado));
                } else if (opcao == 5) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao1(valor, novaMoeda.getPesoColombiano());
                    System.out.println(String.format("Resultado da conversão: COP$ %.2f", resultado));
                } else if (opcao == 6) {
                    System.out.println("Digite o valor a ser convertido");
                    double valor = leitura.nextDouble();
                    double resultado = calculadora.conversao2(valor, novaMoeda.getPesoColombiano());
                    System.out.println(String.format("Resultado da conversão: US$ %.2f", resultado));
                } else if (opcao == 7) {
                    break;
                } else {
                    System.out.println("Opção invalida.");
                }

            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } catch (InputMismatchException e) {
                System.out.println("Formato inválido. Digite o valor utilizando ',' ");
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Operação finalizada");
    }
}