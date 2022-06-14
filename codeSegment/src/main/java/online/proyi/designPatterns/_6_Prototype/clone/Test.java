package online.proyi.designPatterns._6_Prototype.clone;

import java.util.Date;

/**
 * 测试 深拷贝 与 浅拷贝
 */
public class Test {
    public static void main(String[] args) throws CloneNotSupportedException {
        Player player = new Player("头号玩家", new Date());
        Player clonePlayer = (Player) player.clone();
        System.out.println(player);
        System.out.println(clonePlayer);

        // 如果没有对引用类型进行深克隆
        // 仅 修改player的birthday，同样影响到了  clonePlayer的值，浅克隆
        player.getBirthday().setTime(0L);
        System.out.println(player);
        System.out.println(clonePlayer.clone());

    }
}
