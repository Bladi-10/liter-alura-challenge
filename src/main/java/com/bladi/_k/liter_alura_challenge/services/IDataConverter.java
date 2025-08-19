package com.bladi._k.liter_alura_challenge.services;

public interface IDataConverter {
    <T> T getData(String json, Class<T> clazz);
}
