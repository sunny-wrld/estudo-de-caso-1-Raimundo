package gestaocontas;

public class HistoricoDeLancamentos {

    private Lancamento[] lancamentos;
    private int ultimoLancamento;

    public HistoricoDeLancamentos() {
        this.lancamentos = new Lancamento[11];
    }

    public void registraLancamento(double valor, String descricao) {
        if (this.ultimoLancamento == this.lancamentos.length) {
            for (int i = 0; i < this.lancamentos.length; i++) {
                this.lancamentos[i] = this.lancamentos[i + 1];
            }
        } else {
            this.ultimoLancamento++;
        }
        this.lancamentos[this.ultimoLancamento] = new Lancamento(descricao, valor);

    }

    public String geraHistoricoDeLancamentos() {
        StringBuilder historico = new StringBuilder();

        for (int i = 1; i <= this.ultimoLancamento; i++) {
            historico.append(this.lancamentos[i].getDescricao())
                    .append(": R$")
                    .append(this.lancamentos[i].getValor())
                    .append("\n");
        }
        return historico.toString();
    }

}