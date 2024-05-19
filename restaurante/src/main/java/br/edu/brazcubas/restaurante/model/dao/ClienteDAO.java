package br.edu.brazcubas.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.Cliente;

public class ClienteDAO implements IDAO<Cliente> {
    @Override
    public void registrar(Cliente cliente) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO cliente VALUES (default, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(Cliente cliente) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE cliente SET nome = ?, cpf = ? WHERE id_cliente = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setInt(3, cliente.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(int id) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM cliente WHERE id_cliente = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Cliente> retornarTodos() {
        List<Cliente> minhaList = new ArrayList<Cliente>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM cliente;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                Cliente cliente = new Cliente(id, nome, cpf);

                minhaList.add(cliente);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public Cliente retornarPorId(int id) {
        Cliente cliente = new Cliente(id, "", "");
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM cliente WHERE id_cliente = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                cliente.setNome(nome);
                cliente.setCpf(cpf);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return cliente;
    }

}