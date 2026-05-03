package test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder();

        sb.append('1');
        sb.append('2');

        System.out.println(sb);

        sb.append("3");
        System.out.println(sb);

        sb.undo();
        System.out.println("должно быть 12:" + sb);
    }
}