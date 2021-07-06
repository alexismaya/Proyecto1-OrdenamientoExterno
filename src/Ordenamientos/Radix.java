package Ordenamientos;

import java.io.File;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Radix {
    public static void ordenar(File origen, Comparator<Integer> comparator,File Destino){
        String[] lectI=leerA(origen);
        int[] CI=ConvertirAI(lectI);
        int[] ArrO=RadixSort(CI,Destino);
        lectI=ConvertirAS(ArrO);
        GAO(lectI, "Arreglo Ordenado con Radix",Destino);
        System.out.println("\nEL archivo ha sido ordenado con exito");

    }
    public static String[] leerA(File NA){ // Este metodo recoge la información del archivo
        String[] arrS=new String[10000];
        try{
            int i=0,c;
            FileReader Fr= new FileReader(NA);
            BufferedReader Br= new BufferedReader(Fr);
            String linea="";
            while(linea!=null){
                linea=Br.readLine();
                if(linea.equals("]"))
                    break;
                String x;
                x=linea;
                arrS=x.split(",");
                i+=1;
            }
            Br.close();
            for(c=i;c<arrS.length;c++){
                arrS[c]=null;
            }
        }catch(Exception e){

        }
        return arrS;
    }
    public static int[] ConvertirAI(String[] as){  // Este metodo convierte la información tomada de String a Int
        int i=0;
        int[] arrI=new int[as.length];
        for(i=0;i<arrI.length-1;i++)
            arrI[i]=-999;
        i=0;
        for(i=0;i<arrI.length-1;i++){ 
            int tmp=Integer.parseInt(as[i]);
            arrI[i]=tmp;
        }
        return arrI;
    }
    public static int[] RadixSort(int[] ai,File Destino){ //En este metodo se ordenea el arreglo convertido
        int i,j,k,tm=ai.length;
      for(k=Integer.SIZE-1;k>=0;k--){
          int a[]=new int[tm];
          j=0;
          for(i=0;i<tm;i++){
              boolean m=ai[i]<<k>=0;
              if(k==0 ? !m:m){
                  a[j]=ai[i];
                  j++;
              }else{
                  ai[i-j]=ai[i];
              }
              FA1(a, Destino,k);
          }
          for(i=j;i<a.length;i++){
              a[i]=ai[i-j];
          }
          ai=a;
        }
        return ai;
    }
    public static String[] ConvertirAS(int[] ai){ // Aquí ahora se convierte el arreglo Int a String para llenar el nuevo archivo ordenado
        int i=0;
        String[] ASF=new String[ai.length];
        for(i=0;i<ai.length;i++){
            String tmp=Integer.toString(ai[i]);
            ASF[i]=tmp;
        }
        return ASF;
    }
    public static void GAO(String[] asf, String nuevoNom,File directorioDestino){ //De este metodo se genera el nuevo archivo con los datos ya ordenados en una ruta de momento preasignada
        try {
            int i=0;
            FileWriter CAO=new FileWriter(directorioDestino+"//"+nuevoNom+"//"+".txt");
            BufferedWriter BCAO=new BufferedWriter(CAO);
            BCAO.append("[");
            for(i=0;i<asf.length;i++){
                BCAO.write(asf[i]+",");
            }
            BCAO.append("]");
            BCAO.close();
        } catch (Exception e) {
        }
    }
    public static void FA1(int[] asf1,File directorioDestino, int k){
        try {
            int i=0;

            FileWriter FW1=new FileWriter(directorioDestino+"//"+"Elementos("+k+").txt");
            BufferedWriter BFW1=new BufferedWriter(FW1);
            BFW1.append("[");
            for(i=0;i<asf1.length;i++){
                BFW1.write(asf1[i]);
            }
            BFW1.append("]");
            BFW1.close();
        } catch (Exception e) {
        }
    }
}
