public class ListaDuplamente {
    No inicio, fim;

    public ListaDuplamente() {
        inicializa();
    }

    public void inicializa(){
        inicio = fim = null;
    }

    public void inserirNoInicio(int info){
        No novaCaixa = new No(null,null,info);
        if(fim == null)
        {
            fim = inicio = novaCaixa;
        }
        else
        {
            novaCaixa.setProx(inicio);
            inicio.setAnt(novaCaixa);
            inicio = novaCaixa;
        }
    }

    public void inserirNoFim(int info){
        No novaCaixa = new No(null,null,info);
        if(fim == null)
        {
            fim = inicio = novaCaixa;
        }
        else
        {
            novaCaixa.setAnt(fim);
            fim.setProx(novaCaixa);
            fim = novaCaixa;
        }
    }

    public void exibir()
    {
        for (No aux = inicio; aux!=null; aux = aux.getProx())
            System.out.println(aux.getInfo());
    }

    public No BuscaExaustiva(int info){
        No aux = inicio;
        while (aux!=null && aux.getInfo()!=info)
            aux = aux.getProx();

        return aux;
    }


    public void remover(int info){
        No aux = inicio,prox,ant;
        aux = BuscaExaustiva(info);
        if(aux != null)
        {
            if(inicio == fim)
            {
                inicio = fim = null;
            } else if (aux == inicio) {
                aux = inicio.getProx();
                inicio.setProx(null);
                aux.setAnt(null);
                inicio = aux;
            }
            else if (aux == fim) {
                aux = fim.getAnt();
                fim.setAnt(null);
                aux.setProx(null);
                fim = aux;
            }
            else {
                prox = aux.getProx();
                ant = aux.getAnt();
                prox.setAnt(ant);
                ant.setProx(prox);
            }
        }
    }

    public void insercaoDireta(int info)
    {
        inserirNoFim(info);
        No i = this.inicio;
        No pos;
        int aux,ant,atual;

        while (i!=null)
        {
            aux = i.getInfo();
            for (pos = i; pos != inicio && aux < pos.getAnt().getInfo(); pos = pos.getAnt())
            {
                ant = pos.getAnt().getInfo();
                atual = pos.getInfo();

                pos.getAnt().setInfo(atual);
                pos.setInfo(ant);
            }
                i = i.getProx();
            pos.setInfo(aux);

        }
    }

    public void selecaoDireta()
    {
        No  i, menor;
        int menorValor,aux;

        for (i = inicio; i != fim; i = i.getProx()){
            menorValor = i.getInfo();
            menor = i;
            for (No j = i.getProx(); j != null ; j = j.getProx()){
                if(j.getInfo() < menorValor){
                    menorValor = j.getInfo();
                    menor = j;
                }
            }
            aux = i.getInfo();
            i.setInfo(menorValor);
            menor.setInfo(aux);
        }

    }

    public void bubbleSort()
    {
        int temp;
        boolean flag = true;

        for (No TLF = fim; inicio != TLF && flag; TLF = TLF.getAnt())
        {
            flag = false;
            for (No j = inicio ; j != TLF; j = j.getProx()){
                if(j.getInfo() > j.getProx().getInfo())
                {
                    temp = j.getInfo();
                    j.setInfo(j.getProx().getInfo());
                    j.getProx().setInfo(temp);
                    flag = true;
                }
            }
        }
    }

    public void shakeSort()
    {
        int temp;
        boolean flag = true;
        No TLF = fim,TLI=inicio;
        while (TLF != TLI && flag)
        {
            flag = false;
            for (No j = TLI ; j != TLF; j = j.getProx()){
                if(j.getInfo() > j.getProx().getInfo())
                {
                    temp = j.getInfo();
                    j.setInfo(j.getProx().getInfo());
                    j.getProx().setInfo(temp);
                    flag = true;
                }
            }
            TLF = TLF.getAnt();
            if(flag) {
                for (No j = TLF ; j != TLI; j = j.getAnt()){
                    if(j.getInfo() < j.getAnt().getInfo())
                    {
                        temp = j.getInfo();
                        j.setInfo(j.getAnt().getInfo());
                        j.getAnt().setInfo(temp);
                    }
                }
            }
            TLI = TLI.getProx();
        }
    }


    public void insereEmPosEspecificaLista(int pos, int elem){
        No i = this.inicio;
        while (i!=null && pos > 0) {
            i = i.getProx();
            pos--;
        }
        if(i!=null) {
            i.setInfo(elem);
        } else {
            System.out.println("Posição inválida.");
        }
    }

    public No buscaeEmPosEspecificaLista(int pos){
        No i = inicio;

        while (i!=null && pos > 0)
        {
            i = i.getProx();
            pos--;
        }

        return i;
    }
    public void countingSort(){
        ListaDuplamente listaSoma = new ListaDuplamente();
        No  i = inicio, j;
        int maior = -1;
        if(i!=null) {
            maior = i.getInfo();
            for (i = inicio; i != null; i = i.getProx()) {//procurar o maior valor no vetor
                if (maior < i.getInfo())
                    maior = i.getInfo();
            }
            while (maior >= 0) { // criar a lista com todos valores zeraods
                listaSoma.inserirNoInicio(0);
                maior--;
            }
            for (i = inicio ; i != null; i = i.getProx()) {// contar as ocorrencias
                int info = i.getInfo();
                int valorAnterior = listaSoma.buscaeEmPosEspecificaLista(info).getInfo();
                listaSoma.insereEmPosEspecificaLista(info,  valorAnterior + 1);
            }
            for (j = listaSoma.inicio.getProx() ; j != null; j = j.getProx()) { // realizar uma janela deslizante na lista
                int valorAtual = j.getInfo();
                int valorAnterior = j.getAnt().getInfo();
                j.setInfo(valorAtual + valorAnterior);
            }
            for (i = fim; i != null ; i = i.getAnt()){ // realizar a ordenaçao
                int posVetorContador = i.getInfo();
                int qtdOcorrencia = listaSoma.buscaeEmPosEspecificaLista(posVetorContador).getInfo();
                insereEmPosEspecificaLista(qtdOcorrencia-1, posVetorContador);
            }
        }
    }


}
