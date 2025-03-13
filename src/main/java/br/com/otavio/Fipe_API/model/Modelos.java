package br.com.otavio.Fipe_API.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) //Para pegar apenas oq deseja (modelos)
public record Modelos(List<Dados> modelos) {
    
}
