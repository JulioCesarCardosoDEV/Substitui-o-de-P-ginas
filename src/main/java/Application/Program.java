
package Application;

import Entities.FIFO;
import Entities.Otimo;
import Entities.PaginaRelogio;
import Entities.SC;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double tamanho = 0;
        int tipo = 0;

        // Tipo de algorimto escolhido
        System.out.print("Digite o tipo de algoritmo (1 FIFO, 2 PaginaRelogio, 3 SC, 4 Otimo): ");
        tipo = sc.nextInt();

        // Tamanho total da memória
        System.out.print("Digite o tamanho da memória: ");
        tamanho = sc.nextDouble();

        // Quantidade de páginas
        System.out.print("Digite o número de páginas: ");
        int numeroDePaginas = sc.nextInt();

        // Variável pagina pega o valor da página digitada
        // Variável Tempo será usada no algoritmo otimo
        double[] pagina = new double[numeroDePaginas];
        double[] tempo = new double[numeroDePaginas];

        // Switch case para o tipo escolhido
        switch (tipo) {
            // Caso 1 = executa o algoritmo FIFO
            case 1:
                // Criando objeto da classe FIFO
                FIFO fifo = null;

                // For para instanciar o objeto fifo
                for (int i = 0; i < numeroDePaginas; i++) {
                    System.out.print("Digite o tamanho da página " + (i + 1) + ": ");
                    pagina[i] = sc.nextDouble();
                    fifo = new FIFO(tamanho, numeroDePaginas, pagina);
                }

                // Método que executa o algoritmo FIFO
                fifo.executaFIFO();
                break;

            // Caso 2 = executa o algoritmo PaginaRelogio
            case 2:
                // Criando objeto da classe PaginaRelogio
                PaginaRelogio paginaRelogio = null;

                // For para instanciar o objeto paginaRelogio
                for (int i = 0; i < numeroDePaginas; i++) {
                    System.out.print("Digite o tamanho da página " + (i + 1) + ": ");
                    pagina[i] = sc.nextDouble();
                    paginaRelogio = new PaginaRelogio(tamanho, numeroDePaginas, pagina);
                }

                // Método que executa o algoritmo PaginaRelogio
                paginaRelogio.executaPaginaRelogio();
                break;

            // Caso 3 = executa o algoritmo SC (Second Chance)
            case 3:
                // Criando objeto da classe SC
                SC obj = null;

                // For para instanciar o objeto obj
                for (int i = 0; i < numeroDePaginas; i++) {
                    System.out.print("Digite o tamanho da página " + (i + 1) + ": ");
                    pagina[i] = sc.nextDouble();
                    obj = new SC(tamanho, numeroDePaginas, pagina);
                }

                // Método que executa o algoritmo SC
                obj.executaSC();
                break;

            // Caso 4 = executa o algoritmo Otimo
            case 4:
                // Criando objeto da classe Otimo
                Otimo otimo = null;

                // For para instanciar o objeto otimo
                for (int i = 0; i < numeroDePaginas; i++) {
                    System.out.print("Digite o tamanho da página " + (i + 1) + ": ");
                    pagina[i] = sc.nextDouble();
                    System.out.print("Digite o tempo desde a última intrução da página: ");
                    tempo[i] = sc.nextDouble();
                    otimo = new Otimo(tamanho, numeroDePaginas, pagina, tempo);
                }

                // Método que executa o algoritmo Otimo
                otimo.executaOtimo();
                break;

            // Caso não seja nenhum dos casos, o tipo de algoritmo digitado é inválido
            default:
                System.out.println("Tipo de algoritmo inválido");
        }

        sc.close();
    }
}
