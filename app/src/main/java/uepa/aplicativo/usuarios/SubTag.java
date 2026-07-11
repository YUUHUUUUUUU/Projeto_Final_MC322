package uepa.aplicativo.usuarios;
public enum SubTag {
    BASKETBALL("Basquete",true),
    VOLLEYBALL("Vôlei",true),
    PARTICLEPHYSICS("Física de partículas",false);

    private final String name;
    private final boolean exists;

    SubTag(String name, boolean exists){
        this.name=name;
        this.exists=exists;
    }

    public String getName(){
        return name;
    }

    public boolean getExistence(){
        return exists;
    }

    


}
