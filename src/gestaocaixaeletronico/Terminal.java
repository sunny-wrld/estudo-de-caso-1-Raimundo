package gestaocaixaeletronico;

import java.util.Scanner;

public class Terminal {

    private Caixa meuCaixa;
    private int modoAtual;

    public Terminal(CadastroContas bdContas) {
        this.meuCaixa = new Caixa(this, bdContas);
    }

    public void iniciaOperacao() {

        int opcao;
        opcao = this.getOpcao();

        while (opcao != 4) {
            switch (opcao) {
                case 1:
                    double saldo = this.meuCaixa.verificaSaldo(this.getInt("Número da Conta"), this.getInt("senha"));
                    if (saldo != -1) {
                        System.out.println("Saldo Atual: " + saldo);
                    } else {
                        System.out.println("Conta/ senha inválida");
                    }
                    break;
                case 2:
                    boolean b = this.meuCaixa.efetuaSaque(this.getInt("Número da Conta"), this.getInt("valor"),
                            this.getInt("senha"));
                    if (b) {
                        System.out.println("Saque Realizado");
                    } else {
                        System.out.println("Pedido de Saque Recusado");
                    }
                    break;
                case 3:
                    this.meuCaixa.recarrega();
                    break;
            }
            opcao = this.getOpcao();
        }

    }

    public void setModo(int modo) {
        if (modo == 0 || modo == 1) {
            this.modoAtual = modo;
        }
    }

    private int getOpcao() {
        int opcao;
        do {
            if (this.modoAtual == 1) {
                opcao = this.getInt("1 - Consulta Saldo, 2 - Saque, 4 - Sair");
                if (opcao != 1 & opcao != 2 & opcao != 4) {
                    opcao = 0;
                }
            } else {
                opcao = this.getInt("3 - Recarrega Caixa, 4 - Sair");
                if (opcao != 3 & opcao != 4) {
                    opcao = 0;
                }
            }
        } while (opcao == 0);

        return opcao;
    }

    private int getInt(String string) {

        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        System.out.println("Entre com " + string);

        if (sc.hasNextInt()) {
            return sc.nextInt();
        }

        @SuppressWarnings("unused")
        String st = sc.next();
        System.out.println("Erro na leitura de dados");
        return 0;

    }

}