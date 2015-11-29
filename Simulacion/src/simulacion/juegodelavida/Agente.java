package simulacion.juegodelavida;

public class Agente {
    private int tiempodevida=0;
    
    private boolean vida=true;

    public void setVida(boolean vida) {
        this.vida = vida;
    }

    public boolean isVida() {
        return vida;
    }
    public void setTiempodevida(int tiempodevida) {
        this.tiempodevida = tiempodevida;
    }

    public void setCx(double cx) {
        this.cx = cx;
    }

    public void setCy(double cy) {
        this.cy = cy;
    }

    public int getTiempodevida() {
        return tiempodevida;
    }

    public double getCx() {
        return cx;
    }

    public double getCy() {
        return cy;
    }
    private double cx,cy;
    
}
