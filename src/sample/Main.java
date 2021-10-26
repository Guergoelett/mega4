/**
 Mega Naldo e MIlton
 */
package sample;

import java.util.Scanner;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import viwer.MegaSena;

import javax.swing.*;


public class Main extends MegaSena {



    static int NUMERO_DEZENAS = 6;
    static String sorteioComputador = "";
    static String apostaUsuario = "";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[] sorteio = sorteaSena();
        int[] aposta = new int[NUMERO_DEZENAS];

        //trecho processa a aposta
        JOptionPane.showMessageDialog(null,
                "Mega da virada \n\n Faça sua aposta.",
                "Mega", JOptionPane.PLAIN_MESSAGE);
        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int nroAposta;
            boolean repetido = false;

            do {
                System.out.println("Informe a dezena "+(i+1)+": ");
                nroAposta = teclado.nextInt();
                if (nroAposta <= 0) {
                    System.out.println("Número inválido, aposta cancelada!");
                    return;
                }
                repetido = existeNumero(aposta, nroAposta);
                if (repetido) {
                    System.out.println("Número repetido!");
                }
            } while(repetido); // evita repetição de número

            aposta[i] = nroAposta;
        }

        System.out.println("\nConfira sua aposta: ");
        for (int i = 0; i < aposta.length; i++) {
            System.out.print(aposta[i] + " ");
            apostaUsuario = "Sua aposta: " + " \n" + aposta[0] + " ,"
                    + aposta[1] + " ," + aposta[2] + " ," + aposta[3] + " ,"
                    + aposta[4] + " " +
                    "," + aposta[5];
        }

        System.out.println("\nResultado do sorteio: ");
        for (int i = 0; i < sorteio.length; i++) {
            System.out.print(sorteio[i] + " ");

        }

        int nroAcertos = contaAcertos(sorteio, aposta);

        JOptionPane.showMessageDialog(null, apostaUsuario + "\n"
                        + sorteioComputador + " \n Acertos: \n "
                        + nroAcertos, "Mega Sena",
                JOptionPane.INFORMATION_MESSAGE);

        }



    static int[] sorteaSena() {
        int[] resultado = new int[NUMERO_DEZENAS];

        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int sorteado;
            boolean repetido = false;

            do {
                sorteado = (int) (Math.random()*60)+1; //nro aleatorio de 1 a 60
                repetido = existeNumero(resultado, sorteado);
            } while(repetido); // evita repetição de número

            resultado[i] = sorteado;

        }

        return resultado;
    }


    static int contaAcertos(int[] sorteio, int[] aposta) {
        int acertos = 0;
        for (int i = 0; i < NUMERO_DEZENAS; i++) {
            int nroAposta = aposta[i];




            if (existeNumero(sorteio, nroAposta)) {
                acertos++;
            }
        }

        return acertos;

    }


    static boolean existeNumero(int[] numeros, int n) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == n) {
                return true;
            }
        }
        return false;
    }

}