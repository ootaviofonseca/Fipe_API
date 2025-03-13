package br.com.otavio.Fipe_API.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    private ObjectMapper mapper = new ObjectMapper(); 

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType lista = mapper.getTypeFactory() //Serve para criar tipos de coleções
                .constructCollectionType(List.class, classe);//Gera lista da Classe desejada
        
        try {
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    //conveter para o veiculo final como no modelo
    @Override
    public <T> T obterVeiculo(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}