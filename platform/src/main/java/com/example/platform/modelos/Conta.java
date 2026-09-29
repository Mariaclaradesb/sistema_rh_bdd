package com.example.platform.modelos;

public class Conta {

    private String cliente;
    private Integer numero;
    private Double saldo;
    private Double limite;

    private int valorTransacoes;
    private int quantidadeSaques;
    private int quantidadeDepositos;

    public Conta(String cliente, int numero, Double limite, Double saldo) {
        this.cliente = cliente;
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.valorTransacoes = 0;
        this.quantidadeDepositos = 0;
        this.quantidadeSaques = 0;
    }

    public void sacar(Double valor) {
        if (saldo + limite <= valor) {
            // Não pode sacar
            throw new RuntimeException("Não possui saldo suficiente");
        }
        saldo = saldo - valor;
        valorTransacoes++;
        quantidadeSaques++;
    }

    public void depositar(Double valor) {
        saldo += valor;
        valorTransacoes++;
        quantidadeDepositos++;
    }

    public void enviarPix(Double valor){
        if (saldo <= valor) {
            // Não pode sacar
            throw new RuntimeException("Não possui saldo suficiente");
        }
        saldo = saldo - valor;
        valorTransacoes++;
    }

    public void receberPix(Double valor){
        saldo += valor;
        valorTransacoes++;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public int getvalorTransacoes() {
        return valorTransacoes;
    }

    public void setvalorTransacoes(int valorTransacoes) {
        this.valorTransacoes = valorTransacoes;
    }

    public int getValorTransacoes() {
        return valorTransacoes;
    }

    public void setValorTransacoes(int valorTransacoes) {
        this.valorTransacoes = valorTransacoes;
    }

    public int getQuantidadeSaques() {
        return quantidadeSaques;
    }

    public void setQuantidadeSaques(int quantidadeSaques) {
        this.quantidadeSaques = quantidadeSaques;
    }

    public int getQuantidadeDepositos() {
        return quantidadeDepositos;
    }

    public void setQuantidadeDepositos(int quantidadeDepositos) {
        this.quantidadeDepositos = quantidadeDepositos;
    }
}