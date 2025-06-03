package Main;

import gestaocaixaeletronico.*;
import gestaocontas.*;

public class Main {

    public static void main(String[] args) {

        Cliente cl1 = new Cliente("123", "Osvaldo");
        Cliente cl2 = new Cliente("321", "Maria");

        Conta c1 = new Conta(1, cl1, 1000, 123);
        Conta c2 = new Conta(2, cl2, 1000, 123);
        Conta c3 = new Conta(3, cl1, 1000, 123);

        CadastroContas bd = new CadastroContas(5);

        bd.adicionaConta(c1);
        bd.adicionaConta(c2);
        bd.adicionaConta(c3);

        Terminal meuTerminal = new Terminal(bd);
        meuTerminal.iniciaOperacao();



    }

}