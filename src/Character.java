public abstract class Character {
    public String name;
    public int HP, XP, maxHP;

    public Character(String name, int HP,int maxHP, int XP){
        this.name = name;
        this.XP = XP;
        this.maxHP = maxHP;
        this.HP = HP;
    }

    public abstract int attack();
    public abstract int defence();
}
