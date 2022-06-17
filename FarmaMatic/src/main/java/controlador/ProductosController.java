/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import EJB.ProductoFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import modelo.Producto;

/**
 *
 * @author sanch
 */
@Named
@ViewScoped
public class ProductosController implements Serializable{
    private List<Producto> productos;
    
    @EJB
    private ProductoFacadeLocal productoEJB;
    
    @PostConstruct
    public void init(){
        productos = productoEJB.findNoVendidos();
    }
    
    public void eliminarProducto(Producto producto) throws IOException{
        productoEJB.remove(producto);

    }
    
    public String add(){
        return "addProducto.xhtml";
    }
    
    public float calcularIVA(Producto producto){
        float iva = 0;
        
        iva = (producto.getPvp()*producto.getIva())/100;
        
        return iva;
    }
    
     public float calcularPVP(Producto producto){
        float pvp = 0;
        
        pvp = this.calcularIVA(producto) + producto.getPvp();
        
        return pvp;
    }
    
    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public ProductoFacadeLocal getProductoEJB() {
        return productoEJB;
    }

    public void setProductoEJB(ProductoFacadeLocal productoEJB) {
        this.productoEJB = productoEJB;
    }
}
