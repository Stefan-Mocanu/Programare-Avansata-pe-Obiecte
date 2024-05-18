package org1.Paging;

import java.util.List;
import java.util.Scanner;

import static java.lang.Math.ceil;
import static java.lang.Math.min;

/**
 * Poate itera printr-o lista de T si sa afiseze in pagini de nrRezultatePagina elemente
 * Schimbarea nrRezultatePagina va afecta cate obiecte vor fi afisate pe o pagina
 * Pentru a utiliza aceata clasa se creaza un obiect de tip Paginare si se apeleaza functia afisare
 * @param <T> de preferabil functia toString din T sa afiseze continutul lui T, si nu Hash value sau adresa de memorie
 *
 */
public class Paginare<T> {
    public static int nrRezultatePagina = 3;
    List<T> deAfisat;
    public Paginare(List<T> deAfisat) {
        this.deAfisat = deAfisat;
    }
    private PagingCommands citire(){
        Scanner sc = new Scanner(System.in);
        PagingCommands x = PagingCommands.STOP;
        System.out.println("Introduceti NEXT sau STOP pentru a vizualiza urmatoarele elemente, sau pentru a opri afisarea");
        try {
            x = PagingCommands.valueOf(sc.next().strip());
        }catch (IllegalArgumentException e){
            System.out.println("Trebuie introdus NEXT sau STOP");
        }
        return x;
    }

    public void afisare(){
        int index = 1;
        int pagina = 1;
        int nrPagini = (int) ceil(((double) deAfisat.size())/nrRezultatePagina);
        boolean ok = true;
        if(nrPagini ==0){
            System.out.println("Pagina: 0"+"/"+nrPagini +" ("+nrRezultatePagina+" rezultate pe pagina)");
            return;
        }
        while(ok){
            System.out.println("Pagina: "+pagina+"/"+nrPagini +" ("+nrRezultatePagina+" rezultate pe pagina)");
            for(int i = (pagina-1)*nrRezultatePagina;
                i < min(pagina*nrRezultatePagina,deAfisat.size());
                i++){
                System.out.println(index+ ": "+ deAfisat.get(i).toString());
                index++;
            }
            if(pagina==nrPagini)break;
            PagingCommands command = citire();
            switch (command){
                case NEXT -> pagina++;
                case STOP -> ok=false;
            }

        }

    }
}
