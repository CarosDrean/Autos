package com.drean.projects.autos.presenter;

import com.drean.projects.autos.pojo.Pedido;

import java.util.ArrayList;

public interface IRegistroPedido {
    public void createPedido(Pedido pedido);
    public void updatePedido(Pedido pedido);
    public ArrayList<Pedido> getPedidos();
    public void deletePedido(int id);
}
