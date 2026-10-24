package emberdeep.ejercicios.c2;

/**
 * GuardMain
 */
public class GuardMain {

    public static void main(String[] args) {
        Behavior attack = (actor) -> 
            actor + " ataca con furia.";
        Behavior defense = (actor) -> 
            actor + " se cubre con el escudo.";
        
        Guardian golem = new Guardian("Golem", 20, attack, defense);
        System.out.println(golem.act());
        golem.takeDamage(5);
        System.out.println(golem.act());
        golem.takeDamage(10);
        System.out.println(golem.act());
    }
}
