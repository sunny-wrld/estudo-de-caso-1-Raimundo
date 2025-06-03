
package gestaocontas;

public class Conta {

    private int numero;
    private Cliente titular;
    private double saldo;
    private int senha;
    private HistoricoDeLancamentos historico;

    public Conta(int numero, Cliente titular, double saldo, int senha) {
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldo;
        this.senha = senha;
        this.historico = new HistoricoDeLancamentos();
    }

    public int getNumero() {
        return this.numero;
    }

    public Cliente getTitular() {
        return this.titular;
    }

    public double verificaSaldo(int senha) {
        if (senha == this.senha) {
            return this.saldo;
        }
        return -1;
    }

    public boolean creditaValor(double valor, String descricao) {
        if (valor >= 0) {
            this.saldo += valor;
            historico.registraLancamento(valor, descricao);
            return true;
        }
        return false;
    }

    public boolean debitaValor(double valor, int senha, String descricao) {
        if (this.senha == senha && valor <= this.saldo && valor > 0) {
            this.saldo -= valor;
            historico.registraLancamento(-valor, descricao);
            return true;
        }
        return false;
    }

    /* public boolean depositaDinheiro(int numeroConta, double valor) {
        Conta conta = this.bdContas.buscaConta(numeroConta);
        if (conta == null || valor <= 0) {
            return false;
        }
        return conta.creditaValor(valor, "Depósito em Dinheiro");
    }

    public boolean depositaCheque(int numeroConta, double valor) {
        Conta conta = this.Contas.buscaConta(numeroConta);
        if (conta == null || valor <= 0) {
            return false;
        }
        return conta.creditaValor(valor, "Depósito em Cheque");
    }

    // public boolean transfereValor(int numeroConta, double valor, int senha) {
        Conta conta = this.Contas.buscaConta(numeroConta);
        if (conta == null || !this.debitaValor(valor, senha, "Transferência para conta " + numeroConta)) {
            return false;
        }
        return conta.creditaValor(valor, "Transferência recebida de conta " + this.numero);
    }
    intelij que fez */

    public String verificaHistoricoDeLancamentos(int senha) {
        if(this.senha == senha) {
            return this.historico.geraHistoricoDeLancamentos();
        }
        return null;
    }

}
