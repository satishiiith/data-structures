public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        System.out.println(fileSystem.insertPath("/a",1));
        System.out.println(fileSystem.insertPath("/a//b",1));
        System.out.println(fileSystem.insertPath("/a/c/d",2));
        System.out.println(fileSystem.insertPath("/",2));





    }



}