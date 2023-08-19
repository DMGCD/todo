package db;

public class DBconnection {
    private static DBconnection dBconnection;

    private DBconnection(){

    }
    public static DBconnection getInstance(){
        return (dBconnection == null) ? dBconnection =new DBconnection() :dBconnection;
    }
}
