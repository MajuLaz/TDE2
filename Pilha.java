class Pilha {
    private Node topo;
    private int numero;

    public Pilha(int numero) {
        
        this.topo = null;
        this.numero = numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() { 
        return numero;
    }

    public boolean estaVazia() {
        return topo == null;
    }

    public void empilhar(int dado) {
        Node novoNode = new Node(dado);
        novoNode.proximo = topo;
        topo = novoNode;
    }

    public int desempilhar() {
    if (estaVazia()) {
        return -1; 
    }
    int dado = topo.dado;
    topo = topo.proximo;
    return dado;
}


    public int topo() {
        if (estaVazia()) {
            System.out.println("Erro: A pilha está vazia.");
            return -1; 
        }
        return topo.dado;
    }

public void imprimir() {
    Node atual = topo;
    while (atual != null) {
        if (atual.dado != -1) {
            System.out.print(atual.dado + " ");
        }
        atual = atual.proximo;
    }
    System.out.println();
}


    public boolean estaOrdenada(boolean ordemCrescente) {
        if (estaVazia() || topo.proximo == null) {
            return true; 
        }
        
        return estaOrdenadaRecursivamente(topo, ordemCrescente);
    }

    private boolean estaOrdenadaRecursivamente(Node atual, boolean ordemCrescente) {
        if (atual.proximo == null) {
            return true; 
        }
        
        int valorAtual = atual.dado;
        int proximoValor = atual.proximo.dado;
        
        if ((ordemCrescente && proximoValor < valorAtual) || (!ordemCrescente && proximoValor > valorAtual)) {
            return false; 
        }
        
        return estaOrdenadaRecursivamente(atual.proximo, ordemCrescente);
    }
}

class Node {
    int dado;
    Node proximo;

    public Node(int dado) {
        this.dado = dado;
        this.proximo = null;
    }
}