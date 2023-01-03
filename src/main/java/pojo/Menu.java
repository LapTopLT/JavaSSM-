package pojo;/*
 *Author:Litao
 *Created in:
 */

public class Menu {
    int menuid;
    int pid;
    String path;
    String name;
    String title;

    @Override
    public String toString() {
        return "Menu{" +
                "menuid=" + menuid +
                ", pid=" + pid +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public int getMenuid() {
        return menuid;
    }

    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Menu() {
    }

    public Menu(int menuid, int pid, String path, String name, String title) {
        this.menuid = menuid;
        this.pid = pid;
        this.path = path;
        this.name = name;
        this.title = title;
    }
}
