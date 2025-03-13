package br.com.otavio.Fipe_API.service;

import java.util.List;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);

    <T> T obterVeiculo(String json, Class<T> classe);
}
