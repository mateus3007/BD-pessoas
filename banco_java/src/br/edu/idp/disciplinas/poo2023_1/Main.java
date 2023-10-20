package br.edu.idp.disciplinas.poo2023_1;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) throws SQLException {

        // 1. Habilitate o driver JDBC a partir da aplicação cliente;
        //      ok



        // 2. Estabelecer uma conexão entre a aplicação cliente e servidor do banco de dados;
        String paramsConexao = "jdbc:h2:mem:testdb";
        String usuario = "";
        String senha = "";
        Connection conexao = DriverManager.getConnection(paramsConexao, usuario, senha);

        // 3. Montar e executar a consulta SQL desejada;
        String criacaoSql = "create table pessoa (id int primary key, nome varchar(150), qtdAcesso int, naturalidade varchar(120) )";
        Statement stmtC = conexao.createStatement();
        stmtC.executeUpdate(criacaoSql);
        stmtC.close();

        Statement startInsert = conexao.createStatement();
        String insert = "INSERT INTO pessoa (id, nome, qtdAcesso, naturalidade) VALUES (1,'Mateus',5,'Brasil');";
        startInsert.execute(insert);
        String insert1 = "INSERT INTO pessoa (id, nome, qtdAcesso, naturalidade) VALUES (2,'lucas',10,'Portugal');";
        startInsert.execute(insert1);
        String insert2 = "INSERT INTO pessoa (id, nome, qtdAcesso, naturalidade) VALUES (3,'pedro',50,'Espanha');";
        startInsert.execute(insert2);
        String insert3 = "INSERT INTO pessoa (id, nome, qtdAcesso, naturalidade) VALUES (4,'joao',8,'Japao');";
        startInsert.execute(insert3);
        startInsert.close();



        String consulta = "select nome, naturalidade, qtdAcesso from pessoa";
        Statement stmt = conexao.createStatement();
        ResultSet resultado = stmt.executeQuery(consulta);

        // 4. Processar no cliente o resultado da consulta.
        while (resultado.next()) {
            String nome = resultado.getString("nome");
            Integer quantidade = resultado.getInt("qtdAcesso");
            String naturalidade = resultado.getString("naturalidade");

            Pessoa pessoa = new Pessoa();
            pessoa.setNome(nome);
            pessoa.setQuantidadeAcesso(quantidade);
            pessoa.setNaturalidade(naturalidade);
            System.out.print(nome + ",");
            System.out.print(quantidade + ",");
            System.out.println(naturalidade);


        }



        System.out.println("\nTchau, até a próxima\n\n\t\t:-)");
    }

}
