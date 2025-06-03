package gestaocaixaeletronico;

import gestaocontas.Conta;

public class Caixa {

    private Terminal meuTerminal;
    private CadastroContas bdContas;
    private double saldo;

    public Caixa(Terminal terminal, CadastroContas bdContas) {
        this.meuTerminal = terminal;
        this.bdContas = bdContas;
    }

    public double verificaSaldo(int numero, int senha) {
        Conta conta = this.bdContas.buscaConta(numero);
        if (conta != null) {
            return conta.verificaSaldo(senha);
        }
        return -1;
    }

    public boolean efetuaSaque(int numero, double valor, int senha) {
        if (valor < 0 || (valor % 50) != 0 || valor > 500 || valor > this.saldo) {
            return false;
        }

        Conta conta = this.bdContas.buscaConta(numero);
        if (conta == null || !conta.debitaValor(valor, senha, "Saque")) {
            return false;
        }

        this.saldo -= valor;
        this.liberaCedulas((int) (valor / 50));

        if (this.saldo < 500) {
            this.meuTerminal.setModo(0);
        }

        return true;
    }

    public void recarrega() {
        this.saldo = 2000;
        this.meuTerminal.setModo(1);
    }

    private void liberaCedulas(int quantidade) {
        while (quantidade-- > 0) {
            System.out.println("===/ R$ 50,00 /===");
        }
    }

    public boolean depositaDinheiro(int numeroConta, double valor) {
        Conta conta = this.bdContas.buscaConta(numeroConta);
        if (conta == null || valor <= 0) {
            return false;
        }
        return conta.creditaValor(valor, "Depósito em Dinheiro");
    }

    public boolean depositaCheque(int numeroConta, double valor) {
        Conta conta = this.bdContas.buscaConta(numeroConta);
        if (conta == null || valor <= 0) {
            return false;
        }
        return conta.creditaValor(valor, "Depósito em Cheque");
    }

    public boolean transfereValor(int origem, int destino, double valor, int senhaOrigem) {
        if (valor <= 0) {
            return false;
        }

        Conta contaOrigem = this.bdContas.buscaConta(origem);
        Conta contaDestino = this.bdContas.buscaConta(destino);

        if (contaOrigem == null || contaDestino == null) {
            return false;
        }

        boolean debitoRealizado = contaOrigem.debitaValor(valor, senhaOrigem, "Transferência para conta " + destino);
        if (debitoRealizado) {
            contaDestino.creditaValor(valor, "Transferência recebida de conta " + origem);
            return true;
        }

        return false;
    }
}
