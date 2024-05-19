package Entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Otimo {

    // Declaração de variáveis
    private double tamanho;
    private int numeroDePaginas;
    private double[] pagina;
    private double[] tempo;

    public Otimo() {

    }

    // Construtor para inicializar os valores das variáveis acima
    public Otimo(double tamanho, int numeroDePaginas, double pagina[], double tempo[]) {
        this.tamanho = tamanho;
        this.numeroDePaginas = numeroDePaginas;
        this.pagina = pagina;
        this.tempo = tempo;
    }

    // Método que executa o algortimo Otimo
    public void executaOtimo() {
        // TamanhoEspera vai receber o tamanho das págians que estarão em espera
        // TamanhoInicial recebe o valor do tamanho inicial da memória, porque a memória irá ser alterada
        double tamanhoEspera = 0, tamanhoInicial = tamanho;

        // Variável para seber qual página está com mais tempo desde a última instrução
        int maior = 0;

        // Lista com o valor de todas às páginas que, estão sendo executadas
        List<Double> paginas = new ArrayList<>();

        // Lista com o valor de todas às páginas em espera
        List<Double> espera = new ArrayList<>();

        // Lista com às págians que foram executadas, mas não estão sendo mais
        List<Double> executadas = new ArrayList<>();

        // Lista com o valor das páginas que não foram executadas
        // porque têm um tamanho maior que o Tamanho da memória (tamanhoInicial)
        List<Double> naoExecutadas = new ArrayList<>();

        // For que adiciona às variáveis nas listas
        for (int i = 0; i < numeroDePaginas; i++) {
            // Se a página digitada for menor ou igual ao tamanho
            if (pagina[i] <= tamanho) {
                // A página será executada
                paginas.add(pagina[i]);
                // O tamanho disponível irá ser decrementado, porque, agora tem menos espaço de memória
                tamanho = tamanho - pagina[i];

                // lógica para pegar a página que espera por maior tempo
                // Só consegui fazer com um maior, estão é um algoritmo otimo que se degenera pra um FIFO
                if (i == 0) {
                    maior = i;
                } else if (tempo[i] > tempo[i - 1]) {
                    maior = i;
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

        // Enquanto o tamanho de páginas em espera existir
        while (tamanhoEspera != 0) {
            for (int i = 0; i < paginas.size(); i++) {
                // pegando a página com maior tempo
                if (maior == i) {
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
                            System.out.println(paginas.get(i));
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
                        maior = 0;
                    }
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


