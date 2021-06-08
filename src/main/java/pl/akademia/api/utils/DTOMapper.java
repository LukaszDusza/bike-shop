package pl.akademia.api.utils;

public interface DTOMapper<F,T>{
  T from(F from);
}