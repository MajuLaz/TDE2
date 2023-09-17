import java.util.*;

public class JogoPilha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o tamanho das pilhas: ");
        int tamanhoPilhas = scanner.nextInt();

        Pilha pilha1 = new Pilha(1);
        pilha1.setNumero(1);

        Pilha pilha2 = new Pilha(2);
        pilha2.setNumero(2);

        Pilha pilha3 = new Pilha(3);
        pilha3.setNumero(3);

        // Preenche pilha1 com números aleatórios na faixa de 1 a 100
        Random random = new Random();
        for (int i = 0; i < tamanhoPilhas; i++) {
            pilha1.empilhar(random.nextInt(100) + 1);
        }

        System.out.println("Pilha 1:");
        pilha1.imprimir();
        System.out.println("Pilha 2:");
        pilha2.imprimir();
        System.out.println("Pilha 3:");
        pilha3.imprimir();

        System.out.print("Deseja ordenar em ordem crescente (1) ou decrescente (2): ");
        int escolha = scanner.nextInt();
        boolean ordemCrescente = (escolha == 1);

        int jogadas = 0;

        boolean continuarJogando = true;

        while (continuarJogando) {
            System.out.println("Escolha uma opção:");
            System.out.println("0 - Sair do jogo.");
            System.out.println("1 - Movimentar.");
            System.out.println("2 - Solução automática.");

            int opcao = scanner.nextInt();

            if (opcao == 0) {
                System.out.println("Jogo encerrado.");
                break;
            } else if (opcao == 1) {
                System.out.print("Digite a pilha de origem (1, 2 ou 3): ");
                int origem = scanner.nextInt();
                System.out.print("Digite a pilha de destino (1, 2 ou 3): ");
                int destino = scanner.nextInt();

                Pilha pilhaOrigem, pilhaDestino;

                if (origem == 1) {
                    pilhaOrigem = pilha1;
                } else if (origem == 2) {
                    pilhaOrigem = pilha2;
                } else {
                    pilhaOrigem = pilha3;
                }

                if (destino == 1) {
                    pilhaDestino = pilha1;
                } else if (destino == 2) {
                    pilhaDestino = pilha2;
                } else {
                    pilhaDestino = pilha3;
                }

                if (!pilhaOrigem.estaVazia()
                        && (pilhaDestino.estaVazia() || pilhaOrigem.topo() < pilhaDestino.topo())) {
                    int disco = pilhaOrigem.desempilhar();
                    pilhaDestino.empilhar(disco);
                    jogadas++;

                    System.out.println("Pilha 1:");
                    pilha1.imprimir();
                    System.out.println("Pilha 2:");
                    pilha2.imprimir();
                    System.out.println("Pilha 3:");
                    pilha3.imprimir();

                    if (pilha1.estaOrdenada(ordemCrescente) || pilha2.estaOrdenada(ordemCrescente)
                            || pilha3.estaOrdenada(ordemCrescente)) {
                        System.out.println("Ordenação concluída em " + jogadas + " jogadas.");

                        // Pergunta se o jogador deseja continuar
                        System.out.print("Deseja continuar a ordenar (1 - Sim, 0 - Não)? ");
                        int continuar = scanner.nextInt();
                        if (continuar == 0) {
                            System.out.println("Jogo encerrado.");
                            continuarJogando = false;
                        }
                    }
                } else {
                    System.out.println("Movimento inválido.");
                }
            } else if (opcao == 2) {
                // Solução automática
                solucaoAutomatica(pilha1, pilha3, pilha2, tamanhoPilhas, ordemCrescente);
                jogadas++;
                System.out.println("Solução automática concluída.");

                // Imprime as pilhas após a solução automática
                System.out.println("Pilha 1:");
                pilha1.imprimir();
                System.out.println("Pilha 2:");
                pilha2.imprimir();
                System.out.println("Pilha 3:");
                pilha3.imprimir();

                if (pilha1.estaOrdenada(ordemCrescente) || pilha2.estaOrdenada(ordemCrescente)
                        || pilha3.estaOrdenada(ordemCrescente)) {
                    //System.out.println("Ordenação concluída em " + jogadas + " jogadas.");

                    // Pergunta se o jogador deseja continuar
                    System.out.print("Deseja continuar a ordenar (1 - Sim, 0 - Não)? ");
                    int continuar = scanner.nextInt();
                    if (continuar == 0) {
                        System.out.println("Jogo encerrado.");
                        continuarJogando = false;
                    }
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    public static void solucaoAutomatica(Pilha origem, Pilha destino, Pilha intermediaria, int tamanho,
            boolean ordemCrescente) {
        if (tamanho > 0) {
            solucaoAutomatica(origem, intermediaria, destino, tamanho - 1, ordemCrescente);

            int disco = origem.desempilhar();
            destino.empilhar(disco);
            if (disco == -1) {
                System.out.println("Pilha Vazia");
            } else {

                // System.out.println("Mover disco " + disco + " da Pilha " + origem.getNumero()
                // + " para Pilha " + destino.getNumero());

            }

            System.out.println("Pilha de origem:");
            origem.imprimir();
            System.out.println("Pilha de destino:");
            destino.imprimir();
            System.out.println("Pilha intermediária:");
            intermediaria.imprimir();
            System.out.println("Ordenando pilhas...");
            ordenarPilhas(origem, destino, intermediaria, ordemCrescente);

            solucaoAutomatica(intermediaria, destino, origem, tamanho - 1, ordemCrescente);
        }
    }

    // função para ordenar as pilhas
    public static void ordenarPilhas(Pilha origem, Pilha destino, Pilha intermediaria, boolean ordemCrescente) {
        while (!origem.estaVazia()) {
            int disco = origem.desempilhar();
            if (ordemCrescente) {
                while (!destino.estaVazia() && destino.topo() < disco) {
                    intermediaria.empilhar(destino.desempilhar());
                }
            } else {
                while (!destino.estaVazia() && destino.topo() > disco) {
                    intermediaria.empilhar(destino.desempilhar());
                }
            }
            destino.empilhar(disco);
            while (!intermediaria.estaVazia()) {
                destino.empilhar(intermediaria.desempilhar());
            }
        }
    }
}


