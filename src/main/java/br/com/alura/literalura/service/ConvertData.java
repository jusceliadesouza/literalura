package br.com.alura.literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Mapear a resposta JSON para objetos Java utilizando o Jackson
public class ConvertData implements IConvertData {
  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T getData(String json, Class<T> classe) {
    try {
      return mapper.readValue(json, classe);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
