import java.util.ArrayList;
import java.util.List;

public class Local {
    String coordenada_x;
    String coordenada_y;
    List<String> frequentadores = new ArrayList<String>();

    public Local(String coordenada_x, String coordenada_y, String p){
        this.coordenada_x = coordenada_x;
        this.coordenada_y = coordenada_y;
        this.frequentadores.add(p);
        
    }

    public void adicionaFrequentador(String f){
        this.frequentadores.add(f);
    }
}