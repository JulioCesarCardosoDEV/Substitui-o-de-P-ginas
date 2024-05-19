package Entities;

import java.util.LinkedList;

public class SC {

    // Declaração de variáveis
    private double tamanho;
    private int numeroDePaginas;
    private double[] pagina;

    public SC() {

    }

    // Construtor para inicializar os valores das variáveis acima
    public SC(double tamanho, int numeroDePaginas, double pagina[]) {
        this.tamanho = tamanho;
        this.numeroDePaginas = numeroDePaginas;
        this.pagina = pagina;
    }

    // Método que executa o algortimo SC
    public void executaSC() {
        // TamanhoEspera vai receber o tamanho das págians que estarão em espera
        // TamanhoInicial recebe o valor do tamanho inicial da memória, porque a memória irá ser alterada
        double tamanhoEspera = 0, tamanhoInicial = tamanho;

        // Lista com o valor de todas às páginas que, estão sendo executadas
        LinkedList<Double> paginas = new LinkedList<>();

        // Lista com o valor de todas às páginas em espera
        LinkedList<Double> espera = new LinkedList<>();

        // Lista com às págians que foram executadas, mas não estão sendo mais
        LinkedList<Double> executadas = new LinkedList<>();

        // Lista com o valor das páginas que não foram executadas
        // porque têm um tamanho maior que o Tamanho da memória (tamanhoInicial)
        LinkedList<Double> naoExecutadas = new LinkedList<>();

        // Lista com o estado de R de todas às páginas
        LinkedList<Double> estadoDeR = new LinkedList<>();

        // For que adiciona às variáveis nas listas
        for (int i = 0; i < numeroDePaginas; i++) {
            // Se a página digitada for menor ou igual ao tamanho
            if (pagina[i] <= tamanho) {
                // A página será executada
                paginas.add(pagina[i]);
                // O tamanho disponível irá ser decrementado, porque, agora tem menos espaço de memória
                tamanho = tamanho - pagina[i];

                // A primeira página terá o valor de R = 1
                // Significa que ela tem 2 chances antes de ser retirada
                if (i == 0) {
                    estadoDeR.add(1.0);
                }
                // As demais páginas tem o valord e R = 0
                else {
                    estadoDeR.add(0.0);
                }
            // Se a página for menor que o tamanhoInicial, a página irá ser colocada na lista de espera
            } else if (pagina[i] <= tamanhoInicial) {
                // tamanho total das páginas em espera
                tamanhoEspera = tamanhoEspera + pagina[i];
                // Página adicionada à lista de espera
                espera.add(pagina[i]);
            // Se a página for maior que o tamanhoInicial, ela não será executa
            } else if (pagina[i] > tamanhoInicial) {
                // adiciona na lista não executadas
                naoExecutadas.add(pagina[i]);
            }
        }
        System.out.println();

        // Enquanto o tamanho de páginas em espera existir
        while (tamanhoEspera != 0) {
            for (int i = 0; i < paginas.size(); i++) {
                // Imprimindo às páginas e o seus estados de R
                System.out.println("Pagina " + i + " com valor: " + paginas.get(i));
                System.out.println("Estado de R: " + estadoDeR.get(i));

                // Se o estado de R == 0
                if (estadoDeR.get(i) == 0.0) {
                    for (int j = 0; j < espera.size(); j++) {
                        // se tiver espaço para adicionar a página
                        if (espera.get(j) <= tamanho) {
                            // adiciona a página em execução
                            paginas.add(espera.get(j));
                            tamanho = tamanho - espera.get(j);
                            // remove da lista de espera
                            tamanhoEspera = tamanhoEspera - espera.get(j);
                            espera.remove(j);
                        // senão retira uma página em execução
                        } else {
                            tamanho = tamanho + paginas.get(i);
                            executadas.add(paginas.get(i));
                            paginas.remove(i);

                           // se tiver espaço para adicionar a página
                            if (espera.get(j) <= tamanho) {
                                // adiciona a página em execução
                                paginas.add(espera.get(j));
                                tamanho = tamanho - espera.get(j);
                                // remove da lista de espera
                                tamanhoEspera = tamanhoEspera - espera.get(j);
                                espera.remove(j);
                            }
                        }
                    }
                // Se o estado não for 0, atualie a lista e decremente o R
                } else {
                    paginas.set(i, paginas.get(i));
                    estadoDeR.set(i, estadoDeR.get(i) - 1);
                }
            }
        }

        // Imprimi os valores na tela
        System.out.println("\nMemória livre: " + tamanho);
        System.out.println("Paginas sendo executadas: ");
        paginas.forEach(System.out::println);

        System.out.println("\nPáginas que já foram executadas: ");
        executadas.forEach(System.out::println);

        System.out.println("\nPáginas que não foram executadas: ");
        naoExecutadas.forEach(System.out::println);
    }

}