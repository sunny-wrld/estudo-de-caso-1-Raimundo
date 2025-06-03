package testecaixaeletronico;

import gestaocaixaeletronico.*;
import gestaocontas.*;

public class TesteCaixaEletronico {
    public static void main(String[] args) {
        // Criação de clientes e contas
        Cliente cl1 = new Cliente("33", "mj");
        Cliente cl2 = new Cliente("10", "messi");
        Cliente cl3 = new Cliente("11", "romario");

        Conta c1 = new Conta(1, cl1, 3333, 336);
        Conta c2 = new Conta(2, cl2, 1000, 104);
        Conta c3 = new Conta(3, cl3, 1111, 111);

        CadastroContas bd = new CadastroContas(5);
        bd.adicionaConta(c1);
        bd.adicionaConta(c2);

        Terminal terminal = new Terminal(bd);
        Caixa caixa = new Caixa(terminal, bd);

        caixa.depositaDinheiro(1, 200);
        caixa.depositaCheque(2, 300);

        caixa.transfereValor(1, 2, 150, 123);
        caixa.efetuaSaque(2, 50, 321);

        System.out.println("Extrato 1:");
        System.out.println(c1.verificaHistoricoDeLancamentos(336));
        System.out.println("Extrato 2:");
        System.out.println(c2.verificaHistoricoDeLancamentos(104));
        System.out.println("Extrato 3:");
        System.out.println(c2.verificaHistoricoDeLancamentos(111));
}