package br.edu.brazcubas.restaurante.model.dao;

import java.sql.SQLException;
import java.util.*;

public interface IDAO<T> {
    public void registrar(T item) throws SQLException;

    public void atualizar(T item) throws SQLException;

    public void excluir(int id);

    public List<T> retornarTodos();

    public T retornarPorId(int id);
}