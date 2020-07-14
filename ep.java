import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//modificar codigo pra descobrir quantos lugares são freequentados por 1 pessoa, por 2, 3 em diante

public class ep {

  public static void main(String[] args) throws IOException {

    ep obj = new ep();
    obj.run();

  }

  public void run() throws IOException {

    String arquivoCSV = "OD_2017.csv";
    BufferedReader br = null;
    String linha = "";
    String csvDivisor = ",";

    List<Local> lista = new ArrayList<Local>();
    

    //lendo do arquivo csv
    try {

        br = new BufferedReader(new FileReader(arquivoCSV));
        String aux = br.readLine(); // linha de legendas
        linha = br.readLine();

        while (linha  != null) {

            String[] locais = linha.split(csvDivisor);

            pegaLugar(lista, locais, 2);

            pegaLugar(lista, locais, 57);

            pegaLugar(lista, locais, 62);

            pegaLugar(lista, locais, 71);

            pegaLugar(lista, locais, 84);

            pegaLugar(lista, locais, 88);
            
            pegaLugar(lista, locais, 92);
            
            pegaLugar(lista, locais, 96);

            pegaLugar(lista, locais, 100);

            System.out.println("rodando ate agora");

            linha = br.readLine();

            
        }

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //organizando os dados para o histograma
    List<Freq> freq = new ArrayList<Freq>();
    boolean existe = false;

    for(Iterator<Local> iLocal = lista.iterator(); iLocal.hasNext();){
        Local b = iLocal.next();
        Freq x = new Freq(b.frequentadores.size());

        for (Iterator<Freq> iFreq = freq.iterator(); iFreq.hasNext();) {
            Freq a = iFreq.next();
            if(b.frequentadores.size() == a.frequentadores){
                existe = true;
            }
        }

        if(existe == false){
            for (Iterator<Local> iLocal2 = lista.iterator(); iLocal2.hasNext();) {
                Local a = iLocal2.next();
                if(a.frequentadores.size() == b.frequentadores.size()){
                    x.Luga();
                }
            } 

            freq.add(x);
        }

    }


    //coloca os dados num txt como saida
    try {

        // Cria arquivo
        File file = new File("dados_histograma.txt");

        // Se o arquivo nao existir, ele gera
        if (!file.exists()) {
            file.createNewFile();
        }

        // Prepara para escrever no arquivo
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        PrintWriter bw = new PrintWriter(fw);
        
        /* Escreve e fecha arquivo
        for (Freq o : freq) {
            bw.print(o.lugares+",");
            bw.println(o.frequentadores);
        }*/
        for (Local o : lista){
            bw.println(o.frequentadores.size());
        }
        fw.close();

        // Imprime confirmacao
        System.out.println("Feito =D");

    } catch (IOException e) {
        e.printStackTrace();
    }

}

public static void pegaLugar (List<Local> lista, String[] locais, int n){

    

    if (locais[n].equals("0") && locais[n+1].equals("0")) {
        return;
    }
    
    Local l1 = new Local(locais[n], locais[n+1], locais[43]);
    boolean encontrou = false;
    
    for (Iterator<Local> iterator = lista.iterator(); iterator.hasNext();) {
        Local a = iterator.next();
        if((l1.coordenada_x.equals(a.coordenada_x)) && (l1.coordenada_y.equals(a.coordenada_y))){
            if(a.frequentadores.contains(locais[43])){
                encontrou = true;
            }
            else{
                //a.frequentadores.add(locais[43]);
                lista.get(lista.indexOf(a)).frequentadores.add(locais[43]);
                encontrou = true;
            }     
        }
    }
    if(encontrou == false){
        lista.add(l1);
    } 

    

}

}