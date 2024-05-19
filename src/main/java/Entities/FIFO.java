package Entities;

import java.util.ArrayList;
import java.util.List;

public class FIFO {

    // Declaração de variáveis
    private double tamanho;
    private int numeroDePaginas;
    private double [] pagina;
    public FIFO() {

    }

    // Construtor para inicializar os valores das variáveis acima
    public FIFO(double tamanho, int numeroDePaginas, double pagina []) {
        this.tamanho = tamanho;
        this.numeroDePaginas = numeroDePaginas;
        this.pagina = pagina;
    }

    // Método que executa o algortimo FIFO
    public void executaFIFO(){
        // TamanhoEspera vai receber o tamanho das págians que estarão em espera
        // TamanhoInicial recebe o valor do tamanho inicial da memória, porque a memória irá ser alterada
        double tamanhoEspera = 0, tamanhoInicial = tamanho;

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

            // Senão, a página irá ser colocada na lista de espera
            } else {
                // tamanho total das páginas em espera
                tamanhoEspera = tamanhoEspera + pagina[i];
                // Página adicionada à lista de espera
                espera.add(pagina[i]);
            }
        }

        // Enquanto o tamanho de páginas em espera existir
        while (tamanhoEspera != 0) {
         for (int i = 0; i < espera.size(); i++) {
             // Se espera for menor ou igual ao tamanhoInicial (se for maior, ela não rodaria mesmo com espaço livre)
                if (espera.get(i) <= tamanhoInicial) {
                    // Incrementa o tamanho da página executada, significa que tem espaço livre
                    tamanho = tamanho + paginas.get(0);

                    // A página que estava sendo executada, agora foi retirada e é adicionada
                    // na lista de páginas que já foram executadas
                    executadas.add(paginas.get(0));

                    // Remoção da primeira página (primeira página por conta do algoritmo ser FIFO)
                    paginas.remove(0);

                    // se tiver espaço para a página em espera
                    if (espera.get(i) <= tamanho) {
                        // adicione a página em espera em executando e decremente o tamanho
                        paginas.add(espera.get(i));
                        tamanho = tamanho - espera.get(i);

                        // e retire a página da lista espera e decremente o tamanhoEspera
                        tamanhoEspera = tamanhoEspera - espera.get(i);
                        espera.remove(i);
                    }
           // Se espra for maior que tamanhoInicial, ela entrará para a lista de não executadas
                } else {
                    tamanhoEspera = tamanhoEspera - espera.get(i);
                    naoExecutadas.add(espera.get(i));
                    espera.remove(i);
                }
            }
        }

        // Imprimi os valores na tela
        System.out.println("\nMemória livre: " + tamanho);
        System.out.println("Paginas sendo executadas: ");
        paginas.forEach(System.out::println);

        System.out.println("\nPáginas que já foram executadas: " );
        executadas.forEach(System.out::println);

        System.out.println("\nPáginas que não foram executadas: ");
        naoExecutadas.forEach(System.out::println);
    }
}
