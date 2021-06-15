/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rip.Torres! ^-^
 */
public class ArbolGeneral {
    NodoGeneral raiz;
    
    public ArbolGeneral(){
        raiz = null;
    }
    
    public boolean insertar(char dato, String path){
        if(raiz == null){
            raiz = new NodoGeneral(dato);
            if(raiz==null) return false;
            return true;
        }
        
        if(path.isEmpty()) return false;
        
        NodoGeneral padre = buscarNodoRECURSIVO(path);
        if(padre==null) return false;
        
        NodoGeneral hijoYaExiste = buscarNodoRECURSIVO(path+"/"+dato);
        if(hijoYaExiste!=null) return false;
        
        NodoGeneral nuevo = new NodoGeneral(dato);
        return padre.enlazar(nuevo);
    }
    
//    private NodoGeneral buscarNodo(String path){
//        path = path.substring(1);
//        
//        String vector[] = path.split("/");
//        
//        if(vector[0].charAt(0) == raiz.dato){
//            if(vector.length==1) return raiz;
//            NodoGeneral padre = raiz;
//            for(int i = 1; i<vector.length; i++){
//                padre = padre.obtenerHijo(vector[i].charAt(0));
//                if(padre==null) return null;
//            }
//            return padre;
//        }
//        
//        return null;
//    }
    
    private NodoGeneral buscarNodoRECURSIVO(String path){
        String pathTemporal = path;
        path = path.substring(1);
        int q = path.length();
        int w = pathTemporal.length();
        w = w/2;
        
        if(pathTemporal.charAt(1) == raiz.dato){
            //EVALUACIONES
            if(w == 1) return raiz;
            NodoGeneral padre = raiz;
            
            if(q == 0){
                padre = padre.obtenerHijo(path.charAt(0));
                buscarNodoRECURSIVO(path.substring(1));
                if(padre == null) return null;
            }            
            
            return padre;
        }
        
        return null;
    }
    
    public boolean eliminar(String path){
        if(raiz==null) return false;
        
        NodoGeneral hijo = buscarNodoRECURSIVO(path);
        if(hijo == null) return false;
        
        //si es una rama no se puede eliminar.
        if(!hijo.esHoja()) return false;
        
        if(hijo == raiz){
            raiz = null;
            return true;
        }
        
        String pathPadre = obtenerPathPadre(path);
        NodoGeneral padre = buscarNodoRECURSIVO(path);
        
        return padre.desenlazar(hijo);
    }

    private String obtenerPathPadre(String path) {
        int posicionANTESUltimaDiagonal = path.lastIndexOf("/")-1;
        return path.substring(0, posicionANTESUltimaDiagonal);
    }
}
