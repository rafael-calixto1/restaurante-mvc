package br.edu.brazcubas.restaurante.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.sql.Date;

import br.edu.brazcubas.restaurante.database.PostgresConfig;
import br.edu.brazcubas.restaurante.model.entity.Cliente;
import br.edu.brazcubas.restaurante.model.entity.Funcionario;
import br.edu.brazcubas.restaurante.model.entity.ItemPedido;
import br.edu.brazcubas.restaurante.model.entity.Pedido;
import br.edu.brazcubas.restaurante.model.entity.Prato;

public class PedidoDAO implements IDAO<Pedido> {
    @Override
    public void registrar(Pedido pedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "INSERT INTO pedido VALUES (default, ?, ?, ?, ?, ?, ?);";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedido.getCliente().getId());
            stmt.setInt(2, pedido.getFuncionario().getId());
            stmt.setDate(3, pedido.getDataPedido());
            stmt.setDouble(4, pedido.getTotalPedido());
            stmt.setString(5, pedido.getFormaPagamento());
            stmt.setString(6, pedido.getStatus());
            int affected = stmt.executeUpdate();
            ResultSet keys = null;
            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                pedido.setId(keys.getInt(1));
                pedido.atualizarPedidoItens(pedido);

                for (ItemPedido itemPedido : pedido.getListaItens()) {
                    query = "INSERT INTO item_pedido VALUES (default, ?, ?, ?);";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, itemPedido.getPedido().getId());
                    stmt.setInt(2, itemPedido.getPrato().getId());
                    stmt.setInt(3, itemPedido.getQuantidade());
                    stmt.executeUpdate();
                }
            } else {
                throw new SQLException("Sem linhas afetadas. ID nao retornado.");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void atualizar(Pedido pedido) throws SQLException {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "UPDATE pedido SET id_func = ?, data_pedido = ?, pagamento = ?, status = ? WHERE id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getFuncionario().getId());
            stmt.setDate(2, pedido.getDataPedido());
            stmt.setString(3, pedido.getFormaPagamento());
            stmt.setString(4, pedido.getStatus());
            stmt.setInt(5, pedido.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void excluir(Pedido pedido) {
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "DELETE FROM pedido WHERE id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedido.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public List<Pedido> retornarTodos() {
        List<Pedido> minhaList = new ArrayList<Pedido>();
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT p.id_pedido AS id_pedido, p.data_pedido AS data_pedido, p.valor_total AS valor_total, p.pagamento AS pagamento, p.status AS status, "
                    + "c.id_cliente AS id_cliente, c.nome AS nome_cliente, "
                    + "f.id_func AS id_funcionario, f.nome AS nome_funcionario "
                    + "FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN funcionario f ON p.id_func = f.id_func";

            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                Date dataPedido = rs.getDate("data_pedido");
                Double valorTotal = rs.getDouble("valor_total");
                String pagamento = rs.getString("pagamento");
                String status = rs.getString("status");

                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome_cliente"));
                Funcionario funcionario = new Funcionario(rs.getInt("id_funcionario"),
                        rs.getString("nome_funcionario"));

                Pedido pedido = new Pedido(id, pagamento, dataPedido, valorTotal, status, cliente, funcionario);

                minhaList.add(pedido);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return minhaList;
    }

    @Override
    public Pedido retornar(Pedido pedidoLocal) {
        Pedido pedido = new Pedido(pedidoLocal.getId(), null, null, null, null, null, null);
        try {
            Connection conn = PostgresConfig.getConnection();
            String query = "SELECT p.id_pedido AS id_pedido, p.data_pedido AS data_pedido, p.valor_total AS valor_total, p.pagamento AS pagamento, p.status AS status, "
                    + "c.id_cliente AS id_cliente, c.nome AS nome_cliente, "
                    + "f.id_func AS id_funcionario, f.nome AS nome_funcionario "
                    + "FROM pedido p INNER JOIN cliente c ON p.id_cliente = c.id_cliente INNER JOIN funcionario f ON p.id_func = f.id_func WHERE p.id_pedido = ?;";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, pedidoLocal.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Date dataPedido = rs.getDate("data_pedido");
                Double valorTotal = rs.getDouble("valor_total");
                String pagamento = rs.getString("pagamento");
                String status = rs.getString("status");

                Cliente cliente = new Cliente(rs.getInt("id_cliente"), rs.getString("nome_cliente"));
                Funcionario funcionario = new Funcionario(rs.getInt("id_funcionario"),
                        rs.getString("nome_funcionario"));

                pedido.setDataPedido(dataPedido);
                pedido.setTotalPedido(valorTotal);
                pedido.setFormaPagamento(pagamento);
                pedido.setStatus(status);
                pedido.setCliente(cliente);
                pedido.setFuncionario(funcionario);

                List<ItemPedido> listaItens = new ArrayList<>();
                query = "SELECT ip.id_item_pedido AS id_item_pedido, ip.qtd AS qtd_item_pedido, "
                        + "p.id_prato AS id_prato, p.nome AS nome_prato, p.preco AS preco_prato, p.descricao AS descricao_prato, p.preco AS preco_prato, p.avaliacao AS avaliacao_prato "
                        + "FROM item_pedido ip INNER JOIN prato p ON ip.id_prato = p.id_prato WHERE ip.id_pedido = ?;";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, pedidoLocal.getId());
                rs = stmt.executeQuery();

                while (rs.next()) {
                    int idItemPedido = rs.getInt("id_item_pedido");
                    int quantidade = rs.getInt("qtd_item_pedido");
                    Prato prato = new Prato(rs.getInt("id_prato"), rs.getString("nome_prato"),
                            rs.getString("descricao_prato"), rs.getDouble("preco_prato"),
                            rs.getDouble("avaliacao_prato"));

                    ItemPedido itemPedido = new ItemPedido(idItemPedido, pedido, quantidade, prato);
                    listaItens.add(itemPedido);
                }

                pedido.setListaItens(listaItens);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return pedido;
    }

}