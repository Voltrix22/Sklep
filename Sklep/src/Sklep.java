import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Sklep {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        HashMap<String, Double> produkty = new HashMap<>();
        HashMap<String, Integer> koszyk = new HashMap<>();

        produkty.put("mleko", 2.5);
        produkty.put("jajka", 9.99);
        produkty.put("chleb", 3.99);
        produkty.put("cukier", 4.5);
        produkty.put("woda", 2.8);

        double cena = 0;
        int ile;
        int ilosc = 0;
        int uilosc = 0;
        String koniec;
        String p;


        System.out.println("Witaj w sklepie XYZ: ");



        while(true) {
            System.out.println("--------------------------------------------------------------------------");
            System.out.println("D - dodaj produkt | U - usuń produkt | P - pokaż koszyk | K - koniec");

            p = input.nextLine().toUpperCase();


            //DODAWANIE
            if(p.equals("D")){
                System.out.println("Podaj produktym do zakupu "+produkty.keySet()+": ");
                p = input.nextLine();
                if(produkty.containsKey(p)){

                    //PODAWANIE ILOŚCI
                    while(true) {
                        System.out.println("Podaj ilość: ");
                        try {
                            ile = input.nextInt();
                            input.nextLine();
                            if (ile >= 1) {
                                ilosc += ile;
                                koszyk.put(p, ilosc);
                                cena += produkty.get(p);
                                cena *= ile;
                                System.out.println(ile + "x " + p + " dodano do koszyka!");
                                ilosc = 0;
                                break;
                            } else {
                                System.out.println("Brak podanej ilości produktów w sklepie");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println(" Zła wartość ");
                            input.nextLine();
                            break;
                        }
                    }
                }
                else{
                    System.out.println("Nie ma takiego produktu!");
                }
            }
            //POKAŻ KOSZYK
            else if(p.equals("P")){
                for(String tmp: koszyk.keySet()){
                    System.out.println(tmp+" x"+koszyk.get(tmp)+" cena za 1szt. - "+produkty.get(tmp));
                }
                System.out.println("Cena za wszystkie produkty: "+cena);
            }
            //USUŃ Z KOSZYKA
            else if(p.equals("U")){

                //NAZWA PRODUKTU DO USUNIĘCIA
                System.out.println("Co chcesz usunąć z koszyka? ");
                for (String tmp: koszyk.keySet()){
                    System.out.println(tmp);
                }
                p = input.nextLine();

                //ILOŚĆ PRODUKTÓW DO USUNIĘCIA
                if(koszyk.containsKey(p)) {
                    System.out.println("Podaj ile produktów ma usunąć: ");
                    uilosc = input.nextInt();
                    input.nextLine();

                    //ILOŚĆ USUWANA
                    if(koszyk.get(p)>0){
                        ilosc = koszyk.get(p) - uilosc;
                        koszyk.put(p, ilosc);

                        //ODEJMOWANA CENA
                        double ucena = 0;
                        ucena += produkty.get(p);
                        cena -= ucena * uilosc;

                        System.out.println(p+" "+uilosc+"x "+"usunięto z koszyka!");
                        if(koszyk.get(p)==0) {
                            koszyk.remove(p);
                        }
                    }
                    else{
                        System.out.println("Zbyt duża ilość podana do usunięcia");
                    }
                }
                else{
                    System.out.println("NIE MA TAKIEGO PRODUKTU W KOSZYKU");
                }


            }
            //KOŃCZENIE PROGRAMU
            else if (p.equals("K")) {
                System.out.println("Do zapłaty: " + cena);
                System.out.println();
                System.out.println("DZIĘKUJEMY ZA DOKONANIE ZAKUPÓW W NASZYM SKLEPIE!!!!!!");
                System.out.println("--------------------------------------------------------------------------");
                System.out.println();
                System.out.println("Chcesz wykonać kolejne zakupy?");
                System.out.println("Tak (t)");
                System.out.println("Nie (n)");
                koszyk.clear();
                cena = 0;
                koniec = input.nextLine().toUpperCase();


                if (koniec.equals("N")) {
                    System.out.println("Czy jesteś pewny?");
                    System.out.println("Tak (t)");
                    System.out.println("Nie (n)");
                    koniec = input.nextLine().toUpperCase();

                    if (koniec.equals("T")) {
                        System.out.println();
                        System.out.println("Miłego dnia :-)");
                        input.close();
                        break;
                    }
                }
            }
            //BRAK ZNAKU/ZŁY ZNAK
            else{
                System.out.println("?????????");
            }
        }
    }
}
