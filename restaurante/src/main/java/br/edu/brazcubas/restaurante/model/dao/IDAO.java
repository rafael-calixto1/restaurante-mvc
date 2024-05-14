package br.edu.brazcubas.restaurante.model.dao;

import java.util.*;

public interface IDAO<T> {
    public void registrar(T item);

    public void atualizar(T item);

    public void excluir(int id);

    public List<T> retornarTodos();

    public T retornarPorId(int id);
}