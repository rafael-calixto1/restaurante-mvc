package br.edu.brazcubas.restaurante.model.dao;

import java.util.*;
import java.sql.*;
import java.sql.Date;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;

public class FuncionarioDAO implements IDAO<Funcionario>{
    @Override
    public void registrar(Funcionario funcionario) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO funcionario VALUES (default, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getCpf());
            stmt.setDate(4, funcionario.getDataNasc());
            stmt.setString(5, funcionario.getCargo());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(Funcionario funcionario) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE funcionario SET nome = ?, senha = ?, cpf = ?, dt_nasc = ?, cargo = ? WHERE id_func = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, funcionario.getNome());
            stmt.setString(2, funcionario.getSenha());
            stmt.setString(3, funcionario.getCpf());
            stmt.setDate(4, funcionario.getDataNasc());
            stmt.setString(5, funcionario.getCargo());
            stmt.setInt(6, funcionario.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(Funcionario funcionario) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM funcionario WHERE cpf = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, funcionario.getCpf());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Funcionario> retornarTodos() {
        List<Funcionario> minhaList = new ArrayList<Funcionario>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM funcionario;";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_func");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                Date dtNasc = rs.getDate("dt_nasc");
                String cargo = rs.getString("cargo");

                Funcionario func = new Funcionario(id, nome, cpf, dtNasc, cargo, senha);

                minhaList.add(func);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public Funcionario retornar(Funcionario funcionario) {
        Funcionario func = new Funcionario(funcionario.getId(), "", "", null, "", "");
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM funcionario WHERE cpf = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, funcionario.getCpf());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                Date dtNasc = rs.getDate("dt_nasc");
                String cargo = rs.getString("cargo");
                int idFunc = rs.getInt("id_func");

                func.setId(idFunc);
                func.setNome(nome);
                func.setCargo(cargo);
                func.setCpf(cpf);
                func.setDataNac(dtNasc);
                func.setSenha(senha);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return func;
    }

    public boolean logar(String nome, String senha) throws SQLException{
        try {
            Connection conn = PostgresConfig.getConnection();
            String sql = "Select * FROM funcionario WHERE nome = ? AND senha = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nome);
            pstmt.setString(2, senha);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                if (result.getString("nome")!= null) {
                    return true;               
                }else{
                    return false;
                }
                
            }else{
                return false;
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return false;
        
    }

    public Funcionario retornarPorNomeSenha(String nomefunc, String senhafunc) {
        Funcionario func = new Funcionario("", "", null, "", "");
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT * FROM funcionario WHERE nome = ? AND senha = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nomefunc);
            stmt.setString(2, senhafunc);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_func");
                String nome = rs.getString("nome");
                String senha = rs.getString("senha");
                String cpf = rs.getString("cpf");
                Date dtNasc = rs.getDate("dt_nasc");
                String cargo = rs.getString("cargo");

                func.setId(id);
                func.setNome(nome);
                func.setCargo(cargo);
                func.setCpf(cpf);
                func.setDataNac(dtNasc);
                func.setSenha(senha);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return func;
    }
}