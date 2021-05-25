package br.com.alura.bytebank.registrador;

import br.com.alura.bytebank.model.Pagamento;
import br.com.alura.bytebank.model.Tipo;

import java.util.ArrayList;
import java.util.List;

public class RegistroDePagamentoAlpha {

    private final static List<Pagamento> psr = new ArrayList<>();

    public void registra(List<Pagamento> ps) {
    for (int i = 0; i < ps.size(); i++) {
        Pagamento p = ps.get(i);
        if (p.getTipo().equals(Tipo.CREDITO)) { p.setValor(p.getValor() * 1.02);
            psr.add(p); System.out.println("Pagamento realizado " + p);
        } if (p.getTipo().equals(Tipo.DEBITO)) { p.setValor(p.getValor() * 1.001);
            psr.add(p); System.out.println("Pagamento realizado " + p);
        } if (p.getTipo().equals(Tipo.DINHEIRO)) { psr.add(p); System.out.println("Pagamento realizado " + p); }
    } if (!psr.isEmpty()) { System.out.println("Todos os pagamentos");
    for (int i = 0; i < psr.size(); i++) {
        System.out.println(psr.get(i));
    }
    } else { System.out.println("Não temos pagamentos"); }
    }

}
