package br.com.otavio.Fipe_API.principal;

import br.com.otavio.Fipe_API.model.Dados;
import br.com.otavio.Fipe_API.model.Modelos;
import br.com.otavio.Fipe_API.model.Veiculo;
import br.com.otavio.Fipe_API.service.ConsumoApi;
import br.com.otavio.Fipe_API.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo =  new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";
    //Endereço base da API
    public void exibeMenu(){
        var menu = """ 
            ----OPÇÕES----
            Moto
            Carro
            Caminhão
            
            Digite qual a categoria deseja consultar:
            """;
        System.out.println(menu);
        var categoria = leitura.nextLine();
        String url;

        //exibe as marcas referentes a categoria desejada
        if (categoria.toLowerCase().contains("carr")) {
            url = URL_BASE + "carros/marcas";
        }else if (categoria.toLowerCase().contains("mot")) {
            url = URL_BASE + "motos/marcas";
        } else {
            url = URL_BASE + "caminhoes/marcas";
        }

        //Gera um JSON com as marcas da categoria
        var json = consumo.obterDados(url);        

        var marcas = conversor.obterLista(json, Dados.class);
        //Stream para ordenar as marcas e imprimir essas
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        //Pega o código da marca escolhida
        System.out.println("Digite o código da marca desejada:");
        var codigoMarca = leitura.nextLine();
        //URL para pegar os modelos da marca escolhida
        url = url + "/" + codigoMarca + "/modelos";
        //Gera um JSON com os modelos da marca escolhida(porem tem q ser formatado)
        json = consumo.obterDados(url);
        //Converte o JSON em apenas Modelos como esta no Record Modelos
        var modeloLista = conversor.obterDados(json, Modelos.class);
       
        //Imprime os modelos disponíveis
        System.out.println("\nModelos disponíveis:");
        modeloLista.modelos().stream()
                    .sorted(Comparator.comparing(Dados::codigo))
                    .forEach(System.out::println);

        //Recebe o nome do modelo escolhido
        System.out.println("\n Digite o nome (ou trecho) do modelo desejado:");
        var nomeModelo = leitura.nextLine();
        
        //Filtrar modelos pelo
        List<Dados> modelosFiltrados = modeloLista.modelos().stream()
                .filter(modelo -> modelo.nome().toLowerCase().contains(nomeModelo.toLowerCase()))
                .collect(Collectors.toList());
        
        System.out.println("\nModelos filtrados:");
        modelosFiltrados.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);

        //Recebe codigo do modelo escolhido
        System.out.println("\nDigite o código do modelo desejado:");
        var codigoModelo = leitura.nextLine();

         //URL para pegar os anos do modelo escolhido
         url = url + "/" + codigoModelo + "/anos";

        //Gera um JSON com os anos do modelo escolhido
        json = consumo.obterDados(url);
        //Converte o JSON em uma lista de anos e o odigo referente
        List<Dados> anos = conversor.obterLista(json, Dados.class);

        //Imprime todos do modelo diposniveis e sua informacoes
        System.out.println("\nVeiculos disponíveis:");
        for (Dados ano : anos) {
            var newurl = url + "/" + ano.codigo();
            json = consumo.obterDados(newurl);
            //Converte o JSON em um veiculo como esta no Record Veiculo
            var veiculo = conversor.obterVeiculo(json, Veiculo.class);
            System.out.println(veiculo);
        }

        
    
        
    }

}
