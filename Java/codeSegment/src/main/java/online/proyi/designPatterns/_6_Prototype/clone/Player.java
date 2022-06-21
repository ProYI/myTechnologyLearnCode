package online.proyi.designPatterns._6_Prototype.clone;

import java.util.Date;

public class Player implements Cloneable {
    private String name;
    private Date birthday;

    public Player(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Player player = (Player) super.clone();

        // 引用对象 深克隆
        player.birthday = (Date) player.birthday.clone();
        return player;
    }
}
