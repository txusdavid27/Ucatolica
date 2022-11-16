package edu.puj.taller3.Model;

public class Usuario {

    private String key;
    private String name;
    private String apellido;
    private int id;
    private double latitude;
    private double longitude;
    private boolean disponible;
    private byte[] photo;

    public Usuario(){

    }

    public Usuario(String key, String name, String apellido, int id, double latitude, double longitude, boolean disponible){
        this.key = key;
        this.name = name;
        this.apellido = apellido;
        this.id = id;
        this.latitude= latitude;
        this.longitude = longitude;
        this.disponible = disponible;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setApellido(String apellido){
        this.apellido = apellido;
    }

    public String getApellido(){
        return apellido;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public boolean getDisponible() { return disponible; }

    public void setPhoto(byte[] photo) { this.photo = photo; }

    public byte[] getPhoto() { return photo; }

}
