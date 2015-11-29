
package simulacion.juegodelavida;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Ventana extends javax.swing.JFrame {

    JPanel panel;
    int agentes,agentesm=0;
    ArrayList<Agente> agen=new ArrayList<Agente>();
    Agente ag[];
    double x[],y[];
    int k1=760,k2=1160;
    Calendar calendario = new GregorianCalendar();
    double e[];
    JLabel l1,l2,l3,l4;
    int tiempodevida;
    
    public Ventana(int tv,int ag){
        tiempodevida=tv;
        agentes=ag;
        initComponents();
        panel =new JPanel();
        panel.setSize(1160,760);
        panel.setBackground(Color.WHITE);
        l1=new JLabel("Cantidad de Agentes Vivos:");
        l2=new JLabel("");
        l3=new JLabel("Cantidad de Agentes Muertos:");
        l4=new JLabel("");
        l1.setBounds(1180, 20, 200, 30);
        l2.setBounds(1180, 60, 200, 30);
        l3.setBounds(1180, 100, 200, 30);
        l4.setBounds(1180, 140, 200, 30);
        this.add(panel);
        this.add(l1);
        this.add(l2);
        this.add(l3);
        this.add(l4);
        //agentes=Integer.parseInt(JOptionPane.showInputDialog("Cantidad de Agentes a crear: "));
        //ag=new Agente[agentes];
        x=gen(agentes,calendario.get(Calendar.SECOND),k2);
        y=gen(agentes,calendario.get(Calendar.SECOND)+5,k1);
        for(int i=0;i<agentes;i++){
            System.out.println("Se crearon los Agentes");
            /*ag[i]=new Agente();
            ag[i].setCx(x[i]);
            ag[i].setCy(y[i]);*/
            Agente a=new Agente();
            a.setCx(x[i]);
            a.setCy(y[i]);
            agen.add(a);
        }
        //System.out.println("Se crearon los Agentes");
    }

    public void mover() throws InterruptedException{
        System.out.println("Se van a mover");
        e=gen(agen.size(),calendario.get(Calendar.MILLISECOND),8);
        int agentes=agen.size();
        for(int i=0;i<agentes;i++){
            /*ag[i].setCx(ag[i].getCx()+5);
            ag[i].setCy(ag[i].getCy()+5);*/
            if(agen.get(i).getTiempodevida()>tiempodevida){
                System.out.println("El agente "+i+" Murio");
                agen.get(i).setVida(false);
                //agen.remove(i);
                //agentesm++;
            }else{
                System.out.println("Tiene Vecinos?");
                vecino(agen.get(i));
                System.out.println("Cambie de posicion");
                posicion(agen.get(i),(int)e[i]);
                System.out.println("Cambie el Tiempo de vida");
                agen.get(i).setTiempodevida(agen.get(i).getTiempodevida()+1);
            }
        }
        Thread.sleep(500);   
    }
    
    public void posicion(Agente a,int e){
        int x=0;
        int y=0;
        if(e==1){
            y=(int)a.getCy()-5;
            if(y<0){
                y=760;
            }
            a.setCy(y);
        }else if(e==2){
            y=(int)a.getCy()+5;
            if(y>760){
                y=0;
            }
            a.setCy(y);
        }else if(e==3){
            x=(int)a.getCx()-5;
            if(x<0){
                x=1160;
            }
            a.setCx(x);
        }else if(e==4){
            x=(int)a.getCx()+5;
            if(x>1160){
                x=0;
            }
            a.setCx(x);
        }else if(e==5){
            x=(int)a.getCx()+5;
            if(x>1160){
                x=0;
            }
            a.setCx(x);
            y=(int)a.getCy()-5;
            if(y<0){
                y=760;
            }
            a.setCy(y);
        }else if(e==6){
            x=(int)a.getCx()-5;
            if(x<0){
                x=1160;
            }
            a.setCx(x);
            y=(int)a.getCy()-5;
            if(y<0){
                y=760;
            }
            a.setCy(y);
        }else if(e==7){
            x=(int)a.getCx()-5;
            if(x<0){
                x=1160;
            }
            a.setCx(x);
            y=(int)a.getCy()+5;
            if(y>760){
                y=0;
            }
            a.setCy(y);
        }else {
            x=(int)a.getCx()+5;
            if(x>1160){
                x=0;
            }
            a.setCx(x);
            y=(int)a.getCy()+5;
            if(y>760){
                y=0;
            }
            a.setCy(y);
        }
    }
    public void vecino(Agente a){
        int agentes=agen.size();
        int d;
        int c=0;
        for(int i=0;i<agentes;i++){
            d=(int)Math.sqrt(Math.pow(a.getCx()-agen.get(i).getCx(),2)+Math.pow(a.getCy()-agen.get(i).getCy(),2));
            if(d>0 && d<20){
                c++;
                if(c>10){
                    a.setVida(false);
                    agen.get(i).setVida(false);
                    continue;
                }
            }
            if(d<20 && d!=0 && d>17){
                System.out.println("Si tiene Vecinos, ----REPRODUSCASE----");
                reproducir(a,agen.get(i));
            }
            
        }
        
    }
    
    public void reproducir(Agente a,Agente b){
        Agente ag=new Agente();
        ag.setCx((a.getCx()+b.getCx())/2);
        ag.setCy((a.getCy()+b.getCy())/2);
        agen.add(ag);
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int agentes=agen.size();
        for(int i=0;i<agen.size();i++){
            //if(ag[i].isVida()){
            if(agen.get(i).isVida()){
                g.setColor(Color.black);
                /*g.drawRect((int)ag[i].getCx(), (int)ag[i].getCy(), 10, 10);
                g.fillRect((int)ag[i].getCx(), (int)ag[i].getCy(), 10, 10);*/
                g.drawRect((int)agen.get(i).getCx(),(int)agen.get(i).getCy(), 10, 10);
                g.fillRect((int)agen.get(i).getCx(),(int)agen.get(i).getCy(), 10, 10);
            }else{
                agen.remove(i);
                agentesm++;
            }
        }
        if(agen.size()!=0){
            try {
                l2.setText(String.valueOf(agen.size()));
                l4.setText(String.valueOf(agentesm));
                mover();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Repinte");
            update(g);
        }
    }
    
    public double[] gen(int n,int x,int K){
        double rand[]=new double[n];
        int k=K;
        double xo=x*100;
        int ui=0;
        double u=0;
        for(int i=0;i<n;i++){
            xo=((65539*xo))%(Math.pow(2,16));
            u=xo/(Math.pow(2,16));
            u=(2*k*u)-k;
            if(u<0){
                u=u*-1;
            }
            rand[i]=(int)u;
        }
        return rand;
    }
    
    public int gen(int x,int K){
        int k=K;
        double xo=x*100;
        int ui=0;
        double u=0;
        xo=((65539*xo))%(Math.pow(2,16));
        u=xo/(Math.pow(2,16));
        u=(2*k*u)-k;
        if(u<0){
            u=u*-1;
        }
        return (int)u;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1360, 760));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
