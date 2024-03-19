public class Main {
    public static void main(String[] args) {

        ListaDuplamente lista = new ListaDuplamente();
        System.out.println("inserindo no inicio");
        lista.inserirNoInicio(1);
        lista.inserirNoInicio(5);
        lista.inserirNoInicio(2);
        lista.inserirNoInicio(3);
        lista.inserirNoInicio(3);
        lista.inserirNoInicio(0);
        lista.inserirNoInicio(0);
        lista.inserirNoInicio(0);
        lista.inserirNoInicio(0);
        lista.exibir();
        System.out.println("////////////////////////////////");


        lista.countingSort();
        System.out.println("couting");
        lista.exibir();


    }
}